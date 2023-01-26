package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.common.component.ComponentContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExecutionModeTest extends BaseTest {

    @Before
    public void setUp() throws Exception {
        LOGGER.info("########################## setUp - start ##########################");
        super.setUp();

        component.activate(ComponentContext.getStaticContext());

        component.occupy(occupier);

//        component.reset(occupier);
//        recorder.waitForExecutionState(ExecutionState.IDLE);
//        status = component.setOperationMode("TSTMD", occupier);
//        recorder.getLastInfo(); // wait for and consume opmode change info
//
        Thread.sleep(1000);
        recorder.clear();

        LOGGER.info("########################## setUp - finished ##########################");
    }

    @After
    public void tearDown() throws Exception {
        LOGGER.info("########################## tearDown - start ##########################");

        status = component.free(occupier);
        super.tearDown();

        LOGGER.info("########################## tearDown - finished ##########################");
    }

    @Test
    public void testChangeExecutionMode() {

        component.setExecutionMode(ExecutionMode.AUTO,occupier);
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.SIMULATE, info.getExecutionMode());
        assertFalse(info.isConnected());
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.AUTO, info.getExecutionMode());
        assertTrue(info.isConnected());
        info = recorder.getLastInfo(); //ignore
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        component.setExecutionMode(ExecutionMode.SIMULATE,occupier);
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.AUTO, info.getExecutionMode());
        assertFalse(info.isConnected());
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.SIMULATE, info.getExecutionMode());
        assertTrue(info.isConnected());
        info = recorder.getLastInfo(); //ignore

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        component.setExecutionMode(ExecutionMode.AUTO,occupier);
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.SIMULATE, info.getExecutionMode());
        assertFalse(info.isConnected());
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.AUTO, info.getExecutionMode());
        assertTrue(info.isConnected());
        info = recorder.getLastInfo(); //ignore

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        component.setExecutionMode(ExecutionMode.SIMULATE,occupier);
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.AUTO, info.getExecutionMode());
        assertFalse(info.isConnected());
        info = recorder.getLastInfo();
        assertEquals(ExecutionMode.SIMULATE, info.getExecutionMode());
        assertTrue(info.isConnected());
        info = recorder.getLastInfo(); //ignore

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
