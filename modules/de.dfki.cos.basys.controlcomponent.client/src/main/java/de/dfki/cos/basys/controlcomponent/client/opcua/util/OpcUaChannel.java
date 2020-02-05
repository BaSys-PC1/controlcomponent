package de.dfki.cos.basys.controlcomponent.client.opcua.util;


import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.stack.client.DiscoveryClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentOperationsType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentStatusType;
import de.dfki.cos.basys.controlcomponent.client.opcua.types.ControlComponentType;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.KeyStoreLoader;


public class OpcUaChannel  {

	public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public UShort nsIndex;
    
	protected OpcUaClient opcuaClient;
	protected SecurityPolicy securityPolicy = SecurityPolicy.None;
	protected IdentityProvider identityProvider;

    private final AtomicLong clientHandles = new AtomicLong(1L);
    
    public OpcUaChannel(Properties config) {
    	if (config.containsKey("username")) {
	    	String username = config.getProperty(StringConstants.username);
	    	String password = config.getProperty(StringConstants.password,"");
	    	identityProvider = new UsernameProvider(username, password);
    	} else {
    		identityProvider = new AnonymousProvider();
    	}
    }

	public void open(String connectionString) throws OpcUaException {			
		try {
			if (opcuaClient == null) {
				opcuaClient = createClient(connectionString);
				opcuaClient.connect().get();
				
				nsIndex = opcuaClient.getNamespaceTable().getIndex(NodeIds.NAMESPACE_URI);
				NodeIds.initNodeIds(nsIndex);
				opcuaClient.getTypeRegistry().registerType(NodeIds.ControlComponentType, ControlComponentType.class, ControlComponentNode.class, ControlComponentNode::new);
				opcuaClient.getTypeRegistry().registerType(NodeIds.StatusType, ControlComponentStatusType.class, ControlComponentStatusNode.class, ControlComponentStatusNode::new);
				opcuaClient.getTypeRegistry().registerType(NodeIds.OperationsType, ControlComponentOperationsType.class, ControlComponentOperationsNode.class, ControlComponentOperationsNode::new);
				opcuaClient.getDataTypeManager().registerCodec(NodeIds.StatusDataType_Encoding_DefaultBinary, new ControlComponentStatusDataType.Codec().asBinaryCodec());
				opcuaClient.getDataTypeManager().registerCodec(NodeIds.StatusDataType_Encoding_DefaultXml, new ControlComponentStatusDataType.Codec().asXmlCodec());

			}
		} catch (Exception  e) {
			throw new OpcUaException(e);
		}
	}

	public void close() throws OpcUaException {
		try {			
			opcuaClient.disconnect().get();
			opcuaClient = null;
		} catch (InterruptedException | ExecutionException e) {
			throw new OpcUaException(e);
		}
	}
	
	public OpcUaClient getClient() {
		return opcuaClient;
	}
	
	public SecurityPolicy getSecurityPolicy() {
		return securityPolicy;
	}

	public void setSecurityPolicy(SecurityPolicy securityPolicy) {
		this.securityPolicy = securityPolicy;
	}

	public IdentityProvider getIdentityProvider() {
		return identityProvider;
	}

	public void setIdentityProvider(IdentityProvider identityProvider) {
		this.identityProvider = identityProvider;
	}
	
	public UShort getNsIndex() {
		return nsIndex;
	}

    private OpcUaClient createClient(String connectionString) throws Exception {
        Path securityTempDir = Paths.get(System.getProperty("java.io.tmpdir"), "opcua_client_security");
        Files.createDirectories(securityTempDir);
        if (!Files.exists(securityTempDir)) {
            throw new Exception("unable to create security dir: " + securityTempDir);
        }
        LoggerFactory.getLogger(getClass())
            .info("security temp dir: {}", securityTempDir.toAbsolutePath());

        KeyStoreLoader loader = new KeyStoreLoader().load(securityTempDir);

        List<EndpointDescription> endpoints;

        try {
            endpoints = DiscoveryClient.getEndpoints(connectionString).get();
        } catch (Throwable ex) {
            // try the explicit discovery endpoint as well
            String discoveryUrl = connectionString;

            if (!discoveryUrl.endsWith("/")) {
                discoveryUrl += "/";
            }
            discoveryUrl += "discovery";

            LOGGER.info("Trying explicit discovery URL: {}", discoveryUrl);
            endpoints = DiscoveryClient.getEndpoints(discoveryUrl).get();
        }

        EndpointDescription endpoint = endpoints.stream()
            .filter(e -> e.getSecurityPolicyUri().equals(securityPolicy.getUri()))
            //.filter(e -> true) // an additional filter
            .findFirst()
            .orElseThrow(() -> new Exception("no desired endpoints returned"));

        LOGGER.info("Using endpoint: {} [{}/{}]",
            endpoint.getEndpointUrl(), securityPolicy, endpoint.getSecurityMode());

        OpcUaClientConfig config = OpcUaClientConfig.builder()
            .setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
            .setApplicationUri("urn:eclipse:milo:examples:client")
            .setCertificate(loader.getClientCertificate())
            .setKeyPair(loader.getClientKeyPair())
            .setEndpoint(endpoint)
            .setIdentityProvider(getIdentityProvider())
            .setRequestTimeout(uint(5000))
            .build();

        return OpcUaClient.create(config);
    }

