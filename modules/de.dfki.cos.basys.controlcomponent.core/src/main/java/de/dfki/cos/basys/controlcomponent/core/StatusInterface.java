package de.dfki.cos.basys.controlcomponent.core;

import java.util.List;

import de.dfki.cos.basys.controlcomponent.ComponentInfo;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.packml.PackMLStatusInterface;

public interface StatusInterface extends PackMLStatusInterface {

	OccupationStatus getOccupationStatus();
	OccupationLevel getOccupationLevel();
	String getOccupierId();	

	List<OperationModeInfo> getOperationModes();	
	OperationModeInfo getOperationMode();	
	String getWorkState();
	ErrorStatus getErrorStatus();
	String getErrorMessage();
	int getErrorCode();
	
	ComponentInfo getInfo();
	
}
