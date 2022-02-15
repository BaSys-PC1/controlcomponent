package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentType;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.util.Strings;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.BaseObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class ControlComponentNode extends BaseObjectTypeNode implements ControlComponentType {
    
    public ControlComponentNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask,
			UByte eventNotifier) {
		super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ControlComponentStatusNode getControlComponentStatusNode() {
		try {
			return (ControlComponentStatusNode) getVariableComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Status"));
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ControlComponentOperationsNode getControlComponentOperationsNode() {
		try {
			UaObjectNode node = getObjectComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Operations"));
			return (ControlComponentOperationsNode) node;
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FolderTypeNode getControlComponentVariablesNode() {
		try {
			return (FolderTypeNode) getObjectComponent(NodeIds.NAMESPACE_URI, Strings.getString("ControlComponent.BN.Variables"));
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ControlComponentStatusDataType getControlComponentStatus() {
		DataValue value =  getControlComponentStatusNode().getValue();
		Variant v = value.getValue();		
		ExtensionObject xo = (ExtensionObject) v.getValue();

		ControlComponentStatusDataType decoded = (ControlComponentStatusDataType) xo.decode(
            client.getSerializationContext()
        );
		
		return decoded;
	}


}
