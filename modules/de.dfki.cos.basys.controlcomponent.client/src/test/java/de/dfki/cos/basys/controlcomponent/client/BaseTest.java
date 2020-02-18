package de.dfki.cos.basys.controlcomponent.client;

import java.util.Properties;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.StringConstants;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;
import de.dfki.cos.basys.controlcomponent.util.ControlComponentInfoRecorder;

public class BaseTest {

	protected final Logger LOGGER = LoggerFactory.getLogger("ControlComponentClientTests");
	
	protected Properties config;
	protected ControlComponentInfoRecorder recorder;
	protected ControlComponentInfo info = null;
	protected ComponentOrderStatus status = null;
	
	protected String user_a = "user_a";
	protected String user_b = "user_b";
	protected String occupier = "occupier";
	
	private boolean serverRequired = false;
	private ControlComponentServer server;
	protected ControlComponentClientImpl component;
	
	@Before
	public void setUp() throws Exception {
		if (serverRequired) {
			server = new ControlComponentServer();
			server.startup().get();
		}

		Properties config = new Properties();
		config.put(StringConstants.id, "component-1/ControlComponent");
		config.put(StringConstants.serviceConnectionString, "opc.tcp://127.0.0.1:12685/basys");
		config.put(StringConstants.username, user_a);
		config.put(StringConstants.password, user_a);
		config.put("nodeId", "component-1/ControlComponent");
		component = new ControlComponentClientImpl(config, null);
		component.connect(ComponentContext.getStaticContext(), config.getProperty(StringConstants.serviceConnectionString));
		
		recorder = new ControlComponentInfoRecorder();
	}

	@After
	public void tearDown() throws Exception {
		component.disconnect();
		if (serverRequired && server != null) {
			server.shutdown().get();
			server = null;
		}
		Stack.releaseSharedResources();
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
