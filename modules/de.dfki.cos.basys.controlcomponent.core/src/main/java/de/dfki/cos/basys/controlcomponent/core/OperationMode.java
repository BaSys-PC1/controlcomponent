package de.dfki.cos.basys.controlcomponent.core;

import java.util.List;

import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;

public interface OperationMode extends PackMLActiveStatesHandler {

	String getName();
	
	OperationModeInfo getInfo();

	ParameterInfo getParameter(String name);
	List<ParameterInfo> getParameters();
	List<ParameterInfo> getParameters(ParameterDirection direction);
	List<ParameterInfo> getInputParameters();
	List<ParameterInfo> getOutputParameters();
	
	
}
