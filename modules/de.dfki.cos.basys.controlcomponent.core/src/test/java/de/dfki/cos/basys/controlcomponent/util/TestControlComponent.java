package de.dfki.cos.basys.controlcomponent.util;

import de.dfki.cos.basys.common.component.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class TestControlComponent extends BaseControlComponent {

	public TestControlComponent(ComponentConfiguration config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void connectToExternal() {
		LOGGER.info("CONNECTED");
	}

	@Override
	public void disconnectFromExternal() {
		LOGGER.info("DISCONNECTED");
	}

}
