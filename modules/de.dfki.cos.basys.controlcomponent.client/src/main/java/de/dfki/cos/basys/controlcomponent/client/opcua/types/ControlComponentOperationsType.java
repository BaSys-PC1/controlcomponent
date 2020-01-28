package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseObjectType;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FolderType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ControlComponentOperationsType extends FolderType {

	CompletableFuture<NodeId> getResetMethodNodeId();
	CompletableFuture<NodeId> getStartMethodNodeId();
	CompletableFuture<NodeId> getStopMethodNodeId();
	CompletableFuture<NodeId> getHoldMethodNodeId();
	CompletableFuture<NodeId> getUnholdMethodNodeId();
	CompletableFuture<NodeId> getSuspendMethodNodeId();
	CompletableFuture<NodeId> getUnsuspendMethodNodeId();
	CompletableFuture<NodeId> getAbortMethodNodeId();
	CompletableFuture<NodeId> getClearMethodNodeId();
	
	CompletableFuture<NodeId> getFreeMethodNodeId();
	CompletableFuture<NodeId> getOccupyMethodNodeId();
	CompletableFuture<NodeId> getPrioMethodNodeId();
	
	CompletableFuture<NodeId> getAutoMethodNodeId();
	CompletableFuture<NodeId> getSemiAutoMethodNodeId();
	CompletableFuture<NodeId> getManualMethodNodeId();
	CompletableFuture<NodeId> getSimulateMethodNodeId();	
	
	CompletableFuture<NodeId> getOperationModeMethodNodeId(String opmode);
}
