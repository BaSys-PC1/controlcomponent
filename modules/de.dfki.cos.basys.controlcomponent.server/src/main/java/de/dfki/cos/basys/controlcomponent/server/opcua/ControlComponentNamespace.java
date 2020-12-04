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

import org.eclipse.basyx.aas.manager.ConnectedAssetAdministrationShellManager;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.haskind.ModelingKind;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetypedef.PropertyValueTypeDef;
import org.eclipse.basyx.vab.modelprovider.lambda.VABLambdaProviderHelper;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
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

public class ControlComponentNamespace extends ManagedNamespace {
	
    private final Logger logger;        
    private final SubscriptionModel subscriptionModel;
  
    private int nodeIndex = 0;

    ControlComponentNamespace(OpcUaServer server) {
        super(server, NodeIds.NAMESPACE_URI);   
        logger = LoggerFactory.getLogger(getClass()); 
        subscriptionModel = new SubscriptionModel(server, this);
    }

    @Override
    protected void onShutdown() {
    	super.onShutdown();    
    	AasComponentContext.getStaticContext().getEventBus().unregister(this);		
    }
    
    @Override
    protected void onStartup() {
        super.onStartup();        
        
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
    	
    	AasComponentContext.getStaticContext().getEventBus().register(this); 
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
				

				//create and register config submodel
				ConnectedAssetAdministrationShellManager aasManager = new ConnectedAssetAdministrationShellManager(AasComponentContext.getStaticContext().getAasRegistry());
				SubModel configSubmodel = createConfigSubmodel(node, cc);
				aasManager.createSubModel(cc.getAasId(), configSubmodel);			
				
			}
		}
		else if (ev.getType() == Type.COMPONENT_DELETED) {
			Component component = ev.getComponent();
			if (component instanceof ControlComponent) {
				ControlComponent cc = (ControlComponent) component;
				Optional<UaNode> node = getNodeManager().removeNode(newNodeId(ev.getValue()));
				node.ifPresent(n -> n.delete());	
				
				//TODO: remove and unregister config sm				
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
    
    private NodeId newNodeId() {
    	return newNodeId(nodeIndex++);
    }
    
    private SubModel createConfigSubmodel(UaNode node, ControlComponent component) {
    	SubModel submodel = new SubModel();		
		submodel.setIdShort(component.getId() + "-ccc");
		submodel.setIdentification(component.getSubmodelId().getIdType(), component.getSubmodelId().getId().replace("control-component", "control-component-config"));
		submodel.setModelingKind(ModelingKind.INSTANCE);
		submodel.setDescription(new LangStrings("en-US", "ControlComponent configuration submodel for component " + component.getId()));
		submodel.setSemanticId(new org.eclipse.basyx.submodel.metamodel.map.reference.Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "ControlComponentConfiguration", IdentifierType.CUSTOM)));
		
//		SubmodelElementCollection status = new SubmodelElementCollection();
//		status.setIdShort("STATUS");
//		submodel.addSubModelElement(status);
			
		Property nodeId = new Property();
		nodeId.setIdShort("nodeId");
		nodeId.set(node.getNodeId().toParseableString());
		submodel.addSubModelElement(nodeId);
		
//		for (EndpointConfiguration ec : this.getServer().getConfig().getEndpoints()) {
//			SubmodelElementCollection endpointConfig = new SubmodelElementCollection();
//			endpointConfig.setIdShort("endpoint");
//
//			Property endpoint = new Property();
//			endpoint.setIdShort("endpoint");
//			endpoint.set(ed.getEndpointUrl());
//			
//			Property transportProfile = new Property();
//			transportProfile.setIdShort("transportProfile");
//			transportProfile.set(ed.getTransportProfileUri());
//
//			Property securityPolicy = new Property();
//			securityPolicy.setIdShort("securityPolicy");
//			securityPolicy.set(ed.getSecurityPolicyUri());			
//
//			endpointConfig.addElement(endpoint);
//			endpointConfig.addElement(transportProfile);
//			endpointConfig.addElement(securityPolicy);
//
//			submodel.addSubModelElement(endpointCollection);
//		}
				
		for (EndpointDescription ed : this.getServer().getEndpointDescriptions()) {
			
			SubmodelElementCollection endpointCollection = new SubmodelElementCollection();
			endpointCollection.setIdShort("endpoint");

			Property endpoint = new Property();
			endpoint.setIdShort("endpoint");
			endpoint.set(ed.getEndpointUrl());
			
			Property transportProfile = new Property();
			transportProfile.setIdShort("transportProfile");
			transportProfile.set(ed.getTransportProfileUri());

			Property securityPolicy = new Property();
			securityPolicy.setIdShort("securityPolicy");
			securityPolicy.set(ed.getSecurityPolicyUri());			

			endpointCollection.addElement(endpoint);
			endpointCollection.addElement(transportProfile);
			endpointCollection.addElement(securityPolicy);

			submodel.addSubModelElement(endpointCollection);
		}		
		
		return submodel;
    }
    
}
