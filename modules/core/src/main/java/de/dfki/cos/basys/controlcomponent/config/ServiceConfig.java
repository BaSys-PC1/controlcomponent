package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public class ServiceConfig {
    private String implementationJavaClass;
    private String connectionString;
    private boolean serviceMockDisabled;
    private boolean connectionObserved;
    private Map<String, String> properties = new HashMap<>();;

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

    public boolean isServiceMockDisabled() {
        return serviceMockDisabled;
    }

    public void setServiceMockDisabled(boolean serviceMockDisabled) {
        this.serviceMockDisabled = serviceMockDisabled;
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

/*
  controlcomponent:
    id: drone_1
    name: Drone-1
    operationModeJavaPackage: de.dfki.cos.basys.controlcomponent.example.opmodes
    executionMode: SIMULATE
    auto:
      disableExecutionModeChange: true
      disableOccupationCheck: true
      service:
        implementationJavaClass: de.dfki.cos.basys.controlcomponent.example.service.CalculationServiceImpl
        connectionString: autoConnectionString
        prop1: value1
    simulate:
      disableExecutionModeChange: false
      disableOccupationCheck: false
      #service:
      #  implementationJavaClass: de.dfki.cos.basys.controlcomponent.example.math.service.CalculationServiceImpl
      #  connectionString: simulateConnectionString
      #  prop2: value2

 */