	public <T> T readValue(final NodeId nodeId) throws OpcUaException {
		CompletableFuture<DataValue> cf = opcuaClient.readValue(0, TimestampsToReturn.Both, nodeId);
		try {
			return (T) cf.get().getValue().getValue();
		} catch (InterruptedException | ExecutionException e) {
			throw new OpcUaException(e);
		}
	}

	public <T> List<T> readValues(final List<NodeId> nodeIds) throws OpcUaException {
		CompletableFuture<List<DataValue>> cf = opcuaClient.readValues(0, TimestampsToReturn.Both, nodeIds);
		try {
			List<T> result = new ArrayList<T>(nodeIds.size());
			final List<DataValue> values = cf.get();
			values.forEach(val -> result.add((T) val.getValue().getValue()));
			return result;
		} catch (InterruptedException | ExecutionException e) {
			throw new OpcUaException(e);
		}
	}

	public StatusCode writeValue(final NodeId nodeId, final Object value) throws OpcUaException {
		CompletableFuture<StatusCode> cf = opcuaClient.writeValue(nodeId, new DataValue(new Variant(value)));
		try {
			StatusCode result = cf.get();
			return result;
		} catch (InterruptedException | ExecutionException e) {
			throw new OpcUaException(e);
		}
	}

	public List<StatusCode> writeValues(final List<NodeId> nodeIds, final List<Object> values)
			throws OpcUaException {
		List<DataValue> val = new ArrayList<DataValue>(values.size());
		CompletableFuture<List<StatusCode>> cf = opcuaClient.writeValues(nodeIds, val);
		try {
			List<StatusCode> result = cf.get();
			return result;
		} catch (InterruptedException | ExecutionException e) {
			throw new OpcUaException(e);
		}
	}

	public <T> T invokeMethod(final NodeId objectNode, final NodeId methodNode, final Object... inputs) throws OpcUaException  {
		try {
			return (T) _invokeMethod(objectNode, methodNode, inputs).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new OpcUaException(e);
		}
	}
	
	public CompletableFuture<Object> invokeMethodAsync(final NodeId objectNode, final NodeId methodNode, final Object... inputs) throws OpcUaException  {
		return  _invokeMethod(objectNode, methodNode, inputs);
	}

//	public <T> T invokeVoidMethod(final NodeId objectNode, final NodeId methodNode, final Object... inputs) throws OpcUaException  {
//		try {
//			return (T) _invokeMethod(objectNode, methodNode, inputs).get();
//		} catch (InterruptedException | ExecutionException e) {
//			throw new OpcUaException(e);
//		}
//	}
	
	
	public <T> CompletableFuture<T> _invokeMethod(final NodeId objectNode, final NodeId methodNode, final Object... inputs) {

		Variant[] inVariants = new Variant[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			inVariants[i] = new Variant(inputs[i]);
		}
		final CallMethodRequest request = new CallMethodRequest(objectNode, methodNode, inVariants);

		return opcuaClient.call(request).thenCompose(result -> {
			final StatusCode statusCode = result.getStatusCode();

			if (statusCode.isGood()) {
				if (result.getOutputArguments() != null && result.getOutputArguments().length > 0) {				
					final T value = (T) l(result.getOutputArguments()).get(0).getValue();
					return CompletableFuture.completedFuture(value);
				} else {
					return CompletableFuture.completedFuture(null);
				}
			} else {
				final CompletableFuture<T> f = new CompletableFuture<>();
				f.completeExceptionally(new UaException(statusCode));
				return f;
			}
		});
	}
	
