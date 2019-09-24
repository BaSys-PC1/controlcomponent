package de.dfki.cos.basys.controlcomponent;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dfki.cos.basys.controlcomponent.core.ControlComponent;
import de.dfki.cos.basys.controlcomponent.core.OperationMode;
import de.dfki.cos.basys.controlcomponent.core.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl;

public class OperationModeTest {
	
	ComponentConfiguration config;
	ControlComponent component;
	
	String user_a = "user_a";

	ComponentOrderStatus status = null;
	
	OperationMode opMode = null;
	
	@Before
	public void setUp() throws Exception {
		config = new ComponentConfigurationImpl.Builder().id("some_id").name("test-control-component")
				.externalConnectionString("").implementationJavaClass("").build();

		opMode = new TestOperationMode();
		
		component = new TestControlComponent(config);
		component.activate();
		status = component.occupy(user_a);
		
	
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
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 2);
		
		status = component.unregisterOperationMode(opMode.getName(), user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());

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
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		
		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 2);
		
		status = component.reset(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		
		//TODO: won't work. register eventBusListener
		assertEquals(ExecutionState.RESETTING, component.getExecutionState());
		assertEquals(ExecutionState.IDLE, component.getExecutionState());
		
		status = component.setOperationMode(opMode.getName(), user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		OperationModeInfo info = component.getOperationMode();
		assertEquals(opMode.getName(), info.getName());
		
		status = component.unregisterOperationMode(opMode.getName(), user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());

		opModes = component.getOperationModes();
		assertTrue(opModes.size() == 1);
		assertEquals("default", opModes.get(0).getName());			
	}
}
