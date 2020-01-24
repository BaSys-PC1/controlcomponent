package de.dfki.cos.basys.controlcomponent.server.opcua.types;

import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BaseDataVariableType;

public interface ControlComponentStatusType extends BaseDataVariableType {

	Integer getErrorCode();

	BaseDataVariableNode getErrorCodeNode();

	String getErrorMessage();

	BaseDataVariableNode getErrorMessageNode();

	String getExecutionMode();

	BaseDataVariableNode getExecutionModeNode();

	String getExecutionState();

	BaseDataVariableNode getExecutionStateNode();

	String getOccupationState();

	BaseDataVariableNode getOccupationStateNode();

	String getOccupierId();

	BaseDataVariableNode getOccupierIdNode();

	String getOperationMode();

	BaseDataVariableNode getOperationModeNode();

	String getWorkState();

	BaseDataVariableNode getWorkStateNode();
}
