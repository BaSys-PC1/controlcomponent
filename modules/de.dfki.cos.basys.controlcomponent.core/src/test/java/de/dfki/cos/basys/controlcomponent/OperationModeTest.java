package de.dfki.cos.basys.controlcomponent;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.common.component.OrderStatus;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.util.ControlComponentInfoRecorder;
import de.dfki.cos.basys.controlcomponent.util.TestControlComponent;
import de.dfki.cos.basys.controlcomponent.util.TestOperationMode;

public class OperationModeTest extends BaseTest {
		
	OperationMode opMode = null;
		
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		opMode = new TestOperationMode(component);
		status = component.occupy(user_a);		
		recorder.clear();
	}

	@After
	public void tearDown() throws Exception {
		status = component.free(user_a);
		
		super.tearDown();
	}

	@Test
	public void testParameters() {
		OperationModeInfo info = opMode.getInfo();
		
		List<ParameterInfo> parameters = opMode.getParameters();
		List<ParameterInfo> inputParameters = opMode.getInputParameters();		
		List<ParameterInfo> outputParameters = opMode.getOutputParameters();
		
	}

	@Test
	public void testRegistration() {		
		List<OperationModeInfo> opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());
		
		ComponentOrderStatus status = component.registerOperationMode(opMode, user_a);
		assertEquals(OrderStatus.ACCEPTED, print(status).getStatus());
		ControlComponentInfo info = recorder.getLastInfo();
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 2);
		
		status = component.unregisterOperationMode(opMode.getName(), user_a);
		assertEquals(OrderStatus.ACCEPTED, print(status).getStatus());
		info = recorder.getLastInfo();
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());		
	}

	@Test
	public void testRegistrationAndSet() {
		List<OperationModeInfo> opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());
		
		ComponentOrderStatus status = component.registerOperationMode(opMode, user_a);
		assertEquals(OrderStatus.ACCEPTED, print(status).getStatus());
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
		assertEquals(ExecutionState.IDLE, info.getExecutionState());
		
		status = component.setOperationMode(opMode.getName(), user_a);
		assertEquals(OrderStatus.ACCEPTED, print(status).getStatus());
		info = recorder.getLastInfo(); // operation mode change
		print(info);
		assertEquals(opMode.getName(), info.getOperationMode());
		assertEquals(opMode.getName(), component.getOperationMode().getName());
		
		status = component.unregisterOperationMode(opMode.getName(), user_a);
		assertEquals(OrderStatus.ACCEPTED, print(status).getStatus());
		info = recorder.getLastInfo(); // operation mode unregistered
		print(info);
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());			
	}
	
	public ComponentOrderStatus print(ComponentOrderStatus status) {
		System.out.println(status.getStatus() + " : " + status.getMessage());
		return status;
	}
	
	public ControlComponentInfo print(ControlComponentInfo info) {
		System.out.println(info);
		return info;
	}
	
}
