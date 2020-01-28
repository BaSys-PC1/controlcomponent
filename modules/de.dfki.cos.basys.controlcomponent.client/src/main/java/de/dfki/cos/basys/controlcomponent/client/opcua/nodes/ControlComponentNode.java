package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseObjectNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FolderType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentOperationsType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentType;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentNode extends BaseObjectNode implements ControlComponentType {
    
    public ControlComponentNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }
    
    @Override
	public CompletableFuture<ControlComponentStatusNode> getControlComponentStatusNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Status")).thenApply(ControlComponentStatusNode.class::cast);
	}
	
	@Override
	public CompletableFuture<ControlComponentStatusDataType> getControlComponentStatus() {
		return getControlComponentStatusNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ControlComponentStatusDataType.class));
	}

	@Override
	public CompletableFuture<ControlComponentOperationsNode> getControlComponentOperationsNode() {
		return getObjectComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Operations")).thenApply(ControlComponentOperationsNode.class::cast);
	}

	@Override
	public CompletableFuture<? extends FolderType> getControlComponentVariablesNode() {
		return getObjectComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Variables")).thenApply(FolderNode.class::cast);
	}

}
