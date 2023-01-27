package de.dfki.cos.basys.controlcomponent.config;

import java.util.HashMap;
import java.util.Map;

public final class ControlComponentConfigBuilder {
    private String id;
    private String name;
    private String implementationJavaClass;
    private String operationModeJavaPackage;
    private String initialExecutionMode;
    private Map<String, ExecutionModeConfig> executionModes;
    private Map<String, String> properties = new HashMap<>();

    private ControlComponentConfigBuilder() {
    }

    public static ControlComponentConfigBuilder aControlComponentConfig() {
        return new ControlComponentConfigBuilder();
    }

    public ControlComponentConfigBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ControlComponentConfigBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ControlComponentConfigBuilder withImplementationJavaClass(String implementationJavaClass) {
        this.implementationJavaClass = implementationJavaClass;
        return this;
    }

    public ControlComponentConfigBuilder withOperationModeJavaPackage(String operationModeJavaPackage) {
        this.operationModeJavaPackage = operationModeJavaPackage;
        return this;
    }

    public ControlComponentConfigBuilder withInitialExecutionMode(String initialExecutionMode) {
        this.initialExecutionMode = initialExecutionMode;
        return this;
    }

    public ControlComponentConfigBuilder withExecutionModes(Map<String, ExecutionModeConfig> executionModes) {
        this.executionModes = executionModes;
        return this;
    }

    public ControlComponentConfigBuilder withProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    public ControlComponentConfig build() {
        ControlComponentConfig controlComponentConfig = new ControlComponentConfig();
        controlComponentConfig.setId(id);
        controlComponentConfig.setName(name);
        controlComponentConfig.setImplementationJavaClass(implementationJavaClass);
        controlComponentConfig.setOperationModeJavaPackage(operationModeJavaPackage);
        controlComponentConfig.setInitialExecutionMode(initialExecutionMode);
        controlComponentConfig.setExecutionModes(executionModes);
        controlComponentConfig.setProperties(properties);
        return controlComponentConfig;
    }
}
