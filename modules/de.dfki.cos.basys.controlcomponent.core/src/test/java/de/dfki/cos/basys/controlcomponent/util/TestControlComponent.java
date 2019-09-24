package de.dfki.cos.basys.controlcomponent.util;

import de.dfki.cos.basys.controlcomponent.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.core.ControlComponentException;
import de.dfki.cos.basys.controlcomponent.core.impl.BaseControlComponent;

public class TestControlComponent extends BaseControlComponent {

	public TestControlComponent(ComponentConfiguration config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void connectToExternal() throws ControlComponentException {
		LOGGER.info("CONNECTED");
	}

	@Override
	public void disconnectFromExternal() {
		LOGGER.info("DISCONNECTED");
	}

}
