package de.dfki.cos.basys.controlcomponent.server.opcua.types;

import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseObjectType;

import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentStatusNode;

public interface ControlComponentType extends BaseObjectType {
		
	ControlComponentStatusNode getControlComponentStatusNode();

	ControlComponentStatusDataType getControlComponentStatus();

	ControlComponentOperationsNode getControlComponentOperationsNode();
	
	FolderTypeNode getControlComponentVariables();
}
