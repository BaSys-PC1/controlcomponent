package de.dfki.cos.basys.controlcomponent;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import de.dfki.cos.basys.controlcomponent.util.ControlComponentInfoRecorder;
import de.dfki.cos.basys.controlcomponent.test.TestControlComponent;

public class BaseTest {

	protected final Logger LOGGER = LoggerFactory.getLogger("ControlComponentTests");
	
	protected Properties config;
	protected TestControlComponent component;
	protected ControlComponentInfoRecorder recorder;
	protected ControlComponentInfo info = null;
	protected ComponentOrderStatus status = null;
	
	protected String user_a = "user_a";
	protected String user_b = "user_b";
	protected String occupier = "occupier";
	
	@Before
	public void setUp() throws Exception {

		config = new Properties();
		config.put(StringConstants.id, "test");
		config.put(StringConstants.name, "test");
		config.put(StringConstants.serviceConnectionString, "");

		component = new TestControlComponent(config);

		recorder = new ControlComponentInfoRecorder();
	}

	@After
	public void tearDown() throws Exception {
		component.deactivate();
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
