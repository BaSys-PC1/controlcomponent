package de.dfki.cos.basys.controlcomponent.test;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import de.dfki.cos.basys.controlcomponent.impl.BaseOperationMode;

import java.util.concurrent.TimeUnit;

@OperationMode(description = "a desc", name = "testmode", shortName = "TSTMD", allowedCommands = {
		ExecutionCommand.ABORT, ExecutionCommand.CLEAR, ExecutionCommand.HOLD, ExecutionCommand.RESET,
		ExecutionCommand.START, ExecutionCommand.STOP, ExecutionCommand.SUSPEND, ExecutionCommand.UNHOLD,
		ExecutionCommand.UNSUSPEND })
public class TestOperationMode extends BaseOperationMode<Void> {

	@Parameter(name = "in", direction = ParameterDirection.IN)
	public String inputStringParameter = "inputString";

	@Parameter(name = "out", direction = ParameterDirection.OUT)
	private String outputStringParameter = "outputString";


	public TestOperationMode(BaseControlComponent<Void> component) {
		super(component);
	}

	public String getInputStringParameter() {
		return inputStringParameter;
	}

	public String getOutputStringParameter() {
		return outputStringParameter;
	}

	public void setInputStringParameter(String inputStringParameter) {
		this.inputStringParameter = inputStringParameter;
	}

	public void setOutputStringParameter(String outputStringParameter) {
		this.outputStringParameter = outputStringParameter;
	}

	@Override
	public void onResetting() {
		sleep(1000);
	}

	@Override
	public void onStarting() {
		sleep(1000);
	}

	@Override
	public void onExecute() {
		sleep(5000);
	}

	@Override
	public void onCompleting() {
		sleep(1000);
	}

	@Override
	public void onHolding() {
		sleep(1000);
	}

	@Override
	public void onUnholding() {
		sleep(1000);
	}

	@Override
	public void onSuspending() {
		sleep(1000);
	}

	@Override
	public void onUnsuspending() {
		sleep(1000);
	}

	@Override
	public void onAborting() {
		sleep(1000);
	}

	@Override
	public void onClearing() {
		sleep(1000);
	}

	@Override
	public void onStopping() {
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
