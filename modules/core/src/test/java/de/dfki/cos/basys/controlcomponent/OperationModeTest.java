package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.test.TestOperationMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OperationModeTest extends BaseTest {
		
	OperationMode opMode = null;
		
	@Before
	public void setUp() throws Exception {
		LOGGER.info("########################## setUp - start ##########################");
		super.setUp();

		component.activate(ComponentContext.getStaticContext());
		
		opMode = new TestOperationMode(component);

		recorder.clear();
		
		status = component.occupy(user_a);	
		assertEquals(OrderStatus.DONE, print(status).getStatus());
		ControlComponentInfo info = recorder.getLastInfo(); // occupied
		print(info);
		recorder.clear();
		LOGGER.info("########################## setUp - finished ##########################");
	}

	@After
	public void tearDown() throws Exception {
		LOGGER.info("########################## tearDown - start ##########################");
		status = component.free(user_a);
		
		super.tearDown();
		LOGGER.info("########################## tearDown - finished ##########################");
	}

	@Test
	@Ignore
	public void testParameters() {
		LOGGER.info("########################## tearDown - start ##########################");
		OperationModeInfo info = opMode.getInfo();
		
		List<ParameterInfo> parameters = opMode.getParameters();
		List<ParameterInfo> inputParameters = opMode.getInputParameters();		
		List<ParameterInfo> outputParameters = opMode.getOutputParameters();
		
	}

	@Test
	public void testRegistration() {		
		LOGGER.info("########################## testRegistration - start ##########################");
		List<OperationModeInfo> opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());
		
		ComponentOrderStatus status = component.registerOperationMode(opMode, user_a);
		assertEquals(OrderStatus.DONE, print(status).getStatus());
		ControlComponentInfo info = recorder.getLastInfo();
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 2);
		
		status = component.unregisterOperationMode(opMode.getShortName(), user_a);
		assertEquals(OrderStatus.DONE, print(status).getStatus());
		info = recorder.getLastInfo();
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());
		assertEquals("BSTATE", opModes.get(0).getShortName());		
		LOGGER.info("########################## testRegistration - finished ##########################");
	}

	@Test
	public void testRegistrationAndSet() {
		LOGGER.info("########################## testRegistrationAndSet - start ##########################");
		List<OperationModeInfo> opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("BSTATE", opModes.get(0).getShortName());
		
		ComponentOrderStatus status = component.registerOperationMode(opMode, user_a);
		assertEquals(OrderStatus.DONE, print(status).getStatus());
		ControlComponentInfo info = recorder.getLastInfo(); // operation mode registered
		print(info);
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 2);
		
		status = component.reset(user_a);
		
		assertEquals(OrderStatus.ACCEPTED, print(status).getStatus());
		info = recorder.getLastInfo(); //state change into RESETTING
		print(info);
		assertEquals(ExecutionState.RESETTING, info.getExecutionState());
		info = recorder.getLastInfo(); //error status reset inside RESETTING
		print(info);
		info = recorder.getLastInfo(); //state change into IDLE
		print(info);
		//assertEquals(ExecutionState.IDLE, info.getExecutionState());
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		status = component.setOperationMode(opMode.getShortName(), user_a);
		assertEquals(OrderStatus.DONE, print(status).getStatus());
		info = recorder.getLastInfo(); // operation mode change
		print(info);
		assertEquals(opMode.getShortName(), info.getOperationMode());
		assertEquals(opMode.getShortName(), component.getOperationMode().getShortName());
		
		status = component.unregisterOperationMode(opMode.getShortName(), user_a);
		assertEquals(OrderStatus.DONE, print(status).getStatus());
		info = recorder.getLastInfo(); // operation mode unregistered
		print(info);
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());
		assertEquals("BSTATE", opModes.get(0).getShortName());		
		LOGGER.info("########################## testRegistrationAndSet - finished ##########################");
	}
	
}
