package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.controlcomponent.packml.PackMLStatusInterface;

import java.util.List;

public interface StatusInterface extends PackMLStatusInterface {

	OccupationStatus getOccupationStatus();
	OccupationState getOccupationState();
	String getOccupierId();	

	List<OperationModeInfo> getOperationModes();	
	OperationModeInfo getOperationMode();	
	String getWorkState();
	ErrorStatus getErrorStatus();
	String getErrorMessage();
	int getErrorCode();
	
}
