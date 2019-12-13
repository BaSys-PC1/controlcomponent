package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.controlcomponent.packml.PackMLCommandInterface;

public interface CommandInterface extends PackMLCommandInterface {

	//ComponentOrderStatus executeOrder(ComponentOrder order);
	
	ComponentOrderStatus free(String occupierId);
	ComponentOrderStatus occupy(String occupierId);
	ComponentOrderStatus occupyPriority(String occupierId);
	ComponentOrderStatus occupyLocal(String occupierId);
	ComponentOrderStatus occupy(OccupationLevel level, String occupierId);

	ComponentOrderStatus setOperationMode(String opModeName, String occupierId);
	ComponentOrderStatus registerOperationMode(OperationMode opMode, String occupierId);
	ComponentOrderStatus unregisterOperationMode(String opModeName, String occupierId);
	
}
