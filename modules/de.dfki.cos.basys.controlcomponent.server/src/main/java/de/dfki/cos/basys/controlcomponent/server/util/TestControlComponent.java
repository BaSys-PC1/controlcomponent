package de.dfki.cos.basys.controlcomponent.server.util;

import java.util.Properties;

import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class TestControlComponent extends BaseControlComponent {

	public TestControlComponent(Properties config) {
		super(config);
	}
	
	@Override
	protected void registerOperationModes() {	
		OperationMode testOpMode = new TestOperationMode(this);
		operationModes.put(testOpMode.getName(), testOpMode);
	}
	
}
