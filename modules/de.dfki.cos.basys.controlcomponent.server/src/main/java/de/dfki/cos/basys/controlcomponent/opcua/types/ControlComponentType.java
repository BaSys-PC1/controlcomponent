package de.dfki.cos.basys.controlcomponent.opcua.types;

import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseObjectType;

import de.dfki.cos.basys.controlcomponent.opcua.objects.ControlComponentStatusNode;

public interface ControlComponentType extends BaseObjectType {
		
	ControlComponentStatusNode getControlComponentStatusNode();
	
	ControlComponentStatusDataType getControlComponentStatus();
}
