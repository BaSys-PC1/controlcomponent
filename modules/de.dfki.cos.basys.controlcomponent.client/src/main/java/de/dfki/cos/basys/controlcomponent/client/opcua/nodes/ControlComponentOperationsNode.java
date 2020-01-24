package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentOperationsType;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentOperationsNode extends FolderNode implements ControlComponentOperationsType {
	

	public ControlComponentOperationsNode(OpcUaClient client, NodeId nodeId) {
		super(client, nodeId);
	}

	@Override
	public CompletableFuture<NodeId> getResetMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Reset"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getStartMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Start"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getStopMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Stop"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getHoldMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Hold"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getUnholdMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Unhold"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getSuspendMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Suspend"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getUnsuspendMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Unsuspend"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getAbortMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Abort"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getClearMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Clear"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getFreeMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Free"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getOccupyMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Occupy"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getPrioMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Prio"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getAutoMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Auto"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getSemiAutoMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.SemiAuto"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getManualMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Manual"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}

	@Override
	public CompletableFuture<NodeId> getSimulateMethodNodeId() {
		return getComponent(new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Simulate"))).thenApply(UaNode.class::cast).thenCompose(UaNode::getNodeId);
	}



}