	public CompletableFuture<ComponentOrderStatus> callMethod(final NodeId objectNode, final NodeId methodNode) {

//		Variant[] inVariants = new Variant[1];
//		inVariants[0] = new Variant(occupierId);
		Variant[] inVariants = new Variant[0];
		
		final CallMethodRequest request = new CallMethodRequest(objectNode, methodNode, inVariants);

		return opcuaClient.call(request).thenCompose(result -> {
			final StatusCode statusCode = result.getStatusCode();

			if (statusCode.isGood()) {
				ComponentOrderStatus status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message(statusCode.toString()).build();
				return CompletableFuture.completedFuture(status);
//				if (result.getOutputArguments() != null && result.getOutputArguments().length > 0) {				
//					final String orderStatus = (String) result.getOutputArguments()[0].getValue();		
//					final String message = (String) result.getOutputArguments()[1].getValue();
//					ComponentOrderStatus status = new ComponentOrderStatus.Builder().status(OrderStatus.get(orderStatus)).message(message).build();
//					return CompletableFuture.completedFuture(status);
//				} else {
//					ComponentOrderStatus status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("Invalid output arguments").build();					
//					return CompletableFuture.completedFuture(status);
//				}
			} else {
				ComponentOrderStatus status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(statusCode.toString()).build();					
				return CompletableFuture.completedFuture(status);
//				final CompletableFuture<ComponentOrderStatus> f = new CompletableFuture<>();
//				f.completeExceptionally(new UaException(statusCode));
//				return f;
			}
		});
	}
	

    public List<Node> browseNode(NodeId browseRoot) {
       
    	try {
            List<Node> nodes = opcuaClient.getAddressSpace().browse(browseRoot).get();
            return nodes;
//            for (Node node : nodes) {
//                LOGGER.info("{} Node={}", indent, node.getBrowseName().get().getName());
//
//                // recursively browse to children
//                browseNode(indent + "  ", node.getNodeId().get());
//            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Browsing nodeId={} failed: {}", browseRoot, e.getMessage(), e);
        }
    	return null;
    }

//	public CompletableFuture<Void> _invokeVoidMethod(final NodeId objectNode, final NodeId methodNode, final Object... inputs) {
//
//		Variant[] inVariants = new Variant[inputs.length];
//		for (int i = 0; i < inputs.length; i++) {
//			inVariants[i] = new Variant(inputs[i]);
//		}
//		final CallMethodRequest request = new CallMethodRequest(objectNode, methodNode, inVariants);
//
//		return opcuaClient.call(request).thenCompose(result -> {
//			final StatusCode statusCode = result.getStatusCode();
//
//			if (statusCode.isGood()) {
//				return CompletableFuture.completedFuture(null);
//			} else {
//				final CompletableFuture<Void> f = new CompletableFuture<>();
//				f.completeExceptionally(new UaException(statusCode));
//				return f;
//			}
//		});
//	}

	
    public UaSubscription subscribeToValue(NodeId node, BiConsumer<UaMonitoredItem, DataValue> valueConsumer) throws Exception {
  

        // create a subscription @ 1000ms
        UaSubscription subscription = opcuaClient.getSubscriptionManager().createSubscription(250.0).get();

        // subscribe to the Value attribute of the server's CurrentTime node
		ReadValueId readValueId = new ReadValueId(node, AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE);

        // important: client handle must be unique per item
        UInteger clientHandle = uint(clientHandles.getAndIncrement());

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            250.0,     // sampling interval
            null,       // filter, null means use default
            uint(10),   // queue size
            true        // discard oldest
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId, MonitoringMode.Reporting, parameters);

        // when creating items in MonitoringMode.Reporting this callback is where each item needs to have its
        // value/event consumer hooked up. The alternative is to create the item in sampling mode, hook up the
        // consumer after the creation call completes, and then change the mode for all items to reporting.
        //BiConsumer<UaMonitoredItem, Integer> onItemCreated = (item, id) -> item.setValueConsumer(this::onSubscriptionValue);
        BiConsumer<UaMonitoredItem, Integer> onItemCreated = (item, id) -> item.setValueConsumer(valueConsumer);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            newArrayList(request),
            onItemCreated
        ).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                LOGGER.info("item created for nodeId={}", item.getReadValueId().getNodeId());
            } else {
                LOGGER.warn(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }
        
        return subscription;

    }

//    protected void onSubscriptionValue(UaMonitoredItem item, DataValue value) {
//        LOGGER.info(
//                "subscription value received: item={}, value={}",
//                item.getReadValueId().getNodeId(), value.getValue());
//        
//        System.out.println("subscription value received: item="+ item.getReadValueId().getNodeId() + ", value=" + value.getValue());
//    }
	
	
	
	
	
	
	
	
	

}
