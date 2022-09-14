package de.dfki.cos.basys.controlcomponent.impl;

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
		component.notifyChange();
		component.onStopped();
		component.LOGGER.debug("onStopped - finished");
	}

	@Override
	public void onIdle() {
		component.LOGGER.debug("onIdle - start");
		component.notifyChange();
		component.onIdle();
		component.LOGGER.debug("onIdle - finished");
	}

	@Override
	public void onComplete() {
		component.LOGGER.debug("onComplete - start");
		component.notifyChange();
		component.onComplete();
		component.LOGGER.debug("onComplete - finished");
	}

	@Override
	public void onHeld() {
		component.LOGGER.debug("onHeld - start");
		component.notifyChange();
		component.onHeld();
		component.LOGGER.debug("onHeld - finished");
	}

	@Override
	public void onSuspended() {
		component.LOGGER.debug("onSuspended - start");
		component.notifyChange();
		component.onSuspended();
		component.LOGGER.debug("onSuspended - finished");
	}

	@Override
	public void onAborted() {
		component.LOGGER.debug("onAborted - start");
		component.notifyChange();
		component.onAborted();
		component.LOGGER.debug("onAborted - finished");
	}

	/*
	 * default ActiveStatesHandler implementation -> notify Basys Middleware
	 */

	@Override
	public void onResetting() {
		component.LOGGER.debug("onResetting - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onResetting();
		} else {
			component.onResetting();
		}
		component.LOGGER.debug("onResetting - finished");
	}

	@Override
	public void onStarting() {
		component.LOGGER.debug("onStarting - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onStarting();
		} else {
			component.onStarting();
		}
		component.LOGGER.debug("onStarting - finished");
	}

	@Override
	public void onExecute() {
		component.LOGGER.debug("onExecute - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onExecute();
		} else {
			component.onExecute();
		}
		component.LOGGER.debug("onExecute - finished");
	}

	@Override
	public void onCompleting() {
		component.LOGGER.debug("onCompleting - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onCompleting();
		} else {
			component.onCompleting();
		}
		component.LOGGER.debug("onCompleting - finished");
	}

	@Override
	public void onHolding() {
		component.LOGGER.debug("onHolding - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onHolding();
		} else {
			component.onHolding();
		}
		component.LOGGER.debug("onHolding - finished");
	}

	@Override
	public void onUnholding() {
		component.LOGGER.debug("onUnholding - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onUnholding();
		} else {
			component.onUnholding();
		}
		component.LOGGER.debug("onUnholding - finished");
	}

	@Override
	public void onSuspending() {
		component.LOGGER.debug("onSuspending - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onSuspending();
		} else {
			component.onSuspending();
		}
		component.LOGGER.debug("onSuspending - finished");
	}

	@Override
	public void onUnsuspending() {
		component.LOGGER.debug("onUnsuspending - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onUnsuspending();
		} else {
			component.onUnsuspending();
		}
		component.LOGGER.debug("onUnsuspending - finished");
	}

	@Override
	public void onAborting() {
		component.LOGGER.debug("onAborting - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onAborting();
		} else {
			component.onAborting();
		}
		component.LOGGER.debug("onAborting - finished");
	}

	@Override
	public void onClearing() {
		component.LOGGER.debug("onClearing - start");
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onClearing();
		} else {
			component.onClearing();
		}
		component.LOGGER.debug("onClearing - finished");
	}

	@Override
	public void onStopping() {
		component.LOGGER.debug("onStopping - start");		
		component.notifyChange();
		if (component.getExecutionMode() == ExecutionMode.SIMULATE) {
			simulatedStatesHandler.onStopping();
		} else {
			component.onStopping();
		}
		component.LOGGER.debug("onStopping - finished");
	}

}
