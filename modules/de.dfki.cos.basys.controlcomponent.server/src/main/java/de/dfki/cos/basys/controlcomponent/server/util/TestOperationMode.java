package de.dfki.cos.basys.controlcomponent.server.util;

import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import de.dfki.cos.basys.controlcomponent.impl.BaseOperationMode;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.VariableAccess;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;

@OperationMode(description = "a desc", name = "testmode", shortName = "TSTMD", allowedCommands = {
		ExecutionCommand.ABORT, ExecutionCommand.CLEAR, ExecutionCommand.HOLD, ExecutionCommand.RESET,
		ExecutionCommand.START, ExecutionCommand.STOP, ExecutionCommand.SUSPEND, ExecutionCommand.UNHOLD,
		ExecutionCommand.UNSUSPEND })
public class TestOperationMode extends BaseOperationMode {

	@Parameter(name = "input", access = VariableAccess.WRITE_ONLY)
	public String inputStringParameter = "in";

	@Parameter(name = "output", access = VariableAccess.READ_ONLY)
	private String outputStringParameter = "out";

	@Parameter(name = "inout", access = VariableAccess.READ_WRITE)
	protected String inoutStringParameter = "inout";

	public TestOperationMode(BaseControlComponent component) {
		super(component);
	}

	public String getInputStringParameter() {
		return inputStringParameter;
	}

	public String getOutputStringParameter() {
		return outputStringParameter;
	}

	public String getInoutStringParameter() {
		return inoutStringParameter;
	}

	public void setInputStringParameter(String inputStringParameter) {
		this.inputStringParameter = inputStringParameter;
	}

	public void setOutputStringParameter(String outputStringParameter) {
		this.outputStringParameter = outputStringParameter;
	}

	public void setInoutStringParameter(String inoutStringParameter) {
		this.inoutStringParameter = inoutStringParameter;
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
		sleep(1000);
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
