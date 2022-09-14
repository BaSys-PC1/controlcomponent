package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;


import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentOperationsType;
import de.dfki.cos.basys.controlcomponent.util.Strings;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.methods.UaMethod;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class ControlComponentOperationsNode extends FolderTypeNode implements ControlComponentOperationsType {

	public ControlComponentOperationsNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
			QualifiedName browseName, LocalizedText displayName, LocalizedText description, UInteger writeMask,
			UInteger userWriteMask, UByte eventNotifier) {
		super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UaMethod getResetMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Reset"));
	}

	@Override
	public UaMethod getStartMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Start"));
	}

	@Override
	public UaMethod getStopMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Stop"));
	}

	@Override
	public UaMethod getHoldMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Hold"));
	}

	@Override
	public UaMethod getUnholdMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Unhold"));
	}

	@Override
	public UaMethod getSuspendMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Suspend"));
	}

	@Override
	public UaMethod getUnsuspendMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Unsuspend"));
	}

	@Override
	public UaMethod getAbortMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Abort"));
	}

	@Override
	public UaMethod getClearMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Clear"));
	}

	@Override
	public UaMethod getFreeMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Free"));
	}

	@Override
	public UaMethod getOccupyMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Occupy"));
	}

	@Override
	public UaMethod getPrioMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Prio"));
	}

	@Override
	public UaMethod getAutoMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Auto"));
	}

	@Override
	public UaMethod getSemiAutoMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.SemiAuto"));
	}

	@Override
	public UaMethod getManualMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Manual"));
	}

	@Override
	public UaMethod getSimulateMethod() {
		return getMethodOrNull(Strings.getString("ControlComponent.BN.Simulate"));
	}

	@Override
	public UaMethod getOperationModeMethod(String opmode) {
		return getMethodOrNull(opmode);
	}

//	public UaMethod getMethodOrNull(String name) {
//		try {
//			return getMethod(name);
//		} catch (UaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public UaMethod getMethodOrNull(String methodName) {		
		try {

			NodeId methodNodeId = getMethodNodeId(methodName);
			UaMethodNode methodNode = (UaMethodNode) client.getAddressSpace().getNode(methodNodeId);
			UaMethod method = new UaMethod(client, this, methodNode,  new Argument[0],  new Argument[0]);
			//Argument[] in = methodNode.readInputArgumentsAsync().get();
			//Argument[] out = methodNode.readOutputArgumentsAsync().get();
			//UaMethod method = new UaMethod(client, this, methodNode,  in, out);
			return method;
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public NodeId getMethodNodeId(String methodName) {
		return getMethodNodeId(new QualifiedName(getNodeId().getNamespaceIndex(), methodName));
	}
	
	public NodeId getMethodNodeId(QualifiedName browseName) {
		try {
			return getMethodNodeIdAsync(browseName).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CompletableFuture<NodeId> getMethodNodeIdAsync(QualifiedName browseName) {
		UInteger nodeClassMask = uint(NodeClass.Method.getValue());

        UInteger resultMask = uint(BrowseResultMask.All.getValue());
        
        CompletableFuture<BrowseResult> future = client.browse(new BrowseDescription(
                getNodeId(),
                BrowseDirection.Forward,
                Identifiers.HierarchicalReferences,
                true,
                nodeClassMask,
                resultMask
            ));

			return future.thenApply( result -> {	
				List<ReferenceDescription> references = l(result.getReferences());
				Optional<NodeId> nodeId = references.stream()
						.filter(ref -> ref.getBrowseName().equals(browseName))
						.findFirst()
						.map(ref -> ref.getNodeId().local(client.getNamespaceTable()).orElse(null));
				return nodeId.orElse(null);
			});
       
    	//return references.stream().filter(ref -> ref.getBrowseName().equals(browseName)).findFirst().map(ref -> ref.getNodeId().local()); 
	}
}
