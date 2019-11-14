package de.dfki.cos.basys.controlcomponent.example;

import java.util.Properties;

import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class ExampleControlComponent extends BaseControlComponent {

	public ExampleControlComponent(Properties config) {
		super(config);
	}
	
	@Override
	protected void registerOperationModes() {		
		OperationMode opMode = new ExampleOperationMode(this);
		registerOperationMode(opMode);		
	}
	
}
