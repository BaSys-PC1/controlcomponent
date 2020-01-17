package de.dfki.cos.basys.controlcomponent.server;

import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseObjectType;

import de.dfki.cos.basys.controlcomponent.ControlComponent;

public interface ControlComponentType extends BaseObjectType {
	ControlComponent getControlComponent();
}
