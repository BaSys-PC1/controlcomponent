package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusType;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentStatusNode extends UaVariableNode implements ControlComponentStatusType {

	public ControlComponentStatusNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask,
			DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel,
			UByte userAccessLevel, Double minimumSamplingInterval, Boolean historizing) {
		super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value,
				dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval,
				historizing);

	}

	@Override
	public Integer getErrorCode() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.ErrorCode"));
		try {
			return (Integer) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getErrorMessage() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.ErrorMessage"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getExecutionMode() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.ExecutionMode"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getExecutionState() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.ExecutionState"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UaVariableNode getExecutionStateNode() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.ExecutionState"));
		return node;
	}
	
	@Override
	public String getOccupationState() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.OccupationState"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getOccupierId() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.OccupierId"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getOperationMode() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.OperationMode"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getWorkState() {
		UaVariableNode node = getVariableOrNull(Strings.getString("ControlComponent.BN.WorkState"));
		try {
			return (String) node.readValue().getValue().getValue();
		} catch (UaException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UaVariableNode getVariableOrNull(String name) {
		try {
			return getVariableComponent(name);
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
