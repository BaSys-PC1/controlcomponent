package de.dfki.cos.basys.controlcomponent.core;

import java.util.List;

import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.packml.PackMLStatusInterface;

public interface StatusInterface extends PackMLStatusInterface {

	OccupationLevel getOccupationLevel();
	String getOccupierId();	

	List<OperationModeInfo> getOperationModes();	
	OperationModeInfo getOperationMode();	
	String getWorkState();
	String getErrorMessage();
	int getErrorCode();
	
}
