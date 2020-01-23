package de.dfki.cos.basys.controlcomponent.server.opcua.nodes;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentType;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;

public class ControlComponentNode extends UaObjectNode implements ControlComponentType {
    
	public ControlComponentNode(UaNodeContext context, NodeId nodeId, ObjectTypeNode objectTypeNode) {
		super(context, nodeId, objectTypeNode);
		// TODO Auto-generated constructor stub
	}

	public ControlComponentNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask,
			UByte eventNotifier) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
		// TODO Auto-generated constructor stub
	}

	public ControlComponentNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
		// TODO Auto-generated constructor stub
	}

	public ControlComponentNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName) {
		super(context, nodeId, browseName, displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ControlComponentStatusNode getControlComponentStatusNode() {
		Optional<VariableNode> component = getVariableComponent(NodeIds.NAMESPACE_URI, "STATUS");
		return (ControlComponentStatusNode) component.orElse(null);
	}

	@Override
	public ControlComponentStatusDataType getControlComponentStatus() {
		Optional<VariableNode> component = getVariableComponent("STATUS");
		return component.map(node -> (ControlComponentStatusDataType) node.getValue().getValue().getValue())
				.orElse(null);
	}
}
