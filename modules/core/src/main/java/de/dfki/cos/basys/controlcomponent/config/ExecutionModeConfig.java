package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public class ExecutionModeConfig {
    private boolean executionModeChangeDisabled;
    private boolean occupationCheckDisabled;
    private Map<String, String> properties = new HashMap<>();
    private ServiceConfig service = new ServiceConfig();

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
