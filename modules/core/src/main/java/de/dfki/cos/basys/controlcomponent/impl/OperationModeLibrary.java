package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OperationMode;

import java.util.HashMap;
import java.util.Map;

public class OperationModeLibrary {

	Map<ExecutionMode,Map<String, OperationMode>> operationModes = new HashMap<>();		
	
	public OperationModeLibrary() {
		for (ExecutionMode mode : ExecutionMode.values()) {
			operationModes.put(mode, new HashMap<String, OperationMode>());
		}
	}
	
	
	public OperationMode getOperationModeForExecutionMode(String opMode, ExecutionMode exMode) {
		Map<String, OperationMode> opmodes = operationModes.get(exMode);
		return opmodes.get(opMode);
	}
	
	public void registerOperationMode(OperationMode opMode) {
		
	}
	
}
