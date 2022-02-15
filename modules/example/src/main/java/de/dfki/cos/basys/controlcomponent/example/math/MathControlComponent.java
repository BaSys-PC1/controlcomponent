package de.dfki.cos.basys.controlcomponent.example.math;

import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.example.math.opmodes.FibonacciOperationMode;
import de.dfki.cos.basys.controlcomponent.example.math.opmodes.PythagorasOperationMode;
import de.dfki.cos.basys.controlcomponent.example.math.service.CalculationService;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

import java.util.Properties;

public class MathControlComponent extends BaseControlComponent<CalculationService> {

	public MathControlComponent(Properties config) {
		super(config);
	}
	
	@Override
	protected void registerOperationModes() {		
		OperationMode opMode1 = new PythagorasOperationMode(this);
		registerOperationMode(opMode1);			
		OperationMode opMode2 = new FibonacciOperationMode(this);
		registerOperationMode(opMode2);		
	}
	
}
