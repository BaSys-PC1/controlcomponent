package de.dfki.cos.basys.controlcomponent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.test.TestControlComponent;

public class OccupationTest extends BaseTest {

	@Before
	public void setUp() throws Exception {
		LOGGER.info("########################## setUp - start ##########################");
		super.setUp();

		component.activate(ComponentContext.getStaticContext());
		
		LOGGER.info("########################## setUp - finished ##########################");
	}

	@After
	public void tearDown() throws Exception {
		LOGGER.info("########################## tearDown - start ##########################");
		super.tearDown();		
		LOGGER.info("########################## tearDown - finished ##########################");
	}

	@Test
	public void testOccupyFree() {
		LOGGER.info("########################## testOccupyFree - start ##########################");
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.free(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
		LOGGER.info("########################## testOccupyFree - finished ##########################");
	}

	@Test
	public void testOccupyFreeReject() {
		LOGGER.info("########################## testOccupyFreeReject - start ##########################");

		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
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
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
		LOGGER.info("########################## testOccupyFreeReject - finished ##########################");
	}

	@Test
	public void testOccupyFreePriority() {
		LOGGER.info("########################## testOccupyFreePriority - start ##########################");

		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.occupyPriority(user_b);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_b, component.getOccupierId());
		assertEquals(OccupationLevel.PRIORITY, component.getOccupationLevel());

		status = component.free(user_b);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
		LOGGER.info("########################## testOccupyFreePriority - finished ##########################");
	}

	@Test
	public void testOccupyFreePriorityLocal() {
		LOGGER.info("########################## testOccupyFreePriorityLocal - start ##########################");
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());

		status = component.occupy(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.OCCUPIED, component.getOccupationLevel());

		status = component.occupyPriority(user_b);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_b, component.getOccupierId());
		assertEquals(OccupationLevel.PRIORITY, component.getOccupationLevel());

		status = component.occupyLocal(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationLevel.LOCAL, component.getOccupationLevel());

		status = component.free(user_a);
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationLevel.FREE, component.getOccupationLevel());
		LOGGER.info("########################## testOccupyFreePriorityLocal - finished ##########################");
	}
}
