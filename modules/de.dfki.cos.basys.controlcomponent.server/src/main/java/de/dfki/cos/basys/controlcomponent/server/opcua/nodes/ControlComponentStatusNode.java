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
		Optional<VariableNode> component = getVariableComponent("ERRCODE");
		return component.map(node -> (Integer) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getErrorCodeNode() {
		Optional<VariableNode> component = getVariableComponent("ERRCODE");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getErrorMessage() {
		Optional<VariableNode> component = getVariableComponent("ERRMSG");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getErrorMessageNode() {
		Optional<VariableNode> component = getVariableComponent("ERRMSG");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getExecutionMode() {
		Optional<VariableNode> component = getVariableComponent("EXMODE");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getExecutionModeNode() {
		Optional<VariableNode> component = getVariableComponent("EXMODE");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getExecutionState() {
		Optional<VariableNode> component = getVariableComponent("EXST");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getExecutionStateNode() {
		Optional<VariableNode> component = getVariableComponent("EXST");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getOccupationState() {
		Optional<VariableNode> component = getVariableComponent("OCCST");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getOccupationStateNode() {
		Optional<VariableNode> component = getVariableComponent("OCCST");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getOccupierId() {
		Optional<VariableNode> component = getVariableComponent("OCCUPIER");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getOccupierIdNode() {
		Optional<VariableNode> component = getVariableComponent("OCCUPIER");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getOperationMode() {
		Optional<VariableNode> component = getVariableComponent("OPMODE");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getOperationModeNode() {
		Optional<VariableNode> component = getVariableComponent("OPMODE");
		return (BaseDataVariableNode) component.orElse(null);
	}

	@Override
	public String getWorkState() {
		Optional<VariableNode> component = getVariableComponent("WORKST");
		return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
	}

	@Override
	public BaseDataVariableNode getWorkStateNode() {
		Optional<VariableNode> component = getVariableComponent("WORKST");
		return (BaseDataVariableNode) component.orElse(null);
	}
}
