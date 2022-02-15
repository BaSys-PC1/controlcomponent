package de.dfki.cos.basys.controlcomponent.server.opcua.loader;

import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ControlComponentObjectTypeLoader {

	private final UaNodeContext context;
	private final NodeManager<UaNode> nodeManager;
	private final UShort nsIndex;

	public ControlComponentObjectTypeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager, UShort nsIndex) {
		this.context = context;
		this.nodeManager = nodeManager;
		this.nsIndex = nsIndex;
	}
	
	public void buildNodes() {
		
	}
}