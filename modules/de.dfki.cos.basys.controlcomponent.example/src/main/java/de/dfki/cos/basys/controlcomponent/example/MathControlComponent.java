package de.dfki.cos.basys.controlcomponent.example;

import java.util.Properties;

import de.dfki.cos.basys.common.component.impl.ServiceManagerImpl;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class MathControlComponent extends BaseControlComponent {

	public MathControlComponent(Properties config) {
		super(config);		
		serviceManager = new ServiceManagerImpl<CalculationService>(config, CalculationServiceImpl::new); 
	}
	
	@Override
	protected void registerOperationModes() {		
		OperationMode opMode1 = new PythagorasOperationMode(this);
		registerOperationMode(opMode1);			
		OperationMode opMode2 = new FibonacciOperationMode(this);
		registerOperationMode(opMode2);		
	}
	
}
