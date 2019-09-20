package de.dfki.cos.basys.controlcomponent.packml;

public interface PackMLWaitStatesHandler {

	void onStopped();

	void onIdle();

	void onComplete();

	void onHeld();

	void onSuspended();

	void onAborted();

}
