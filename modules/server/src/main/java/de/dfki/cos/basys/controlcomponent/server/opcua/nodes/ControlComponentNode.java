package de.dfki.cos.basys.controlcomponent.server.opcua.nodes;

import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentType;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.util.Strings;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import java.util.Optional;

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
		Optional<VariableNode> component = getVariableComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Status"));
		return (ControlComponentStatusNode) component.orElse(null);
	}

	@Override
	public ControlComponentStatusDataType getControlComponentStatus() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.Status"));
		return component.map(node -> (ControlComponentStatusDataType) node.getValue().getValue().getValue())
				.orElse(null);
	}

	@Override
	public ControlComponentOperationsNode getControlComponentOperationsNode() {
		Optional<ObjectNode> component = getObjectComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Operations"));
		return (ControlComponentOperationsNode) component.orElse(null);
	}
	@Override
	public FolderTypeNode getControlComponentVariables() {
		Optional<ObjectNode> component = getObjectComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Variables"));
		return (FolderTypeNode) component.orElse(null);
	}
}
