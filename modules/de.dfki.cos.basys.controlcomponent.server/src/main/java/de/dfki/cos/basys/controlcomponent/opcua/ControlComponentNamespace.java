/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.opcua;

import java.util.List;
import java.util.Optional;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.opcua.loader.ControlComponentNodeLoader;

public class ControlComponentNamespace extends ManagedNamespace {

    public static final String NAMESPACE_URI = "http://www.basys40.de/controlcomponent";

	public static NodeId ControlComponentType;
	public static NodeId ControlComponentType_STATUS;
	public static NodeId ControlComponentType_STATUS_ERRCODE;
	public static NodeId ControlComponentType_STATUS_ERRMSG;
	public static NodeId ControlComponentType_STATUS_EXMODE;
	public static NodeId ControlComponentType_STATUS_EXST;
	public static NodeId ControlComponentType_STATUS_OCCST;
	public static NodeId ControlComponentType_STATUS_OCCUPIER;
	public static NodeId ControlComponentType_STATUS_OPMODE;
	public static NodeId ControlComponentType_STATUS_WORKST;
	
	public static NodeId StatusType;	
	public static NodeId StatusType_ERRCODE;
	public static NodeId StatusType_ERRMSG;
	public static NodeId StatusType_EXMODE;
	public static NodeId StatusType_EXST;
	public static NodeId StatusType_OCCST;
	public static NodeId StatusType_OCCUPIER;
	public static NodeId StatusType_OPMODE;
	public static NodeId StatusType_WORKST;
	
	public static NodeId StatusDataType;
	public static NodeId StatusDataType_Description_Encoding_DefaultBinary;
	public static NodeId StatusDataType_Description_Encoding_DefaultXml;
	public static NodeId StatusDataType_Encoding_DefaultBinary;
	public static NodeId StatusDataType_Encoding_DefaultXml;

	
    private final Logger logger;        
    private final SubscriptionModel subscriptionModel;
  
    private int nodeIndex = 0;

    ControlComponentNamespace(OpcUaServer server) {
        super(server, NAMESPACE_URI);   
        logger = LoggerFactory.getLogger(getClass()); 
        subscriptionModel = new SubscriptionModel(server, this);
    }

    @Override
    protected void onShutdown() {
    	super.onShutdown();    
    	ComponentContext.getStaticContext().getEventBus().unregister(this);		
    }
    
    @Override
    protected void onStartup() {
        super.onStartup();        
        
    	ControlComponentType = newNodeId();     
    	ControlComponentType_STATUS = newNodeId(); 
    	ControlComponentType_STATUS_ERRCODE = newNodeId(); 
    	ControlComponentType_STATUS_ERRMSG = newNodeId(); 
    	ControlComponentType_STATUS_EXMODE = newNodeId(); 
    	ControlComponentType_STATUS_EXST = newNodeId(); 
    	ControlComponentType_STATUS_OCCST = newNodeId(); 
    	ControlComponentType_STATUS_OCCUPIER = newNodeId(); 
    	ControlComponentType_STATUS_OPMODE = newNodeId(); 
    	ControlComponentType_STATUS_WORKST = newNodeId();    

    	StatusType = newNodeId();
    	StatusType_ERRCODE = newNodeId();
    	StatusType_ERRMSG = newNodeId();
    	StatusType_EXMODE = newNodeId();
    	StatusType_EXST = newNodeId();
    	StatusType_OCCST = newNodeId();
    	StatusType_OCCUPIER = newNodeId();
    	StatusType_OPMODE = newNodeId();
    	StatusType_WORKST = newNodeId();
        
      	StatusDataType = newNodeId();
    	StatusDataType_Description_Encoding_DefaultBinary = newNodeId();
    	StatusDataType_Description_Encoding_DefaultXml = newNodeId();
    	StatusDataType_Encoding_DefaultBinary = newNodeId();
    	StatusDataType_Encoding_DefaultXml = newNodeId();
    	
    	
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
    	
    	ComponentContext.getStaticContext().getEventBus().register(this); 
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
				UaNode node = createComponentRootNode((ControlComponent) component);
				
				// Make sure our new folder shows up under the server's Objects folder.
				node.addReference(new Reference(node.getNodeId(), Identifiers.Organizes,
						Identifiers.ObjectsFolder.expanded(), false));
			}
		}
		else if (ev.getType() == Type.COMPONENT_DELETED) {
			Optional<UaNode> node = getNodeManager().removeNode(newNodeId(ev.getValue()));
			node.ifPresent(n -> n.delete());
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
    
}
