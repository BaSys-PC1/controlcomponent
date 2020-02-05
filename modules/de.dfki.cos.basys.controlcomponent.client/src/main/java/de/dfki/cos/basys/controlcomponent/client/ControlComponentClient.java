package de.dfki.cos.basys.controlcomponent.client;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;
import de.dfki.cos.basys.controlcomponent.SharedParameterSpace;
import de.dfki.cos.basys.controlcomponent.StatusInterface;

public interface ControlComponentClient extends StatusInterface, SharedParameterSpace {
	
	ComponentOrderStatus free();
	ComponentOrderStatus occupy();
	ComponentOrderStatus occupyPriority();
	ComponentOrderStatus occupy(OccupationCommand cmd);

	ComponentOrderStatus setOperationMode(String opModeName);
	
	ComponentOrderStatus setExecutionMode(ExecutionMode mode);	
	
	ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command);

	ComponentOrderStatus reset();

	ComponentOrderStatus start();

	ComponentOrderStatus stop();

	ComponentOrderStatus hold();

	ComponentOrderStatus unhold();

	ComponentOrderStatus suspend();

	ComponentOrderStatus unsuspend();

	ComponentOrderStatus abort();

	ComponentOrderStatus clear();
	
}
