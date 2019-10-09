package de.dfki.cos.basys.controlcomponent.client;

import static org.junit.Assert.*;

import java.util.Properties;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.common.component.OrderStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.server.ControlComponentServer;

public class ClientTest {

	private boolean serverRequired = false;
	private ControlComponentServer server;
	private ControlComponentClient client;

	String occupier = "occupier";
	String opmode = "testmode";

	@Before
	public void setUp() throws Exception {
		if (serverRequired) {
			server = new ControlComponentServer();
			server.startup().get();
		}

		Properties config = new Properties();
		config.put(Component.id, "component-1");
		config.put(Component.connectionString, "opc.tcp://127.0.0.1:12685/basys");
		client = new ControlComponentClient(config);
		client.connect();
	}

	@After
	public void tearDown() throws Exception {
		client.disconnect();
		if (serverRequired && server != null) {
			server.shutdown().get();
			server = null;
		}
		Stack.releaseSharedResources();
	}

	@Test
	public void testReadStatus() {
		int errorCode = client.getErrorCode();
		assertEquals(0, errorCode);
		String errorMessage = client.getErrorMessage();
		assertEquals("OK", errorMessage);

		OccupationLevel level = client.getOccupationLevel();
		assertEquals(OccupationLevel.FREE, level);
		String occupierId = client.getOccupierId();
		assertEquals("INIT", occupierId);

		ExecutionMode exmode = client.getExecutionMode();
		assertEquals(ExecutionMode.PRODUCTION, exmode);
		ExecutionState state = client.getExecutionState();
		assertEquals(ExecutionState.STOPPED, state);

		String opmode = client.getOperationMode().getName();
		assertEquals("default", opmode);

		String workState = client.getWorkState();
		assertNull(workState);
	}

	@Test
	public void testSetOperationMode() {
		ComponentOrderStatus status = null;
		
		try {			
			status = client.occupy(occupier);
			assertEquals(status.getMessage(), OrderStatus.ACCEPTED, status.getStatus());
			Thread.sleep(1000);

			OccupationLevel level = client.getOccupationLevel();
			assertEquals(OccupationLevel.OCCUPIED, level);
			String occupierId = client.getOccupierId();
			assertEquals(occupier, occupierId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			status = client.reset(occupier);
			assertEquals(status.getMessage(), OrderStatus.ACCEPTED, status.getStatus());
			Thread.sleep(1000);

			ExecutionMode exmode = client.getExecutionMode();
			assertEquals(ExecutionMode.PRODUCTION, exmode);
			ExecutionState state = client.getExecutionState();
			assertEquals(ExecutionState.IDLE, state);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			status = client.setOperationMode(opmode, occupier);
			assertEquals(status.getMessage(), OrderStatus.ACCEPTED, status.getStatus());
			Thread.sleep(1000);

			String mode = client.getOperationMode().getName();
			assertEquals(opmode, mode);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int errorCode = client.getErrorCode();
		assertEquals(0, errorCode);
		String errorMessage = client.getErrorMessage();
		assertEquals("OK", errorMessage);
		String workState = client.getWorkState();
		assertNull(workState);
	}
}
