package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BaseDataVariableType;

public interface ControlComponentStatusType extends BaseDataVariableType {

	CompletableFuture<Integer> getErrorCode();

	CompletableFuture<? extends BaseDataVariableNode> getErrorCodeNode();

	CompletableFuture<String> getErrorMessage();

	CompletableFuture<? extends BaseDataVariableNode> getErrorMessageNode();

	CompletableFuture<String> getExecutionMode();

	CompletableFuture<? extends BaseDataVariableNode> getExecutionModeNode();

	CompletableFuture<String> getExecutionState();

	CompletableFuture<? extends BaseDataVariableNode> getExecutionStateNode();

	CompletableFuture<String> getOccupationState();

	CompletableFuture<? extends BaseDataVariableNode> getOccupationStateNode();

	CompletableFuture<String> getOccupierId();

	CompletableFuture<? extends BaseDataVariableNode> getOccupierIdNode();

	CompletableFuture<String> getOperationMode();

	CompletableFuture<? extends BaseDataVariableNode> getOperationModeNode();

	CompletableFuture<String> getWorkState();

	CompletableFuture<? extends BaseDataVariableNode> getWorkStateNode();
}
