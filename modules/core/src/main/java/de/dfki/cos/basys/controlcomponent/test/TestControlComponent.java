package de.dfki.cos.basys.controlcomponent.test;

import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.config.ControlComponentConfig;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

import java.util.Properties;

public class TestControlComponent extends BaseControlComponent<TestService> {

	public TestControlComponent(ControlComponentConfig config) {
		super(config);
	}
	
	@Override
	protected void registerOperationModes() {
		boolean registerOperationModes = Boolean.parseBoolean(config.getProperties().getOrDefault("testRegisterOperationModes", "false"));
		if (registerOperationModes) {
			OperationMode testOpMode = new TestOperationMode(this);
			registerOperationMode(testOpMode);
			OperationMode moveOpMode = new MoveOperationMode(this);
			registerOperationMode(moveOpMode);
		}
	}
	
}
