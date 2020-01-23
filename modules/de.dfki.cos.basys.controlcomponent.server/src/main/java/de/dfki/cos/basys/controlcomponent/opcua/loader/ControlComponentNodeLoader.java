package de.dfki.cos.basys.controlcomponent.opcua.loader;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaDataTypeLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaMethodLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaObjectLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaObjectTypeLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaReferenceTypeLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaVariableLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaVariableTypeLoader;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaViewLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class ControlComponentNodeLoader {

	private final UaNodeContext context;
	private final NodeManager<UaNode> nodeManager;
	private final UShort nsIndex;

	public ControlComponentNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager, UShort nsIndex) {
		this.context = context;
		this.nodeManager = nodeManager;
		this.nsIndex = nsIndex;
	}
	
     public void loadNodes() throws Exception {
        //new UaDataTypeLoader(context, nodeManager).buildNodes();
        //new UaMethodLoader(context, nodeManager).buildNodes();
        //new UaObjectLoader(context, nodeManager).buildNodes();
        new ControlComponentObjectTypeLoader(context, nodeManager, nsIndex).buildNodes();
        //new UaReferenceTypeLoader(context, nodeManager).buildNodes();
        //new UaVariableLoader(context, nodeManager).buildNodes();
        new ControlComponentVariableTypeLoader(context, nodeManager, nsIndex).buildNodes();
        //new UaViewLoader(context, nodeManager).buildNodes();
    }
}