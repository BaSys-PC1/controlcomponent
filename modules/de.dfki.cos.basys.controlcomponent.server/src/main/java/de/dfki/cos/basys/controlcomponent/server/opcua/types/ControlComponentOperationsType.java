package de.dfki.cos.basys.controlcomponent.server.opcua.types;

import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseObjectType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;

public interface ControlComponentOperationsType extends BaseObjectType {

	UaMethodNode getResetMethodNode();
	UaMethodNode getStartMethodNode();
	UaMethodNode getStopMethodNode();
	UaMethodNode getHoldMethodNode();
	UaMethodNode getUnholdMethodNode();
	UaMethodNode getSuspendMethodNode();
	UaMethodNode getUnsuspendMethodNode();
	UaMethodNode getAbortMethodNode();
	UaMethodNode getClearMethodNode();
	
	UaMethodNode getFreeMethodNode();
	UaMethodNode getOccupyMethodNode();
	UaMethodNode getPrioMethodNode();
	
	UaMethodNode getAutoMethodNode();
	UaMethodNode getSemiAutoMethodNode();
	UaMethodNode getManualMethodNode();
	UaMethodNode getSimulateMethodNode();	
}
