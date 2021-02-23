package de.dfki.cos.basys.controlcomponent.server.opcua.nodes;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentNamespace;
import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentStatusType;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentStatusNode extends BaseDataVariableTypeNode implements ControlComponentStatusType {

	public ControlComponentStatusNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
	}

	public ControlComponentStatusNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask,
			DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel,
			UByte userAccessLevel, double minimumSamplingInterval, boolean historizing) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType,
				valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
	}

	@Override
	public Integer getErrorCode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorCode"));
		return component.map(node -> (Integer) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getErrorCodeNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorCode"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getErrorMessage() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorMessage"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getErrorMessageNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorMessage"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getExecutionMode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionMode"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getExecutionModeNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionMode"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getExecutionState() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionState"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getExecutionStateNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionState"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getOccupationState() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupationState"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getOccupationStateNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupationState"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getOccupierId() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupierId"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getOccupierIdNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupierId"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getOperationMode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OperationMode"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getOperationModeNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OperationMode"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}

	@Override
	public String getWorkState() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.WorkState"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableTypeNode getWorkStateNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.WorkState"));
		return (BaseDataVariableTypeNode) component.orElse(null);
	}
}
