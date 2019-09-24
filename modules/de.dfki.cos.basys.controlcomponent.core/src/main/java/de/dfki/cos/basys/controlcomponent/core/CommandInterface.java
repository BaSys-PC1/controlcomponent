package de.dfki.cos.basys.controlcomponent.core;

import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.packml.PackMLCommandInterface;
import de.dfki.cos.basys.controlcomponent.ComponentOrder;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;

public interface CommandInterface extends PackMLCommandInterface {

	ComponentOrderStatus executeOrder(ComponentOrder order);
	
	ComponentOrderStatus free(String occupierId);
	ComponentOrderStatus occupy(String occupierId);
	ComponentOrderStatus occupyPriority(String occupierId);
	ComponentOrderStatus occupyLocal(String occupierId);
	ComponentOrderStatus occupy(OccupationLevel level, String occupierId);

	ComponentOrderStatus setOperationMode(String opModeName, String occupierId);
	ComponentOrderStatus registerOperationMode(OperationMode opMode, String occupierId);
	ComponentOrderStatus unregisterOperationMode(String opModeName, String occupierId);
}
