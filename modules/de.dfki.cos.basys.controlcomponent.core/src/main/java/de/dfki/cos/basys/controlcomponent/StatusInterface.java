package de.dfki.cos.basys.controlcomponent;

import java.util.List;

import de.dfki.cos.basys.controlcomponent.packml.PackMLStatusInterface;

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
