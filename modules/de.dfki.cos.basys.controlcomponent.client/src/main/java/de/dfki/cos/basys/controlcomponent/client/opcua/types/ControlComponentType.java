package de.dfki.cos.basys.controlcomponent.client.opcua.types;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseObjectType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public interface ControlComponentType extends BaseObjectType {

	CompletableFuture<? extends ControlComponentStatusType> getControlComponentStatusNode();

	CompletableFuture<ControlComponentStatusDataType> getControlComponentStatus();
}
