package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;

public interface ControlComponentStatusType extends BaseDataVariableType {

	Integer getErrorCode();
	String getErrorMessage();
	String getExecutionMode();
	String getExecutionState();
	String getOccupationState();
	String getOccupierId();
	String getOperationMode();
	String getWorkState();
	
	UaVariableNode getExecutionStateNode();

}
