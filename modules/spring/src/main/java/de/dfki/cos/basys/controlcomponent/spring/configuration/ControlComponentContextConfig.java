package de.dfki.cos.basys.controlcomponent.spring.configuration;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.aas.services.EventTransmitterComponent;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ServiceManager;
import de.dfki.cos.basys.common.component.impl.ServiceManagerImpl;
import org.eclipse.basyx.aas.registration.api.IAASRegistry;
import org.eclipse.basyx.aas.registration.proxy.AASRegistryProxy;
import org.eclipse.basyx.aas.registry.compatibility.DotAASRegistryProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ControlComponentContextConfig {

    @Value("${basys.aasRegistry.type:basyx}")
    private String aasRegistryType;

    @Value("${basys.aasRegistry.service.connectionString:http://localhost:4000}")
    private String arServiceConnectionString;

    @Value("${basys.eventTransmitter.type:mqtt}")
    private String etType;

    @Value("${basys.eventTransmitter.service.implementationJavaClass:de.dfki.cos.basys.aas.event.mqtt.MqttEventTransmitter}")
    private String etServiceImplementationJavaClass;

    @Value("${basys.eventTransmitter.service.connectionString:tcp://localhost:1883}")
    private String etServiceConnectionString;


    @Bean
    public IAASRegistry aasRegistry() {
        IAASRegistry aasRegistry = null;
        if ("dotaas".equals(aasRegistryType)) {
            aasRegistry = new DotAASRegistryProxy(arServiceConnectionString);
        } else if ("basyx".equals(aasRegistryType)) {
            aasRegistry = new AASRegistryProxy(arServiceConnectionString);
        } else { // defaulting to none
            //FIXME: returning null lets the bean creation fail!
        }
        return aasRegistry;
    }

    @Bean
    public EventTransmitterComponent eventTransmitterComponent(AasComponentContext context) throws ComponentException {
        Properties config = new Properties();
        config.setProperty("id", "EventTransmitterComponent");
        config.setProperty("name", "EventTransmitterComponent");
        config.setProperty("category", "SERVICE_COMPONENT");
        config.setProperty("register", "false");
        config.setProperty("serviceConnectionString", etServiceConnectionString);
        config.setProperty("serviceImplementationJavaClass", etServiceImplementationJavaClass);

        EventTransmitterComponent eventTransmitter = new EventTransmitterComponent(config);
        eventTransmitter.activate(context);
        return eventTransmitter;
    }

    @Bean
    public AasComponentContext ccContext() {
        AasComponentContext context = AasComponentContext.getStaticContext();
        context.setAasRegistry(aasRegistry());
        return context;
    }
}
