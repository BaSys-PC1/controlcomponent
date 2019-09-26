package de.dfki.cos.basys.controlcomponent.packml;

import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;

public interface PackMLCommandInterface {

	ComponentOrderStatus setExecutionMode(ExecutionMode mode, String occupierId);
	
	ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String occupierId);

	ComponentOrderStatus reset(String occupierId);

	ComponentOrderStatus start(String occupierId);

	ComponentOrderStatus stop(String occupierId);

	ComponentOrderStatus hold(String occupierId);

	ComponentOrderStatus unhold(String occupierId);

	ComponentOrderStatus suspend(String occupierId);

	ComponentOrderStatus unsuspend(String occupierId);

	ComponentOrderStatus abort(String occupierId);

	ComponentOrderStatus clear(String occupierId);
}
