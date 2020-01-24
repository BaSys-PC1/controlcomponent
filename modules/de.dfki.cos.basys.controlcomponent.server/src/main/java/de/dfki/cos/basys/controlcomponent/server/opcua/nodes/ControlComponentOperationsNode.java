package de.dfki.cos.basys.controlcomponent.server.opcua.nodes;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentOperationsType;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentOperationsNode extends FolderNode implements ControlComponentOperationsType {
	
	public ControlComponentOperationsNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
	}
	
	public ControlComponentOperationsNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask,
			UByte eventNotifier) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
	}

	@Override
	public UaMethodNode getResetMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Reset"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getStartMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Start"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getStopMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Stop"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getHoldMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Hold"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getUnholdMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Unhold"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getSuspendMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Suspend"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getUnsuspendMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Unsuspend"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getAbortMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Abort"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getClearMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Clear"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getFreeMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Free"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getOccupyMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Occupy"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getPrioMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Prio"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getAutoMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Auto"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getSemiAutoMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.SemiAuto"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getManualMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Manual"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}

	@Override
	public UaMethodNode getSimulateMethodNode() {
		Optional<UaNode> methodNode = findNode(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Simulate"), node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
	}



}
