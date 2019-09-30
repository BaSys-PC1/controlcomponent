package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;

@OperationMode(description = "the default empty operation mode", name = "default", shortName = "BSTATE")
public class DefaultOperationMode extends BaseOperationMode {

	public DefaultOperationMode(BaseControlComponent component) {
		super(component);
		// TODO Auto-generated constructor stub
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
	public void onStopping() {
		// TODO Auto-generated method stub

	}

}
