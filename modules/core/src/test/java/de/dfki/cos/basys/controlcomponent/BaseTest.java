package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.controlcomponent.config.ControlComponentConfig;
import de.dfki.cos.basys.controlcomponent.config.ControlComponentConfigBuilder;
import de.dfki.cos.basys.controlcomponent.config.ExecutionModeConfigBuilder;
import de.dfki.cos.basys.controlcomponent.config.ServiceConfigBuilder;
import de.dfki.cos.basys.controlcomponent.test.TestControlComponent;
import de.dfki.cos.basys.controlcomponent.util.ControlComponentInfoRecorder;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class BaseTest {

	protected final Logger LOGGER = LoggerFactory.getLogger("ControlComponentTests");
	
	protected ControlComponentConfig config;
	protected TestControlComponent component;
	protected ControlComponentInfoRecorder recorder;
	protected ControlComponentInfo info = null;
	protected ComponentOrderStatus status = null;
	
	protected String user_a = "user_a";
	protected String user_b = "user_b";
	protected String occupier = "occupier";
	
	@Before
	public void setUp() throws Exception {

		config = ControlComponentConfigBuilder.aControlComponentConfig()
				.withId("id")
				.withName("name")
				.withExecutionMode("SIMULATE")
				.withSimulate(
						ExecutionModeConfigBuilder.anExecutionModeConfig()
								.withOccupationCheckDisabled(false)
								.withExecutionModeChangeDisabled(false)
								.withService(ServiceConfigBuilder.aServiceConfig()
										.withImplementationJavaClass("de.dfki.cos.basys.controlcomponent.test.TestService")
										.withConnectionString("http://connection/for/simulate")
										.build())
								.build()
				)
				.withAuto(
						ExecutionModeConfigBuilder.anExecutionModeConfig()
								.withOccupationCheckDisabled(false)
								.withExecutionModeChangeDisabled(false)
								.withService(ServiceConfigBuilder.aServiceConfig()
										.withImplementationJavaClass("de.dfki.cos.basys.controlcomponent.test.TestService")
										.withConnectionString("http://connection/for/auto")
										.build())
								.build()
				)
				.build();

		component = new TestControlComponent(config);

		recorder = new ControlComponentInfoRecorder();
	}

	@After
	public void tearDown() throws Exception {
		component.deactivate();
		recorder.close();
	}
	
	public ComponentOrderStatus print(ComponentOrderStatus status) {
		System.out.println(status.getStatus() + " : " + status.getMessage());
		return status;
	}
	
//	public ControlComponentInfo print(ControlComponentInfo info) {
//		System.out.println(info);
//		return info;
//	}
}
