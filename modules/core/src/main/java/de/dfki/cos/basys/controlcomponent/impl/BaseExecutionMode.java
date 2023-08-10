package de.dfki.cos.basys.controlcomponent.impl;

import com.google.common.base.Strings;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ServiceManager;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.config.ExecutionModeConfig;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseExecutionMode<T> {

    private Logger LOGGER = LoggerFactory.getLogger("ExecutionMode");
    private ExecutionMode executionMode;
    private ExecutionModeConfig config;
    private ControlComponent component;

    private ServiceManager<T> serviceManager = null;

    public BaseExecutionMode(ExecutionMode executionMode, ExecutionModeConfig config, ControlComponent component) {
        this.LOGGER = LoggerFactory.getLogger(component.getName() + "." + "ExecutionMode." + executionMode);
        this.executionMode = executionMode;
        this.config = config;
        this.component = component;

        if (config.getService() != null && !Strings.isNullOrEmpty(config.getService().getImplementationJavaClass())) {
            serviceManager = new ServiceManagerImpl<T>(config.getService());
        }
    }

    public ExecutionMode getExecutionMode() {
        return executionMode;
    }

    public boolean isExecutionModeChangeDisabled() {
        return config.isExecutionModeChangeDisabled();
    }

    public boolean isOccupationCheckDisabled() {
        return config.isOccupationCheckDisabled();
    }

    // @Override
    public T getService() {
        return serviceManager != null ? serviceManager.getService() : null;
    }

    public void connect(ComponentContext context) throws ComponentException {
        LOGGER.info("connect to service");
        if (serviceManager != null) {
            serviceManager.connect(context);
        }
    }

    public void disconnect() throws ComponentException {
        LOGGER.info("disconnect from service");
        if (serviceManager != null) {
            serviceManager.disconnect();
        }
    }


    public boolean isConnected() {
        return (serviceManager != null) ? serviceManager.isConnected() : false;
    }

}
