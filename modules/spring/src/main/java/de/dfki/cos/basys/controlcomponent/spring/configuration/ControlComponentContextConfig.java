package de.dfki.cos.basys.controlcomponent.spring.configuration;

import de.dfki.cos.basys.aas.event.mqtt.EventTransmitterComponent;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.controlcomponent.server.aas.EventTranslator;
import org.eclipse.basyx.aas.registration.api.IAASRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class ControlComponentContextConfig {

    @Value("${basys.eventTransmitter.type:mqtt}")
    private String etType;

    @Value("${basys.eventTransmitter.service.implementationJavaClass:de.dfki.cos.basys.aas.event.mqtt.MqttEventTransmitter}")
    private String etServiceImplementationJavaClass;

    @Value("${basys.eventTransmitter.service.connectionString:tcp://localhost:1883}")
    private String etServiceConnectionString;

    @Autowired
    private IAASRegistry aasRegistry;

    @Bean(destroyMethod = "deactivate")
    public EventTranslator eventTranslator(ComponentContext context) {
        var eventTranslator = new EventTranslator();
        eventTranslator.activate(context);
        return eventTranslator;
    }

    @Bean(destroyMethod = "deactivate")
    public EventTransmitterComponent eventTransmitterComponent(ComponentContext context) throws ComponentException {
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
    public ComponentContext ccContext() {
        ComponentContext context = ComponentContext.getStaticContext();
        return context;
    }
}
