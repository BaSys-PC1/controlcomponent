package de.dfki.cos.basys.controlcomponent.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dfki.cos.basys.controlcomponent.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.VariableAccess;
import de.dfki.cos.basys.controlcomponent.VariableType;
import de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl;
import de.dfki.cos.basys.controlcomponent.impl.VariableImpl;

public class OccupationTest {

	ComponentConfiguration config;
	ControlComponent component;

	String user_a = "user_a";
	String user_b = "user_b";

	ComponentOrderStatus status = null;

	@Before
	public void setUp() throws Exception {

		config = new ComponentConfigurationImpl.Builder().id("some_id").name("test-control-component")
				.externalConnectionString("").implementationJavaClass("").build();

		component = new TestControlComponent(config);
		component.activate();
	}

	@After
	public void tearDown() throws Exception {
		component.deactivate();
	}

	@Test
	public void testOccupyFree() {
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.free(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(null, component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
	}

	@Test
	public void testOccupyFreeReject() {

		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.occupy(user_b);
		assertEquals(OrderStatus.REJECTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.free(user_b);
		assertEquals(OrderStatus.REJECTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.free(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(null, component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
	}

	@Test
	public void testOccupyFreePriority() {

		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.occupyPriority(user_b);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_b, component.getOccupierId());
		assertEquals(OccupationLevel.PRIORITY, component.getOccupationLevel());

		status = component.free(user_b);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(null, component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
	}

	@Test
	public void testOccupyFreePriorityLocal() {
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.occupyPriority(user_b);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_b, component.getOccupierId());
		assertEquals(OccupationLevel.PRIORITY, component.getOccupationLevel());

		status = component.occupyLocal(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.LOCAL, component.getOccupationLevel());

		status = component.free(user_a);
		assertEquals(OrderStatus.ACCEPTED, status.getStatus());
		assertEquals(null, component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
	}
}
