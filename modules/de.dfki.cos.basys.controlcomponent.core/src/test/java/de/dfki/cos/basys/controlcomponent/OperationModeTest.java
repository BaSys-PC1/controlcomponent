package de.dfki.cos.basys.controlcomponent;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import de.dfki.cos.basys.controlcomponent.core.ComponentContext;
import de.dfki.cos.basys.controlcomponent.core.ControlComponent;
import de.dfki.cos.basys.controlcomponent.core.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl;
import de.dfki.cos.basys.controlcomponent.util.ComponentInfoRecorder;
import de.dfki.cos.basys.controlcomponent.util.TestControlComponent;
import de.dfki.cos.basys.controlcomponent.util.TestOperationMode;

public class OperationModeTest {
	
	ComponentConfiguration config;
	ControlComponent component;
	ComponentInfoRecorder recorder;
	
	String user_a = "user_a";

	ComponentOrderStatus status = null;
	
	OperationMode opMode = null;
		
	@Before
	public void setUp() throws Exception {
		config = new ComponentConfigurationImpl.Builder().id("some_id").name("test-control-component")
				.externalConnectionString("").implementationJavaClass("").build();
		
		opMode = new TestOperationMode();
		
		component = new TestControlComponent(config);
		component.activate(ComponentContext.getStaticContext());
		status = component.occupy(user_a);		
		
		//start listening now
		recorder = new ComponentInfoRecorder();
	}

	@After
	public void tearDown() throws Exception {
		status = component.free(user_a);
		component.deactivate();
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
		ComponentInfo info = recorder.getLastInfo();
		
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
		ComponentInfo info = recorder.getLastInfo(); // operation mode registered
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
	
	public ComponentInfo print(ComponentInfo info) {
		System.out.println(info);
		return info;
	}
	
}
