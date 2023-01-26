package de.dfki.cos.basys.controlcomponent.test;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestService implements ServiceProvider<TestService> {

    private final Logger LOGGER = LoggerFactory.getLogger("TestService");

    private boolean connected = false;

    @Override
    public boolean connect(ComponentContext context, String connectionString) {
        connected = true;
        return connected;
    }

    @Override
    public void disconnect() {
        connected = false;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public TestService getService() {
        return this;
    }
}
