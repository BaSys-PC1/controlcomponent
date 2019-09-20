package de.dfki.cos.basys.controlcomponent.core;

import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.core.impl.BaseOperationMode;

@OperationMode(description = "a desc", name = "testmode", shortName = "TSTMD")
public class TestOperationMode extends BaseOperationMode {

	@Parameter(name = "input", direction = ParameterDirection.IN)
	public String inputStringParameter = null;

	@Parameter(name = "output", direction = ParameterDirection.OUT)
	private String outputStringParameter = null;

	@Parameter(name = "inout", direction = ParameterDirection.IN_OUT)
	protected String inoutStringParameter = null;

	public TestOperationMode() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub

	}

	@Override
	public void onStarting() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCompleting() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHolding() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnholding() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuspending() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsuspending() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAborting() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClearing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopping() {
		// TODO Auto-generated method stub

	}

}
