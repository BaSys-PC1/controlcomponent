package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public final class ServiceConfigBuilder {
    private String implementationJavaClass;
    private String connectionString;
    private boolean connectionObserved;
    private Map<String, String> properties = new HashMap<>();

    private ServiceConfigBuilder() {
    }

    public static ServiceConfigBuilder aServiceConfig() {
        return new ServiceConfigBuilder();
    }

    public ServiceConfigBuilder withImplementationJavaClass(String implementationJavaClass) {
        this.implementationJavaClass = implementationJavaClass;
        return this;
    }

    public ServiceConfigBuilder withConnectionString(String connectionString) {
        this.connectionString = connectionString;
        return this;
    }

    public ServiceConfigBuilder withConnectionObserved(boolean connectionObserved) {
        this.connectionObserved = connectionObserved;
        return this;
    }

    public ServiceConfigBuilder withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public ServiceConfig build() {
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setImplementationJavaClass(implementationJavaClass);
        serviceConfig.setConnectionString(connectionString);
        serviceConfig.setConnectionObserved(connectionObserved);
        serviceConfig.setProperties(properties);
        return serviceConfig;
    }
}
