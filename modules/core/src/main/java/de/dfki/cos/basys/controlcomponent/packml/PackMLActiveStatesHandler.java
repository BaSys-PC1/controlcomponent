package de.dfki.cos.basys.controlcomponent.packml;

public interface PackMLActiveStatesHandler {

	void onResetting();

	void onStarting();

	void onExecute();

	void onCompleting();

	void onHolding();

	void onUnholding();

	void onSuspending();

	void onUnsuspending();

	void onAborting();

	void onClearing();

	void onStopping();

}
