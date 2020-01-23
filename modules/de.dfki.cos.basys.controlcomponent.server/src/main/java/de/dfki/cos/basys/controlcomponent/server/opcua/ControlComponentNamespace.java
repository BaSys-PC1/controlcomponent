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
    	ComponentContext.getStaticContext().getEventBus().unregister(this);		
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
