package de.dfki.cos.basys.controlcomponent.test;

import java.util.Properties;

import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class TestControlComponent extends BaseControlComponent {

	public TestControlComponent(Properties config) {
		super(config);
	}
	
	@Override
	protected void registerOperationModes() {
		boolean registerOperationModes = Boolean.parseBoolean(config.getProperty("testRegisterOperationModes", "false"));
		if (registerOperationModes) {
			OperationMode testOpMode = new TestOperationMode(this);
			operationModes.put(testOpMode.getName(), testOpMode);
		}
	}
	
}
