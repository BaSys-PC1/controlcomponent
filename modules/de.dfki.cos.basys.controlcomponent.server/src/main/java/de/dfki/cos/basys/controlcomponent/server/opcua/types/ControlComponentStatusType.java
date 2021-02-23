package de.dfki.cos.basys.controlcomponent.server.opcua.types;

import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BaseDataVariableType;

public interface ControlComponentStatusType extends BaseDataVariableType {

	Integer getErrorCode();

	BaseDataVariableTypeNode getErrorCodeNode();

	String getErrorMessage();

	BaseDataVariableTypeNode getErrorMessageNode();

	String getExecutionMode();

	BaseDataVariableTypeNode getExecutionModeNode();

	String getExecutionState();

	BaseDataVariableTypeNode getExecutionStateNode();

	String getOccupationState();

	BaseDataVariableTypeNode getOccupationStateNode();

	String getOccupierId();

	BaseDataVariableTypeNode getOccupierIdNode();

	String getOperationMode();

	BaseDataVariableTypeNode getOperationModeNode();

	String getWorkState();

	BaseDataVariableTypeNode getWorkStateNode();
}
