package de.dfki.cos.basys.controlcomponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.test.TestHandler;
import de.dfki.cos.basys.controlcomponent.test.TestOperationMode;


public class ExecutionStateTest extends BaseTest {

	OperationMode opMode = null;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		opMode = new TestOperationMode(component);
		
		component.occupy(occupier);	
		component.registerOperationMode(opMode, occupier);
		component.reset(occupier);
		recorder.waitForExecutionState(ExecutionState.IDLE, 1000);		
		status = component.setOperationMode(opMode.getName(), occupier);
		recorder.clear();
	}

	@After
	public void tearDown() throws Exception {
		status = component.free(occupier);		
		super.tearDown();
	}

	@Test
	public void testStartComplete() {	
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		assertEquals(opMode.getName(), component.getOperationMode().getName());
		
		component.start(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETE, info.getExecutionState());
	}

	@Test
	public void testHoldExternal() {
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		assertEquals(opMode.getName(), component.getOperationMode().getName());
		
		component.start(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		status = component.hold(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.HOLDING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.HELD, info.getExecutionState());
		component.unhold(occupier);
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.UNHOLDING, info.getExecutionState());
		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETE, info.getExecutionState());
	}

	@Test
	public void testSuspendExternal() {
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		assertEquals(opMode.getName(), component.getOperationMode().getName());
		
		component.start(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		component.suspend(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.SUSPENDING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.SUSPENDED, info.getExecutionState());
		component.unsuspend(occupier);
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.UNSUSPENDING, info.getExecutionState());
		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETE, info.getExecutionState());
	}

	@Test
	public void testStopExternal() {
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		assertEquals(opMode.getName(), component.getOperationMode().getName());
		
		component.start(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		component.stop(occupier);
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STOPPING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STOPPED, info.getExecutionState());
	}
	

	@Test
	public void testAbort() {
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		assertEquals(opMode.getName(), component.getOperationMode().getName());
	
		component.start(occupier);		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		
		component.abort(occupier);
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.ABORTING, info.getExecutionState());
		info = recorder.getLastInfo();
		assertEquals(ExecutionState.ABORTED, component.getExecutionState());		
		component.clear(occupier);
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.CLEARING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STOPPED, info.getExecutionState());	
	
	}
	
/*
	@Test
	public void testComponentLifecycleDefault() throws ComponentException {
		LOGGER.info("testComponentLifecycleDefault - start");
		
		TestDeviceComponent comp = new TestDeviceComponent(config1);
		comp.activate(context);

		assertEquals(ExecutionState.STOPPED, comp.getState(true));
			
		comp.reset();
		assertEquals(ExecutionState.RESETTING, comp.getState(true));
		assertEquals(ExecutionState.IDLE, comp.getState(true));
		
		comp.start();
		assertEquals(ExecutionState.STARTING, comp.getState(true));
		assertEquals(ExecutionState.EXECUTE, comp.getState(true));
		assertEquals(ExecutionState.COMPLETING, comp.getState(true));
		assertEquals(ExecutionState.COMPLETE, comp.getState(true));	
		
		comp.stop();	
		
		assertEquals(ExecutionState.STOPPING, comp.getState(true));
		assertEquals(ExecutionState.STOPPED, comp.getState(true));		
		
		sleep(2);
		comp.deactivate();
		
		LOGGER.info("testComponentLifecycleDefault - complete");
	}
		
	@Test
	public void testComponentLifecycleStopInExecute() throws ComponentException {
		LOGGER.info("testComponentLifecycleStopInExecute - start");
		
		TestDeviceComponent comp = new TestDeviceComponent(config1);
		comp.activate(context);

		assertEquals(ExecutionState.STOPPED, comp.getState(true));
			
		comp.reset();
		assertEquals(ExecutionState.RESETTING, comp.getState(true));
		assertEquals(ExecutionState.IDLE, comp.getState(true));
		
		comp.start();
		assertEquals(ExecutionState.STARTING, comp.getState(true));
		assertEquals(ExecutionState.EXECUTE, comp.getState(true));
		
		comp.stop();
		
//		assertEquals(ExecutionState.COMPLETING, comp.getState(true));
//		assertEquals(ExecutionState.COMPLETE, comp.getState(true));		
//		
//		
//		assertEquals(ExecutionState.RESETTING, comp.getState(true));
		assertEquals(ExecutionState.STOPPING, comp.getState(true));
		assertEquals(ExecutionState.STOPPED, comp.getState(true));		
		
		sleep(2);
		comp.deactivate();
		
		LOGGER.info("testComponentLifecycleStopInExecute - complete");
	}


	@Test
	public void testComponentLifecycleDefaultSim() throws ComponentException {
		LOGGER.info("testComponentLifecycleDefault - start");

		config1.getProperties().add(new PropertyImpl.Builder().key("simulated").value("true").build());
		
		TestDeviceComponent comp = new TestDeviceComponent(config1);
		comp.activate(context);

		assertEquals(ExecutionState.STOPPED, comp.getState(true));
			
		comp.reset();
		assertEquals(ExecutionState.RESETTING, comp.getState(true));
		assertEquals(ExecutionState.IDLE, comp.getState(true));
		
		comp.start();
		assertEquals(ExecutionState.STARTING, comp.getState(true));
		assertEquals(ExecutionState.EXECUTE, comp.getState(true));
		assertEquals(ExecutionState.COMPLETING, comp.getState(true));
		assertEquals(ExecutionState.COMPLETE, comp.getState(true));	
		
		comp.stop();	
		
		assertEquals(ExecutionState.STOPPING, comp.getState(true));
		assertEquals(ExecutionState.STOPPED, comp.getState(true));		
		
		sleep(2);
		comp.deactivate();
		
		LOGGER.info("testComponentLifecycleDefault - complete");
	}
		
	@Test
	public void testComponentLifecycleStopInExecuteSim() throws ComponentException {
		LOGGER.info("testComponentLifecycleStopInExecute - start");

		config1.getProperties().add(new PropertyImpl.Builder().key("simulated").value("true").build());
		
		TestDeviceComponent comp = new TestDeviceComponent(config1);
		comp.activate(context);

		assertEquals(ExecutionState.STOPPED, comp.getState(true));
			
		comp.reset();
		assertEquals(ExecutionState.RESETTING, comp.getState(true));
		assertEquals(ExecutionState.IDLE, comp.getState(true));
		
		comp.start();
		assertEquals(ExecutionState.STARTING, comp.getState(true));
		assertEquals(ExecutionState.EXECUTE, comp.getState(true));
		
		comp.stop();
		
//		assertEquals(ExecutionState.COMPLETING, comp.getState(true));
//		assertEquals(ExecutionState.COMPLETE, comp.getState(true));		
//		
//		
//		assertEquals(ExecutionState.RESETTING, comp.getState(true));
		assertEquals(ExecutionState.STOPPING, comp.getState(true));
		assertEquals(ExecutionState.STOPPED, comp.getState(true));		
		
		sleep(2);
		comp.deactivate();
		
		LOGGER.info("testComponentLifecycleStopInExecute - complete");
	}
	*/
}
