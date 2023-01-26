package de.dfki.cos.basys.controlcomponent.config;

import de.dfki.cos.basys.controlcomponent.ExecutionMode;

import java.util.HashMap;
import java.util.Map;

public class ControlComponentConfig {
    private String id;
    private String name;
    private String implementationJavaClass = "de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent";
    private String operationModeJavaPackage = "de.dfki.cos.basys";
    private String executionMode = "SIMULATE";
    private ExecutionModeConfig auto;
    private ExecutionModeConfig simulate;

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

    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    public ExecutionModeConfig getAuto() {
        return auto;
    }

    public void setAuto(ExecutionModeConfig auto) {
        this.auto = auto;
    }

    public ExecutionModeConfig getSimulate() {
        return simulate;
    }

    public void setSimulate(ExecutionModeConfig simulate) {
        this.simulate = simulate;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public ExecutionModeConfig getExecutionModeConfig(ExecutionMode executionMode) {
        switch (executionMode) {
            case AUTO:
                return getAuto();
            case SIMULATE:
                return getSimulate();
            default:
                return null;
        }
    }
}