package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseObjectType;

import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentStatusNode;

public interface ControlComponentType extends BaseObjectType {

	ControlComponentStatusNode getControlComponentStatusNode();
	ControlComponentOperationsNode getControlComponentOperationsNode();
	FolderTypeNode getControlComponentVariablesNode();
	ControlComponentStatusDataType getControlComponentStatus();
	
}
