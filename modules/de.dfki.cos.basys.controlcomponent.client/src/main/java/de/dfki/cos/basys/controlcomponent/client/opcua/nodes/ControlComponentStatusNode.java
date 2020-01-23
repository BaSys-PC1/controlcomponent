package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusType;
import de.dfki.cos.basys.controlcomponent.client.util.NodeIds;


public class ControlComponentStatusNode extends BaseDataVariableNode implements ControlComponentStatusType {

	public ControlComponentStatusNode(OpcUaClient client, NodeId nodeId) {
		super(client, nodeId);
	}

	@Override
	public CompletableFuture<Integer> getErrorCode() {
		 return getErrorCodeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, Integer.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getErrorCodeNode() {
		 return getVariableComponent(NodeIds.NAMESPACE_URI, "ERRCODE").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getErrorMessage() {
		 return getErrorMessageNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getErrorMessageNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "ERRMSG").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getExecutionMode() {
		 return getExecutionModeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getExecutionModeNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "EXMODE").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getExecutionState() {
		 return getExecutionStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getExecutionStateNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "EXST").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getOccupationState() {
		 return getOccupationStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getOccupationStateNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "OCCST").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getOccupierId() {
		 return getOccupierIdNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getOccupierIdNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "OCCUPIER").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getOperationMode() {
		 return getOperationModeNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getOperationModeNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "OPMODE").thenApply(BaseDataVariableNode.class::cast);
	}

	@Override
	public CompletableFuture<String> getWorkState() {
		 return getWorkStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
	}

	@Override
	public CompletableFuture<? extends BaseDataVariableNode> getWorkStateNode() {
		return getVariableComponent(NodeIds.NAMESPACE_URI, "WORKST").thenApply(BaseDataVariableNode.class::cast);
	}

}
