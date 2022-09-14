package de.dfki.cos.basys.controlcomponent.packml;

import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;

public interface PackMLStatusInterface {

	//String getId();

	ExecutionState getExecutionState();

	ExecutionMode getExecutionMode();

	//UnitConfiguration getUnitConfig();
}
