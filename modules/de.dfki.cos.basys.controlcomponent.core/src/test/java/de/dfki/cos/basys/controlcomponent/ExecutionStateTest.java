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

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.test.TestHandler;
import de.dfki.cos.basys.controlcomponent.test.TestOperationMode;


public class ExecutionStateTest extends BaseTest {

	//OperationMode opMode = null;
	
	@Before
	public void setUp() throws Exception {
		LOGGER.info("########################## setUp - start ##########################");
		super.setUp();
		config.put("testRegisterOperationModes","true");

		component.activate(ComponentContext.getStaticContext());
		
		//opMode = new TestOperationMode(component);
		
		component.occupy(occupier);
		//component.registerOperationMode(opMode, occupier);
		component.reset(occupier);
		recorder.waitForExecutionState(ExecutionState.IDLE);		
		status = component.setOperationMode("testmode", occupier);
		recorder.getLastInfo(); // wait for and consume opmode change info
		//recorder.clear();

		LOGGER.info("########################## setUp - finished ##########################");
	}

	@After
	public void tearDown() throws Exception {
		LOGGER.info("########################## tearDown - start ##########################");
		
		status = component.free(occupier);		
		super.tearDown();
		
		LOGGER.info("########################## tearDown - finished ##########################");
	}

	@Test
	public void testStartComplete() {	
		LOGGER.info("########################## testStartComplete - start ##########################");
		
		component.start(occupier);		
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
	public void testHoldExternal() {		
		LOGGER.info("########################## testHoldExternal - start ##########################");
		
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

		LOGGER.info("########################## testHoldExternal - finished ##########################");
	}

	@Test
	public void testSuspendExternal() {
		LOGGER.info("########################## testSuspendExternal - start ##########################");
		
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
		
		LOGGER.info("########################## testSuspendExternal - finished ##########################");
	}

	@Test
	public void testStopExternal() {
		LOGGER.info("########################## testStopExternal - start ##########################");
		
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
		
		LOGGER.info("########################## testStopExternal - finished ##########################");
	}
	

	@Test
	public void testAbort() {
		LOGGER.info("########################## testAbort - start ##########################");
		
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
