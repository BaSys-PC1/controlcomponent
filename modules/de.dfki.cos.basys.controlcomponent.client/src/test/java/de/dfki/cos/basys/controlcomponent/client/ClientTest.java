package de.dfki.cos.basys.controlcomponent.client;

import static org.junit.Assert.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationState;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusDataType;

public class ClientTest extends BaseTest {

	private boolean serverRequired = false;
	private ControlComponentServer server;
	private ControlComponentClientImpl client;

	String occupier = "occupier";
	String opmode = "testmode";

	@Before
	public void setUp() throws Exception {
		if (serverRequired) {
			server = new ControlComponentServer();
			server.startup().get();
		}

		Properties config = new Properties();
		config.put(StringConstants.id, "component-1/ControlComponent");
		config.put(StringConstants.serviceConnectionString, "opc.tcp://127.0.0.1:12685/basys");
		client = new ControlComponentClientImpl(config, null);
		client.connect(ComponentContext.getStaticContext(), config.getProperty(StringConstants.serviceConnectionString));
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
	@Ignore
	public void getControlComponentNode() throws InterruptedException, ExecutionException {
		ControlComponentNode node = client.getControlComponentNode();
		assertEquals("ControlComponent", node.getBrowseName().get().getName());
		ControlComponentStatusNode statusNode = node.getControlComponentStatusNode().get();
		ControlComponentStatusDataType statusValue = node.getControlComponentStatus().get();
//		System.out.println("----------");
//		System.out.println(statusValue);
//		System.out.println("----------");
		assertEquals("ControlComponentStatusDataType{ERRCODE=0, ERRMSG=OK, EXMODE=PRODUCTION, EXSTATE=STOPPED, OCCST=FREE, OCCUPIER=INIT, OPMODE=default, WORKST=}",statusValue.toString());
		assertEquals("FREE",statusNode.getOccupationState().get());
		assertEquals("INIT",statusNode.getOccupierId().get());
	}
	
	
	@Test
	@Ignore
	public void testReadStatus() {
		int errorCode = client.getErrorCode();
		assertEquals(0, errorCode);
		String errorMessage = client.getErrorMessage();
		assertEquals("OK", errorMessage);

		OccupationState level = client.getOccupationState();
		assertEquals(OccupationState.FREE, level);
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
	@Ignore
	public void testSetOperationMode() {
		ComponentOrderStatus status = null;
		
		try {			
			status = client.occupy(occupier);
			assertEquals(status.getMessage(), OrderStatus.ACCEPTED, status.getStatus());
			Thread.sleep(1000);

			OccupationState level = client.getOccupationState();
			assertEquals(OccupationState.OCCUPIED, level);
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
	
	

	@Test
	@Ignore
	public void testReadProperty() {
		ParameterInfo info = null;
		try {
			info = client.getParameter("duration");
			System.out.println(info);
		} catch (ComponentException e) {
			e.printStackTrace();
		}
		System.out.println("--------");
	}
}
