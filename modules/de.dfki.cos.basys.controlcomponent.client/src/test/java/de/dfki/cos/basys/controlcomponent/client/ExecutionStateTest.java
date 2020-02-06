package de.dfki.cos.basys.controlcomponent.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.test.TestOperationMode;


public class ExecutionStateTest extends BaseTest {

	//OperationMode opMode = null;
	
	@Before
	public void setUp() throws Exception {
		LOGGER.info("########################## setUp - start ##########################");
		super.setUp();	
		
		component.occupy();
		
		component.reset();
		recorder.waitForExecutionState(ExecutionState.IDLE);		
		status = component.setOperationMode("TSTMD");
		//recorder.getLastInfo(); // wait for and consume opmode change info
		//recorder.clear();

		LOGGER.info("########################## setUp - finished ##########################");
	}

	@After
	public void tearDown() throws Exception {
		LOGGER.info("########################## tearDown - start ##########################");
		
		status = component.free();		
		super.tearDown();
		
		LOGGER.info("########################## tearDown - finished ##########################");
	}

	@Test
	@Ignore
	public void testStartComplete() {	
		LOGGER.info("########################## testStartComplete - start ##########################");
		
		component.start();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETE, info.getExecutionState());

		LOGGER.info("########################## testStartComplete - finished ##########################");
	}

	@Test
	@Ignore
	public void testHoldExternal() {		
		LOGGER.info("########################## testHoldExternal - start ##########################");
		
		component.start();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		status = component.hold();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.HOLDING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.HELD, info.getExecutionState());
		component.unhold();
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.UNHOLDING, info.getExecutionState());
		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETE, info.getExecutionState());

		LOGGER.info("########################## testHoldExternal - finished ##########################");
	}

	@Test
	@Ignore
	public void testSuspendExternal() {
		LOGGER.info("########################## testSuspendExternal - start ##########################");
		
		component.start();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		component.suspend();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.SUSPENDING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.SUSPENDED, info.getExecutionState());
		component.unsuspend();
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.UNSUSPENDING, info.getExecutionState());
		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.COMPLETE, info.getExecutionState());
		
		LOGGER.info("########################## testSuspendExternal - finished ##########################");
	}

	@Test
	@Ignore
	public void testStopExternal() {
		LOGGER.info("########################## testStopExternal - start ##########################");
		
		component.start();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		component.stop();
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STOPPING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STOPPED, info.getExecutionState());
		
		LOGGER.info("########################## testStopExternal - finished ##########################");
	}
	

	@Test
	@Ignore
	public void testAbort() {
		LOGGER.info("########################## testAbort - start ##########################");
		
		component.start();		
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STARTING, info.getExecutionState());				
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.EXECUTE, info.getExecutionState());
		
		
		component.abort();
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.ABORTING, info.getExecutionState());
		info = recorder.getLastInfo();
		assertEquals(ExecutionState.ABORTED, component.getExecutionState());		
		component.clear();
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.CLEARING, info.getExecutionState());
		info = recorder.getLastInfo();		
		assertEquals(ExecutionState.STOPPED, info.getExecutionState());	
		
		LOGGER.info("########################## testAbort - finished ##########################");
	
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
