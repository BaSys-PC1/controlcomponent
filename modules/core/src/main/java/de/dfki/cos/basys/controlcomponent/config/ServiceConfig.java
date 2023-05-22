package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public class ServiceConfig {
    private String implementationJavaClass;
    private String connectionString;
    private boolean connectionObserved;
    private Map<String, String> properties = new HashMap<>();

    public String getImplementationJavaClass() {
        return implementationJavaClass;
    }

    public void setImplementationJavaClass(String implementationJavaClass) {
        this.implementationJavaClass = implementationJavaClass;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public boolean isConnectionObserved() {
        return connectionObserved;
    }

    public void setConnectionObserved(boolean connectionObserved) {
        this.connectionObserved = connectionObserved;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

}
