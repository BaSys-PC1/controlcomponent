package de.dfki.cos.basys.controlcomponent.example.math.opmodes;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.example.math.service.CalculationService;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import de.dfki.cos.basys.controlcomponent.impl.BaseOperationMode;

import java.util.concurrent.TimeUnit;

@OperationMode(name = "pythargoras", shortName = "PYT", description = "this operation mode calculates "
		+ "the length of the hypothenuse 'c' in a right triangle given sides 'a' and 'b' according to Pythagoras", 
		allowedCommands = {	ExecutionCommand.RESET, ExecutionCommand.START, ExecutionCommand.STOP }, 
		allowedModes = { ExecutionMode.PRODUCTION, ExecutionMode.SIMULATE })
public class PythagorasOperationMode extends BaseOperationMode<CalculationService> {

	@Parameter(name = "pyt_a", direction = ParameterDirection.IN)
	private double a = 0;
	
	@Parameter(name = "pyt_b", direction = ParameterDirection.IN)
	private double b = 0;

	@Parameter(name = "pyt_c", direction = ParameterDirection.OUT)
	private double c = 0;
	
	@Parameter(name = "pyt_duration", direction = ParameterDirection.OUT)
	private int duration = 0;
	
	@Parameter(name = "pyt_calculation", direction = ParameterDirection.OUT)
	private String calcString = "";
	
	long startTime = 0;
	long endTime = 0;
	
	
	public PythagorasOperationMode(BaseControlComponent<CalculationService> component) {
		super(component);
	}

	@Override
	public void onResetting() {
		a = 0;
		b = 0;
		c = 0;
		calcString = "";
		duration = 0;
		startTime = 0;
		endTime = 0;	
	}

	@Override
	public void onStarting() {		
		startTime = System.currentTimeMillis();		
		sleep(1000);
	}

	@Override
	public void onExecute() {
		c = getService(CalculationService.class).calculateHypothenuseLength(a, b);
		calcString = "c² = a² + b² with a=" + a + ", b=" + b + ", and c=" + c;
		sleep(1000);
	}

	@Override
	public void onCompleting() {
		endTime = System.currentTimeMillis();
		duration = (int) (endTime - startTime);
		sleep(1000);
	}

	@Override
	public void onStopping() {
		endTime = System.currentTimeMillis();
		duration = (int) (endTime - startTime);
		sleep(1000);
	}

	public void sleep(long millis) {
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
