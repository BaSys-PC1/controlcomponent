package de.dfki.cos.basys.controlcomponent.core;

public interface ControlComponent extends StatusInterface, CommandInterface {

	String getId();
	
	String getName();
	
	//ComponentConfiguration getConfig();
	
	//ComponentInfo getComponentInfo();

	void activate() throws ControlComponentException;

	void deactivate() throws ControlComponentException;
	
	boolean isActivated();
	
	boolean isConnectedToExternal();
	
}
