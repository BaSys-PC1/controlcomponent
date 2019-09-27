package de.dfki.cos.basys.controlcomponent.server.util;

import de.dfki.cos.basys.common.component.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class TestControlComponent extends BaseControlComponent {

	public TestControlComponent(ComponentConfiguration config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void registerOperationModes() {	
		super.registerOperationModes();
		OperationMode testOpMode = new TestOperationMode();
		operationModes.put(testOpMode.getName(), testOpMode);
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
