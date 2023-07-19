package de.dfki.cos.basys.controlcomponent.spring.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.paho.client.mqttv3.util.Strings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("server")
public class AccessibleEndpointConfiguration {

	private String accessibleEndpoint;
	private List<EndpointDefinition> accessibleEndpoints;

	public void setAccessibleEndpoint(String accessibleEndpoint) {
		this.accessibleEndpoint = accessibleEndpoint;
	}
	
	public void setAccessibleEndpoints(List<EndpointDefinition> accessibleEndpoints) {
		this.accessibleEndpoints = accessibleEndpoints;
	}

	public List<EndpointDefinition> getAllAccessibleEndpoints() {
		List<EndpointDefinition> allEndpoints = new ArrayList<>();
		if (!Strings.isEmpty(accessibleEndpoint)) {
			allEndpoints.add(new EndpointDefinition(accessibleEndpoint, "http"));
		}
		if (accessibleEndpoints != null) {
			allEndpoints.addAll(accessibleEndpoints);
		}
		return allEndpoints;
	}
	
	public static class EndpointDefinition {

		@NonNull
		private String href;

		@NonNull
		private String endpointProtocol;

		public EndpointDefinition(String href, String protocol) {
			this.href = href;
			this.endpointProtocol = protocol;
		}
		
		public EndpointDefinition() {
		}		

		public String getHref() {
			return href;
		}

		public String getEndpointProtocol() {
			return endpointProtocol;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public void setEndpointProtocol(String endpointProtocol) {
			this.endpointProtocol = endpointProtocol;
		}

		@Override
		public int hashCode() {
			return Objects.hash(endpointProtocol, href);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			EndpointDefinition other = (EndpointDefinition) obj;
			return Objects.equals(endpointProtocol, other.endpointProtocol) && Objects.equals(href, other.href);
		}
	}
}
