package de.dfki.cos.basys.controlcomponent.client;

import de.dfki.cos.basys.controlcomponent.OccupationState;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OccupationTest extends BaseTest {

	@Before
	public void setUp() throws Exception {
		LOGGER.info("########################## setUp - start ##########################");
		super.setUp();

		//component.activate(ComponentContext.getStaticContext());
		
		LOGGER.info("########################## setUp - finished ##########################");
	}

	@After
	public void tearDown() throws Exception {
		LOGGER.info("########################## tearDown - start ##########################");
		super.tearDown();		
		LOGGER.info("########################## tearDown - finished ##########################");
	}

	@Test
	@Ignore
	public void testOccupyFree() {
		LOGGER.info("########################## testOccupyFree - start ##########################");
		assertEquals(OccupationState.FREE, component.getOccupationState());

		status = component.occupy();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationState.OCCUPIED, component.getOccupationState());

		status = component.free();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationState.FREE, component.getOccupationState());
		LOGGER.info("########################## testOccupyFree - finished ##########################");
	}

	@Test
	@Ignore
	public void testOccupyFreeReject() {
		LOGGER.info("########################## testOccupyFreeReject - start ##########################");

		assertEquals(OccupationState.FREE, component.getOccupationState());

		status = component.occupy();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationState.OCCUPIED, component.getOccupationState());

		status = component.occupy();
		assertEquals(OrderStatus.REJECTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationState.OCCUPIED, component.getOccupationState());

		status = component.free();
		assertEquals(OrderStatus.REJECTED, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationState.OCCUPIED, component.getOccupationState());

		status = component.free();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationState.FREE, component.getOccupationState());
		LOGGER.info("########################## testOccupyFreeReject - finished ##########################");
	}

	@Test
	@Ignore
	public void testOccupyFreePriority() {
		LOGGER.info("########################## testOccupyFreePriority - start ##########################");

		assertEquals(OccupationState.FREE, component.getOccupationState());

		status = component.occupy();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_a, component.getOccupierId());
		assertEquals(OccupationState.OCCUPIED, component.getOccupationState());

		status = component.occupyPriority();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals(user_b, component.getOccupierId());
		assertEquals(OccupationState.PRIORITY, component.getOccupationState());

		status = component.free();
		assertEquals(OrderStatus.DONE, status.getStatus());
		assertEquals("", component.getOccupierId());
		assertEquals(OccupationState.FREE, component.getOccupationState());
		LOGGER.info("########################## testOccupyFreePriority - finished ##########################");
	}

//	@Test
//	public void testOccupyFreePriorityLocal() {
//		LOGGER.info("########################## testOccupyFreePriorityLocal - start ##########################");
//		assertEquals(OccupationState.FREE, component.getOccupationState());
//
//		status = component.occupy(user_a);
//		assertEquals(OrderStatus.DONE, status.getStatus());
//		assertEquals(user_a, component.getOccupierId());
//		assertEquals(OccupationState.OCCUPIED, component.getOccupationState());
//
//		status = component.occupyPriority(user_b);
//		assertEquals(OrderStatus.DONE, status.getStatus());
//		assertEquals(user_b, component.getOccupierId());
//		assertEquals(OccupationState.PRIORITY, component.getOccupationState());
//
//		status = component.occupyLocal(user_a);
//		assertEquals(OrderStatus.DONE, status.getStatus());
//		assertEquals(user_a, component.getOccupierId());
//		assertEquals(OccupationState.LOCAL, component.getOccupationState());
//
//		status = component.free(user_a);
//		assertEquals(OrderStatus.DONE, status.getStatus());
//		assertEquals("", component.getOccupierId());
//		assertEquals(OccupationState.FREE, component.getOccupationState());
//		LOGGER.info("########################## testOccupyFreePriorityLocal - finished ##########################");
//	}
}
