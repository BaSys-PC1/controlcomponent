package de.dfki.cos.basys.controlcomponent.spring.configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.ModelDescriptor;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.aas.registration.api.IAASRegistry;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.restapi.SubmodelProvider;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.server.aas.ControlComponentSubmodelFactory;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;
import de.dfki.cos.basys.controlcomponent.spring.configuration.AccessibleEndpointConfiguration.EndpointDefinition;

@Configuration
public class ControlComponentSubmodelsConfig implements DisposableBean {

	@Autowired
	private AccessibleEndpointConfiguration config;	

	@Value("${basys.eventTransmitter.service.connectionString}")
	private String etServiceConnectionString;
//
//    @Value("${basys.controlcomponent.aas.asset-id}")
//    private String assetId;
//
//    @Value("${basys.controlcomponent.aas.id}")
//    private String assId;
//
//    @Value("${basys.controlcomponent.aas.submodel.interface.id}")
//    private String ccInterfaceSubmodelId;
//
//    @Value("${basys.controlcomponent.aas.submodel.instance.id}")
//    private String ccInstanceSubmodelId;

	@Autowired
	private ControlComponent controlComponent;

	@Autowired
	private ControlComponentServer ccServer;

	@Autowired
	private IAASRegistry aasRegistry;

	@Bean
	public Submodel ccInstanceSubmodel() {
		return ControlComponentSubmodelFactory.createInstanceSubmodel(controlComponent, etServiceConnectionString,
				ccServer.getNodeId(controlComponent), ccServer.getServer().getEndpointDescriptions());
	}

	@Bean
	public Submodel ccInterfaceSubmodel() {
		return ControlComponentSubmodelFactory.createInterfaceSubmodel(controlComponent);
	}

	@Bean
	public ServletRegistrationBean ccInstanceSubmodelServletBean() {
		Submodel sm = ccInstanceSubmodel();
		HttpServlet servlet = new VABHTTPInterface<SubmodelProvider>(new SubmodelProvider(sm));
		String path = "instance"; // sm.getIdShort()
		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/" + path + "/*");
		bean.setName(path); // important to avoid overriding, see
							// https://stackoverflow.com/questions/30670327/spring-boot-with-multiple-dispatcherservlet-each-having-their-own-controllers/30686733
		bean.setLoadOnStartup(1);

		String aasId = ControlComponentSubmodelFactory.getAasId(controlComponent);
		registerSubmodel(aasId, path, sm);

		return bean;
	}

	@Bean
	public ServletRegistrationBean ccInterfaceSubmodelServletBean() {
		Submodel sm = ccInterfaceSubmodel();
		HttpServlet servlet = new VABHTTPInterface(new SubmodelProvider(sm));
		String path = "interface"; // sm.getIdShort()
		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/" + path + "/*");
		bean.setName(path);
		bean.setLoadOnStartup(1);

		String aasId = ControlComponentSubmodelFactory.getAasId(controlComponent);
		registerSubmodel(aasId, path, sm);

		return bean;
	}

	public void registerSubmodel(String aasId, String path, Submodel sm) {
        try {
            aasRegistry.delete(new Identifier(IdentifierType.CUSTOM, aasId), sm.getIdentification());
        } catch (ProviderException var5) {
        }

        try {
            SubmodelDescriptor smDescriptor = createSubmodelDescriptorForPath(sm, path);
            aasRegistry.register(new Identifier(IdentifierType.CUSTOM, aasId), smDescriptor);
        } catch (ProviderException e) {
            e.printStackTrace();
        }
    }

	private SubmodelDescriptor createSubmodelDescriptorForPath(Submodel sm, String path) {
		SubmodelDescriptor smDescriptor = new SubmodelDescriptor(sm, "");
        
        List<Map<String, Object>> endpointsRaw = new ArrayList<>();
        applyAccessibleEndpoints(path, endpointsRaw);
        smDescriptor.put(ModelDescriptor.ENDPOINTS, endpointsRaw);
        System.out.println(smDescriptor.toString());
        return smDescriptor;
	}


	private void applyAccessibleEndpoints(String path, List<Map<String, Object>> endpointsRaw) {
        for (EndpointDefinition eachEndpoint : config.getAllAccessibleEndpoints()) {
        	String href = eachEndpoint.getHref();
        	String protocol = eachEndpoint.getEndpointProtocol();
            endpointsRaw.add(getEndpointWrapper(protocol, href + "/" + path + "/submodel"));
        }
	}

	private Map<String, Object> getEndpointWrapper(String protocol, String address) {		
		LinkedHashMap<String, Object> endpointWrapper = new LinkedHashMap<>();
		endpointWrapper.put(AssetAdministrationShell.TYPE, protocol);
		endpointWrapper.put(AssetAdministrationShell.ADDRESS, address);
		return endpointWrapper;
	}


	@Override
	public void destroy() throws Exception {
		String aasId = ControlComponentSubmodelFactory.getAasId(controlComponent);
		try {
			aasRegistry.delete(new Identifier(IdentifierType.CUSTOM, aasId), new Identifier(IdentifierType.CUSTOM,
					ControlComponentSubmodelFactory.getInstanceSubmodelId(controlComponent)));
		} catch (Exception e) {

		}
		try {
			aasRegistry.delete(new Identifier(IdentifierType.CUSTOM, aasId), new Identifier(IdentifierType.CUSTOM,
					ControlComponentSubmodelFactory.getInterfaceSubmodelId(controlComponent)));
		} catch (Exception e) {

		}
	}


}
