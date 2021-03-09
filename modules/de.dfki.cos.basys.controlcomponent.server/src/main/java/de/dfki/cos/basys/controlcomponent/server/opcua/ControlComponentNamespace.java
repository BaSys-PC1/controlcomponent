/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.server.opcua;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.eclipse.basyx.aas.manager.ConnectedAssetAdministrationShellManager;
import org.eclipse.basyx.submodel.metamodel.api.ISubmodel;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.haskind.ModelingKind;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.ISubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetype.ValueType;
import org.eclipse.basyx.vab.modelprovider.lambda.VABLambdaProviderHelper;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.DataTypeDictionaryManager;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespaceWithLifecycle;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.server.opcua.loader.ControlComponentNodeLoader;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.ControlComponentNodeBuilder;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;

public class ControlComponentNamespace extends ManagedNamespaceWithLifecycle {
	
    private final Logger logger;        
    private final DataTypeDictionaryManager dictionaryManager;
    private final SubscriptionModel subscriptionModel;
    
    private int nodeIndex = 0;

    ControlComponentNamespace(OpcUaServer server) {
        super(server, NodeIds.NAMESPACE_URI);   
        logger = LoggerFactory.getLogger(getClass()); 
        dictionaryManager = new DataTypeDictionaryManager(getNodeContext(), NodeIds.NAMESPACE_URI);
        subscriptionModel = new SubscriptionModel(server, this);

        getLifecycleManager().addLifecycle(dictionaryManager);
        getLifecycleManager().addLifecycle(subscriptionModel);

        getLifecycleManager().addStartupTask(this::createAndAddNodes);

        getLifecycleManager().addLifecycle(new Lifecycle() {
            @Override
            public void startup() {
            	AasComponentContext.getStaticContext().getEventBus().register(ControlComponentNamespace.this);             	
            }

            @Override
            public void shutdown() {
            	AasComponentContext.getStaticContext().getEventBus().unregister(ControlComponentNamespace.this);	
//                try {
//                    keepPostingEvents = false;
//                    eventThread.interrupt();
//                    eventThread.join();
//                } catch (InterruptedException ignored) {
//                    // ignored
//                }
            }
        });
        

    }

    private void createAndAddNodes() {        
        
        NodeIds.initNodeIds(getNamespaceIndex());    	
    	
    	ControlComponentNodeLoader loader = new ControlComponentNodeLoader(getNodeContext(), getNodeManager(), getNamespaceIndex());
    	try {
			loader.loadNodes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	  // Set a reasonable value for the MinimumSamplingInterval
        // attribute on all VariableNodes, otherwise it defaults to 0.
        getNodeManager().getNodes()
            .stream()
            .filter(node -> node instanceof UaVariableNode)
            .map(UaVariableNode.class::cast)
            .forEach(n -> n.setMinimumSamplingInterval(100.0));
    	
    }
    
    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }

	@Subscribe
	public void onComponentManagerEvent(ComponentManagerEvent ev) {		
		if (ev.getType() == Type.COMPONENT_ADDED) {
			Component component = ev.getComponent();
			if (component instanceof ControlComponent) {
				ControlComponent cc = (ControlComponent) component;
				UaNode node = createComponentRootNode(cc);
				
				// Make sure our new folder shows up under the server's Objects folder.
				node.addReference(new Reference(node.getNodeId(), Identifiers.Organizes,
						Identifiers.ObjectsFolder.expanded(), false));
				
				AasComponentContext.getStaticContext().getScheduledExecutorService().schedule(new Runnable() {
					@Override
					public void run() {
						//create and register config submodel
						ConnectedAssetAdministrationShellManager aasManager = new ConnectedAssetAdministrationShellManager(AasComponentContext.getStaticContext().getAasRegistry());
											
						Identifier instanceSubmodelId = new Identifier(cc.getSubmodelId().getIdType(), cc.getSubmodelId().getId().replace("control-component", "control-component-instance"));
						
						ISubmodel instanceSubmodel = aasManager.retrieveSubmodel(cc.getAasId(), instanceSubmodelId);		
						
						logger.info("adding endpoint descriptions to instance submodel " + instanceSubmodelId.getId());
						addEndpointDescription(node, instanceSubmodel);						
					}
				}, 5000, TimeUnit.MILLISECONDS);
				
				
			}
		}
		else if (ev.getType() == Type.COMPONENT_DELETED) {
			Component component = ev.getComponent();
			if (component instanceof ControlComponent) {
				ControlComponent cc = (ControlComponent) component;
				Optional<UaNode> node = getNodeManager().removeNode(newNodeId(ev.getValue()));
				node.ifPresent(n -> n.delete());	
				
				//TODO: remove config fragment--> not necessary, sm is completely deleted if cc is deactivated by component manager			
			}
			
		}
	}
    
    protected UaNode createComponentRootNode(ControlComponent component) {
    	UaFolderNode folderNode = new UaFolderNode(
				getNodeContext(),
				newNodeId(component.getId()),
		        newQualifiedName(component.getName()),
	            LocalizedText.english(component.getName())
			);

		folderNode.addComponent(new ControlComponentNodeBuilder(getNodeContext(), getNodeManager(), getNamespaceIndex()).build(component));
		getNodeManager().addNode(folderNode);
		return folderNode;
	}
    
    private void addEndpointDescription(UaNode node, ISubmodel sm) {
    	
		//configure opcua endpoints    	
    	SubmodelElementCollection endpointDescriptions = new SubmodelElementCollection();
    	endpointDescriptions.setIdShort("EndpointDescriptions");	
    	
    	int i = 0;
		for (EndpointDescription ed : this.getServer().getEndpointDescriptions()) {
			
			SubmodelElementCollection endpointDescription = new SubmodelElementCollection();
			endpointDescription.setIdShort("EndpointDescription" + i++);

			Property endpoint = new Property();
			endpoint.setIdShort("Endpoint");
			endpoint.set(ed.getEndpointUrl(), ValueType.String);

			Property nodeId = new Property();
			nodeId.setIdShort("NodeId");
			nodeId.set(node.getNodeId().toParseableString(), ValueType.String);
			
			Property profile = new Property();
			profile.setIdShort("Profile");
			profile.set("4", ValueType.Integer);
			
			Property transportProfile = new Property();
			transportProfile.setIdShort("TransportProfile");
			transportProfile.set(ed.getTransportProfileUri(), ValueType.String);

			Property securityPolicy = new Property();
			securityPolicy.setIdShort("SecurityPolicy");
			securityPolicy.set(ed.getSecurityPolicyUri(), ValueType.String);			

			endpointDescription.addSubmodelElement(endpoint);
			endpointDescription.addSubmodelElement(nodeId);
			endpointDescription.addSubmodelElement(profile);
			endpointDescription.addSubmodelElement(transportProfile);
			endpointDescription.addSubmodelElement(securityPolicy);
			endpointDescriptions.addSubmodelElement(endpointDescription);		
		}		
				
		sm.addSubmodelElement(endpointDescriptions);
    }
    
}
