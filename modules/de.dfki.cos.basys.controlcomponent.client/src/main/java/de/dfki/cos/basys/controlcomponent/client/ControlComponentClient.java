package de.dfki.cos.basys.controlcomponent.client;

import de.dfki.cos.basys.controlcomponent.CommandInterface;
import de.dfki.cos.basys.controlcomponent.SharedParameterSpace;
import de.dfki.cos.basys.controlcomponent.StatusInterface;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;

public interface ControlComponentClient extends StatusInterface, CommandInterface, SharedParameterSpace {
	
}
