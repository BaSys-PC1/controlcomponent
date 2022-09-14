package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentStatusNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseObjectType;

public interface ControlComponentType extends BaseObjectType {

	ControlComponentStatusNode getControlComponentStatusNode();
	ControlComponentOperationsNode getControlComponentOperationsNode();
	FolderTypeNode getControlComponentVariablesNode();
	ControlComponentStatusDataType getControlComponentStatus();
	
}
