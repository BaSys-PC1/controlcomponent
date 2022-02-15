package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;

import java.util.List;

public interface OperationMode extends PackMLActiveStatesHandler {

	String getName();
	String getShortName();
	
	OperationModeInfo getInfo();

	ParameterInfo getParameter(String name);
	
	List<ParameterInfo> getParameters();
	List<ParameterInfo> getParameters(ParameterDirection access);
	List<ParameterInfo> getInputParameters();
	List<ParameterInfo> getOutputParameters();
	List<ExecutionCommand> getExecutionCommands();
	List<ExecutionMode> getExecutionModes();
	
	
}
