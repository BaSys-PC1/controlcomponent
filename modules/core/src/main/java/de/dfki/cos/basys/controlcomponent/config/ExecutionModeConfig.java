package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public class ExecutionModeConfig {
    private boolean executionModeChangeDisabled;
    private boolean occupationCheckDisabled;
    private Map<String, String> properties = new HashMap<>();
    private ServiceConfig service;

    public boolean isExecutionModeChangeDisabled() {
        return executionModeChangeDisabled;
    }

    public void setExecutionModeChangeDisabled(boolean executionModeChangeDisabled) {
        this.executionModeChangeDisabled = executionModeChangeDisabled;
    }

    public boolean isOccupationCheckDisabled() {
        return occupationCheckDisabled;
    }

    public void setOccupationCheckDisabled(boolean occupationCheckDisabled) {
        this.occupationCheckDisabled = occupationCheckDisabled;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public ServiceConfig getService() {
        return service;
    }

    public void setService(ServiceConfig service) {
        this.service = service;
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