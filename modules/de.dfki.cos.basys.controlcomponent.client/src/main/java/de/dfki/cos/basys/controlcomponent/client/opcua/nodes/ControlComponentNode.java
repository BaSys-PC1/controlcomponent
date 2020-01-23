package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseObjectNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentType;
import de.dfki.cos.basys.controlcomponent.client.util.NodeIds;

public class ControlComponentNode extends BaseObjectNode implements ControlComponentType {
    
    public ControlComponentNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }
    
    @Override
	public CompletableFuture<ControlComponentStatusNode> getControlComponentStatusNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "STATUS").thenApply(ControlComponentStatusNode.class::cast);
	}
	
	@Override
	public CompletableFuture<ControlComponentStatusDataType> getControlComponentStatus() {
		return getControlComponentStatusNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ControlComponentStatusDataType.class));
	}

}
