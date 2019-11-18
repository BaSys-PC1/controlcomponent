package de.dfki.cos.basys.controlcomponent.example;

import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import de.dfki.cos.basys.controlcomponent.impl.BaseOperationMode;

import java.util.concurrent.TimeUnit;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.VariableAccess;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;

@OperationMode(name = "fibonacci", shortName = "FIB", description = "given a number n, "
		+ "this operation mode calculates the n-th fibonacci number", allowedCommands = { ExecutionCommand.RESET,
				ExecutionCommand.START,
				ExecutionCommand.STOP }, allowedModes = { ExecutionMode.PRODUCTION, ExecutionMode.SIMULATION })
public class FibonacciOperationMode extends BaseOperationMode {

	@Parameter(name = "fib_n", access = VariableAccess.READ_WRITE)
	private int n = 0;

	@Parameter(name = "fib_result", access = VariableAccess.READ_ONLY)
	private long result = 0;

	@Parameter(name = "fib_duration", access = VariableAccess.READ_ONLY)
	private int duration = 0;

	@Parameter(name = "fib_calculation", access = VariableAccess.READ_ONLY)
	private String calcString = "";

	long startTime = 0;
	long endTime = 0;

	public FibonacciOperationMode(BaseControlComponent component) {
		super(component);
	}

	@Override
	public void onResetting() {
		n = 0;
		result = 0;
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
		result = getService(CalculationService.class).calculateFibonacci(n);
		calcString = "fib("+ n + ") = " + result;
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
