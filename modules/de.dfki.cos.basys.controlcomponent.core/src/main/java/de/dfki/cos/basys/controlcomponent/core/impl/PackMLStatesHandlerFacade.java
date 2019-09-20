package de.dfki.cos.basys.controlcomponent.core.impl;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;


public class PackMLStatesHandlerFacade implements PackMLActiveStatesHandler, PackMLWaitStatesHandler {

	BaseControlComponent component;
	SimulatedStatesHandler simulatedStatesHandler;

	public PackMLStatesHandlerFacade(BaseControlComponent component) {
		this.component = component;
		this.simulatedStatesHandler = new SimulatedStatesHandler(component);
	}

	/*
	 * default WaitStatesHandler implementation -> notify Basys Middleware
	 */

	@Override
	public void onStopped() {
		component.LOGGER.debug("onStopped - start");
		component.sendChangeEvent();
		component.onStopped();
		component.LOGGER.debug("onStopped - finished");
	}

	@Override
	public void onIdle() {
		component.LOGGER.debug("onIdle - start");
		component.sendChangeEvent();
		component.onIdle();
		component.LOGGER.debug("onIdle - finished");
	}

	@Override
	public void onComplete() {
		component.LOGGER.debug("onComplete - start");
		component.sendChangeEvent();
		component.onComplete();
		component.LOGGER.debug("onComplete - finished");
	}

	@Override
	public void onHeld() {
		component.LOGGER.debug("onHeld - start");
		component.sendChangeEvent();
		component.onHeld();
		component.LOGGER.debug("onHeld - finished");
	}

	@Override
	public void onSuspended() {
		component.LOGGER.debug("onSuspended - start");
		component.sendChangeEvent();
		component.onSuspended();
		component.LOGGER.debug("onSuspended - finished");
	}

	@Override
	public void onAborted() {
		component.LOGGER.debug("onAborted - start");
		component.sendChangeEvent();
		component.onAborted();
		component.LOGGER.debug("onAborted - finished");
	}

	/*
	 * default ActiveStatesHandler implementation -> notify Basys Middleware
	 */

	@Override
	public void onResetting() {
		component.LOGGER.debug("onResetting - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onResetting();
		} else {
			component.onResetting();
		}
		component.LOGGER.debug("onResetting - finished");
	}

	@Override
	public void onStarting() {
		component.LOGGER.debug("onStarting - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onStarting();
		} else {
			component.onStarting();
		}
		component.LOGGER.debug("onStarting - finished");
	}

	@Override
	public void onExecute() {
		component.LOGGER.debug("onExecute - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onExecute();
		} else {
			component.onExecute();
		}
		component.LOGGER.debug("onExecute - finished");
	}

	@Override
	public void onCompleting() {
		component.LOGGER.debug("onCompleting - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onCompleting();
		} else {
			component.onCompleting();
		}
		component.LOGGER.debug("onCompleting - finished");
	}

	@Override
	public void onHolding() {
		component.LOGGER.debug("onHolding - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onHolding();
		} else {
			component.onHolding();
		}
		component.LOGGER.debug("onHolding - finished");
	}

	@Override
	public void onUnholding() {
		component.LOGGER.debug("onUnholding - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onUnholding();
		} else {
			component.onUnholding();
		}
		component.LOGGER.debug("onUnholding - finished");
	}

	@Override
	public void onSuspending() {
		component.LOGGER.debug("onSuspending - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onSuspending();
		} else {
			component.onSuspending();
		}
		component.LOGGER.debug("onSuspending - finished");
	}

	@Override
	public void onUnsuspending() {
		component.LOGGER.debug("onUnsuspending - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onUnsuspending();
		} else {
			component.onUnsuspending();
		}
		component.LOGGER.debug("onUnsuspending - finished");
	}

	@Override
	public void onAborting() {
		component.LOGGER.debug("onAborting - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onAborting();
		} else {
			component.onAborting();
		}
		component.LOGGER.debug("onAborting - finished");
	}

	@Override
	public void onClearing() {
		component.LOGGER.debug("onClearing - start");
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onClearing();
		} else {
			component.onClearing();
		}
		component.LOGGER.debug("onClearing - finished");
	}

	@Override
	public void onStopping() {
		component.LOGGER.debug("onStopping - start");		
		component.sendChangeEvent();
		if (component.getExecutionMode() == ExecutionMode.SIMULATION) {
			simulatedStatesHandler.onStopping();
		} else {
			component.onStopping();
		}
		component.LOGGER.debug("onStopping - finished");
	}

}
