package de.dfki.cos.basys.controlcomponent.client.opcua.nodes;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

import org.apache.commons.jexl3.internal.IntegerRange.Descending;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;

import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentOperationsType;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentOperationsNode extends FolderNode implements ControlComponentOperationsType {
	

	public ControlComponentOperationsNode(OpcUaClient client, NodeId nodeId) {
		super(client, nodeId);
	}

	@Override
	public CompletableFuture<NodeId> getResetMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Reset"));		
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
}

	@Override
	public CompletableFuture<NodeId> getStartMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Start"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getStopMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Stop"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getHoldMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Hold"));
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getUnholdMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Unhold"));
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getSuspendMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Suspend"));
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getUnsuspendMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Unsuspend"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getAbortMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Abort"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getClearMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Clear"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getFreeMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Free"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getOccupyMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Occupy"));
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getPrioMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Prio"));
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getAutoMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Auto"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getSemiAutoMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.SemiAuto"));	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getManualMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Manual"));
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getSimulateMethodNodeId() {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), Strings.getString("ControlComponent.BN.Simulate"));		
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}

	@Override
	public CompletableFuture<NodeId> getOperationModeMethodNodeId(String opmode) {
		QualifiedName name = new QualifiedName(nodeId.getNamespaceIndex(), opmode);	
		//return getComponent(name).thenCompose(n-> n.getNodeId());
		return getMethodNodeId(name);
	}
	
	public CompletableFuture<NodeId> getMethodNodeId(QualifiedName browseName) {
		UInteger nodeClassMask = uint(NodeClass.Method.getValue());

        UInteger resultMask = uint(BrowseResultMask.All.getValue());
        
        CompletableFuture<BrowseResult> future = client.browse(new BrowseDescription(
                nodeId,
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
