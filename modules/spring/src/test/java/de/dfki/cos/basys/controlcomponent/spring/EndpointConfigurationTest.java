package de.dfki.cos.basys.controlcomponent.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import de.dfki.cos.basys.controlcomponent.spring.configuration.AccessibleEndpointConfiguration;
import de.dfki.cos.basys.controlcomponent.spring.configuration.AccessibleEndpointConfiguration.EndpointDefinition;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = { AccessibleEndpointConfiguration.class })
@RunWith(SpringRunner.class)
@ActiveProfiles({ "test" })
public class EndpointConfigurationTest {

	@Autowired
	private AccessibleEndpointConfiguration config;

	@Test
	public void testThatValuesAreAssigned() throws InterruptedException  {
		List<EndpointDefinition> endpoints = config.getAllAccessibleEndpoints();
		assertThat(endpoints.size()).isEqualTo(3);
		checkEndpointValues(endpoints.get(0), "http", "http://www.mytest.org:8080");
		checkEndpointValues(endpoints.get(1), "http", "http://www.mytest.de:9090");
		checkEndpointValues(endpoints.get(2), "https", "https://www.mytest.org:443");
	}

	private void checkEndpointValues(EndpointDefinition endpointDefinition, String protocol, String href) {
		assertThat(endpointDefinition.getEndpointProtocol()).isEqualTo(protocol);
		assertThat(endpointDefinition.getHref()).isEqualTo(href);
	}

}