package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public final class ExecutionModeConfigBuilder {
    private boolean executionModeChangeDisabled;
    private boolean occupationCheckDisabled;
    private Map<String, String> properties = new HashMap<>();
    private ServiceConfig service;

    private ExecutionModeConfigBuilder() {
    }

    public static ExecutionModeConfigBuilder anExecutionModeConfig() {
        return new ExecutionModeConfigBuilder();
    }

    public ExecutionModeConfigBuilder withExecutionModeChangeDisabled(boolean executionModeChangeDisabled) {
        this.executionModeChangeDisabled = executionModeChangeDisabled;
        return this;
    }

    public ExecutionModeConfigBuilder withOccupationCheckDisabled(boolean occupationCheckDisabled) {
        this.occupationCheckDisabled = occupationCheckDisabled;
        return this;
    }

    public ExecutionModeConfigBuilder withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public ExecutionModeConfigBuilder withService(ServiceConfig service) {
        this.service = service;
        return this;
    }

    public ExecutionModeConfig build() {
        ExecutionModeConfig executionModeConfig = new ExecutionModeConfig();
        executionModeConfig.setExecutionModeChangeDisabled(executionModeChangeDisabled);
        executionModeConfig.setOccupationCheckDisabled(occupationCheckDisabled);
        executionModeConfig.setProperties(properties);
        executionModeConfig.setService(service);
        return executionModeConfig;
    }
}
