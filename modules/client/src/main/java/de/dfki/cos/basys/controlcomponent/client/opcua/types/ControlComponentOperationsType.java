package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import org.eclipse.milo.opcua.sdk.client.methods.UaMethod;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FolderType;

public interface ControlComponentOperationsType extends FolderType {

	UaMethod getResetMethod();
	UaMethod getStartMethod();
	UaMethod getStopMethod();
	UaMethod getHoldMethod();
	UaMethod getUnholdMethod();
	UaMethod getSuspendMethod();
	UaMethod getUnsuspendMethod();
	UaMethod getAbortMethod();
	UaMethod getClearMethod();
	
	UaMethod getFreeMethod();
	UaMethod getOccupyMethod();
	UaMethod getPrioMethod();
	
	UaMethod getAutoMethod();
	UaMethod getSemiAutoMethod();
	UaMethod getManualMethod();
	UaMethod getSimulateMethod();
	
	UaMethod getOperationModeMethod(String opmode);	
	
}
