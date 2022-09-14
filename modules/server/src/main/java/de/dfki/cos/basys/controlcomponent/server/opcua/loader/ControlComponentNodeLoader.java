package de.dfki.cos.basys.controlcomponent.server.opcua.loader;

import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

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