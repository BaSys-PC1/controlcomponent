package de.dfki.cos.basys.controlcomponent.server.opcua.nodes;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableNode;
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

public class ControlComponentStatusNode extends BaseDataVariableNode implements ControlComponentStatusType {

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
	public BaseDataVariableNode getErrorCodeNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorCode"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getErrorMessage() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorMessage"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getErrorMessageNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ErrorMessage"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getExecutionMode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionMode"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getExecutionModeNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionMode"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getExecutionState() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionState"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getExecutionStateNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.ExecutionState"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getOccupationState() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupationState"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getOccupationStateNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupationState"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getOccupierId() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupierId"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getOccupierIdNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OccupierId"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getOperationMode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OperationMode"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getOperationModeNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.OperationMode"));
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getWorkState() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.WorkState"));
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getWorkStateNode() {
		Optional<VariableNode> component = getVariableComponent(Strings.getString("ControlComponent.BN.WorkState"));
		return (BaseDataVariableNode) component.orElse(null);
	}
}
