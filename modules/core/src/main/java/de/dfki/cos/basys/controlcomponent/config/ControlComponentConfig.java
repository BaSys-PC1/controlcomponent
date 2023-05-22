package de.dfki.cos.basys.controlcomponent.config;

import de.dfki.cos.basys.controlcomponent.ExecutionMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlComponentConfig {
    private String id;
    private String name;
    private String implementationJavaClass = "de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent";
    private String operationModeJavaPackage = "de.dfki.cos.basys";
    private String initialExecutionMode = "simulate";
    private Map<String,ExecutionModeConfig> executionModes;

    private Map<String, String> properties = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImplementationJavaClass() {
        return implementationJavaClass;
    }

    public void setImplementationJavaClass(String implementationJavaClass) {
        this.implementationJavaClass = implementationJavaClass;
    }

    public String getOperationModeJavaPackage() {
        return operationModeJavaPackage;
    }

    public void setOperationModeJavaPackage(String operationModeJavaPackage) {
        this.operationModeJavaPackage = operationModeJavaPackage;
    }

    public String getInitialExecutionMode() {
        return initialExecutionMode;
    }

    public void setInitialExecutionMode(String initialExecutionMode) {
        this.initialExecutionMode = initialExecutionMode;
    }

    public Map<String, ExecutionModeConfig> getExecutionModes() {
        return executionModes;
    }

    public void setExecutionModes(Map<String, ExecutionModeConfig> executionModes) {
        this.executionModes = executionModes;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public ExecutionModeConfig getExecutionModeConfig(ExecutionMode executionMode) {
        return getExecutionModes().get(executionMode.name().toLowerCase());
    }
}