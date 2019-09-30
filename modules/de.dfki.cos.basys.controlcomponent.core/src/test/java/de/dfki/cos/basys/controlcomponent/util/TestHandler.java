package de.dfki.cos.basys.controlcomponent.util;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import junit.framework.TestCase;


public class TestHandler implements PackMLActiveStatesHandler {

	protected final Logger LOGGER = LoggerFactory.getLogger(TestHandler.class.getName());
		
	enum Path {NORMAL, SUSPEND, HOLD}
	
	public Path path = Path.NORMAL;
	
	PackMLUnit unit;
	
	public TestHandler(PackMLUnit unit) {
		this.unit = unit;
	}

	public void initialize() {
		unit.initialize();
	}

	public void dispose() {
		unit.dispose();
	}
	
	public void sleep(double seconds) {
		try {
			TimeUnit.MILLISECONDS.sleep((long)(seconds*100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResetting() {
		LOGGER.info("onResetting");
		TestCase.assertEquals(ExecutionState.RESETTING, unit.getExecutionState());
		sleep(1);
		
	}

	@Override
	public void onStarting() {
		LOGGER.info("onStarting");
		TestCase.assertEquals(ExecutionState.STARTING, unit.getExecutionState());
		sleep(1);
	}

	@Override
	public void onExecute() {
		LOGGER.info("onExecute");
		TestCase.assertEquals(ExecutionState.EXECUTE, unit.getExecutionState());
		sleep(1);
		
		switch (path) {
		case HOLD:
			unit.hold(null);
			break;
		case SUSPEND:
			unit.suspend(null);
			break;
		case NORMAL:
		default:
			break;
		}
		//sleep(1);
	}

	@Override
	public void onCompleting() {
		LOGGER.info("onCompleting");
		TestCase.assertEquals(ExecutionState.COMPLETING, unit.getExecutionState());
		sleep(1);
	}

	@Override
	public void onHolding() {
		LOGGER.info("onHolding");
		TestCase.assertEquals(ExecutionState.HOLDING, unit.getExecutionState());
		sleep(1);
	}

	@Override
	public void onUnholding() {
		LOGGER.info("onUnholding");
		TestCase.assertEquals(ExecutionState.UNHOLDING, unit.getExecutionState());
		path = Path.NORMAL;
		sleep(1);
	}

	@Override
	public void onSuspending() {
		LOGGER.info("onSuspending");
		TestCase.assertEquals(ExecutionState.SUSPENDING, unit.getExecutionState());
		sleep(1);
	}

	@Override
	public void onUnsuspending() {
		TestCase.assertEquals(ExecutionState.UNSUSPENDING, unit.getExecutionState());
		LOGGER.info("onUnsuspending");
		path = Path.NORMAL;
		sleep(1);
	}

	@Override
	public void onAborting() {
		TestCase.assertEquals(ExecutionState.ABORTING, unit.getExecutionState());
		LOGGER.info("onAborting");
		sleep(1);
	}

	@Override
	public void onClearing() {
		TestCase.assertEquals(ExecutionState.CLEARING, unit.getExecutionState());
		LOGGER.info("onClearing");
		sleep(1);
	}

	@Override
	public void onStopping() {
		TestCase.assertEquals(ExecutionState.STOPPING, unit.getExecutionState());
		LOGGER.info("onStopping");
		sleep(1);
	}

}
