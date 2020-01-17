package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.controlcomponent.packml.PackMLCommandInterface;

public interface CommandInterface extends PackMLCommandInterface {

	//ComponentOrderStatus executeOrder(ComponentOrder order);
	
	ComponentOrderStatus free(String senderId);
	ComponentOrderStatus occupy(String senderId);
	ComponentOrderStatus occupyPriority(String senderId);
	ComponentOrderStatus occupy(OccupationCommand cmd, String senderId);

	ComponentOrderStatus setOperationMode(String opModeName, String senderId);
	ComponentOrderStatus registerOperationMode(OperationMode opMode, String senderId);
	ComponentOrderStatus unregisterOperationMode(String opModeName, String senderId);
	
}
