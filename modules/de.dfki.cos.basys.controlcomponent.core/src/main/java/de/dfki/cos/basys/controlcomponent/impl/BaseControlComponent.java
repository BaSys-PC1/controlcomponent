package de.dfki.cos.basys.controlcomponent.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.eclipse.basyx.aas.manager.ConnectedAssetAdministrationShellManager;
import org.eclipse.basyx.aas.metamodel.connected.ConnectedAssetAdministrationShell;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IIdentifier;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.aas.event.impl.EventMessage;
import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.common.component.ServiceProvider;
import de.dfki.cos.basys.common.component.impl.ServiceComponent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;
import de.dfki.cos.basys.controlcomponent.OccupationState;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.OrderStatusCodes;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.StringConstants;
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;
import de.dfki.cos.basys.controlcomponent.packml.PackMLStateChangeNotifier;
import de.dfki.cos.basys.controlcomponent.packml.PackMLStatusInterface;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;

public class BaseControlComponent<T> extends ServiceComponent<T> implements ControlComponent, PackMLActiveStatesHandler, PackMLWaitStatesHandler, PackMLStateChangeNotifier {

	protected boolean disableExecutionModeChange, disableOccupationCheck, disableServiceMock = false;
	protected SharedParameterSpaceImpl parameterSpace;

	private Map<String, OperationMode> operationModes = new HashMap<>();
	private OccupationState occupationLevel = OccupationState.FREE;
	private String occupierId = "INIT";
	private OperationMode operationMode = null;
	private String workState = "";
	private String errorMessage = "OK";
	private int errorCode = 0;
	
	private PackMLUnit packmlUnit;
	
	public BaseControlComponent(Properties config) {
		super(config);
		this.parameterSpace = new SharedParameterSpaceImpl(this);				
	}
	
	public BaseControlComponent(Properties config, ServiceProvider<T> serviceProvider) {
		super(config, serviceProvider);
		this.parameterSpace = new SharedParameterSpaceImpl(this);				
	}

	@Override
	protected void doActivate() {
		

		
		DefaultOperationMode defaultMode = new DefaultOperationMode(this);		
		this.operationMode = defaultMode; 
		this.operationModes.put(defaultMode.getShortName(), defaultMode);	
		
		packmlUnit = new PackMLUnit(getName());
		packmlUnit.setActiveStatesHandler(this);
		packmlUnit.setWaitStatesHandler(this);
		packmlUnit.setChangeNotifier(this);
		packmlUnit.initialize();
		
		if (config.getProperty("executionMode") != null) {
			ExecutionMode mode = ExecutionMode.valueOf(config.getProperty("executionMode"));
			packmlUnit.setExecutionMode(mode, occupierId);
			LOGGER.info("set component to " + mode + " mode");
		}	
		
		if (config.getProperty("disableExecutionModeChange") != null) {
			disableExecutionModeChange = Boolean.parseBoolean(config.getProperty("disableExecutionModeChange"));
			LOGGER.info("disableExecutionModeChange = " + disableExecutionModeChange);
		}	

		if (config.getProperty("disableOccupationCheck") != null) {
			disableOccupationCheck = Boolean.parseBoolean(config.getProperty("disableOccupationCheck"));
			LOGGER.info("disableOccupationCheck = " + disableOccupationCheck);
		}	
		
		if (config.getProperty("disableServiceMock") != null) {
			disableServiceMock = Boolean.parseBoolean(config.getProperty("disableServiceMock"));
			LOGGER.info("disableServiceMock = " + disableServiceMock);
		}	
		
		registerOperationModes();
		
		for (OperationMode operationMode : operationModes.values()) {
			parameterSpace.registerOperationMode(operationMode);
		}
	}
	
	@Override
	protected void doDeactivate() throws ComponentException {
		for (OperationMode operationMode : operationModes.values()) {
			parameterSpace.unregisterOperationMode(operationMode);
		}
	}

//	public boolean isExecutionModeChangeDisabled() {
//		return disableExecutionModeChange;
//	}
//
//	public boolean isOccupationCheckDisabled() {
//		return disableOccupationCheck;
//	}
//
//	public boolean isServiceMockDisabled() {
//		return disableServiceMock;
//	}

	/*
	 * AAS/Submodel-related methods
	 */
	

	@Override
	public Identifier getAssetId() {
		return new Identifier(IdentifierType.IRI,config.getProperty("asset.id", ""));
	}
	
	@Override
	public Identifier getAasId() {
		return new Identifier(IdentifierType.IRI,config.getProperty("aas.id", ""));
	}

	@Override
	public Identifier getSubmodelId() {
		return new Identifier(IdentifierType.IRI,config.getProperty("submodel.id", ""));
	}
	
	private Submodel submodel = null; 
	
	@Override
	public Submodel getSubmodel() {
		if (submodel == null) {
			submodel = ControlComponentSubmodelFactory.createInterfaceSubmodel(this);
		}
		return submodel; 
	}
	
//	private SubModelProvider provider = null; 
//	
//	@Override
//	public IModelProvider getModelProvider() {	
//		if (provider == null) {
//			provider = new SubModelProvider(getSubmodel());
//		}
//		return provider;
//	}

	@Override
	public SubmodelDescriptor getModelDescriptor(String endpoint) {
		return new SubmodelDescriptor(getSubmodel(), endpoint + "/" + getSubmodel().getIdShort() + "/submodel");
	}
	
	/*
	 * CommandInterface methods
	 */
	
	protected void registerOperationModes() {

	};
	
	public ComponentOrderStatus registerOperationMode(OperationMode operationMode) {
		return registerOperationMode(operationMode, "INIT");
	}
	
	@Override
	public ComponentOrderStatus registerOperationMode(OperationMode operationMode, String senderId) {
		ComponentOrderStatus status = null;
		
		if (senderId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenInvalid).message("senderId must not be null").build();				
		} else if (!senderId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenRejected).message("senderId does not match current occupier").build();
		} else {
			if (operationModes.containsKey(operationMode.getShortName())) {					
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.EntryExists).message("operation mode with short name '" + operationMode.getShortName() + "' already registered. unregister first.").build();
			} else {
				operationModes.put(operationMode.getShortName(), operationMode);
				parameterSpace.registerOperationMode(operationMode);
				if (!"INIT".equals(senderId))
					notifyChange();
				status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("operation mode registered").build();
			}
		}
		
		return status;
	}
	@Override
	public ComponentOrderStatus unregisterOperationMode(String operationModeShortName, String senderId) {
		ComponentOrderStatus status = null;
		
		if (senderId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenInvalid).message("senderId must not be null").build();				
		} else if (!senderId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenRejected).message("senderId does not match current occupier").build();
		} else {
			if (operationModes.containsKey(operationModeShortName)) {
				operationModes.remove(operationModeShortName);
				parameterSpace.unregisterOperationMode(operationMode);
				notifyChange();
				status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("operation mode unregistered").build();
			} else {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.NoEntryExists).message("no operation mode with short name '" + operationModeShortName + "'").build();
			}
		}
	
		return status;
	}
	
	@Override
	public OccupationStatus getOccupationStatus() {
		return new OccupationStatus.Builder().level(getOccupationState()).occupierId(getOccupierId()).build();
	}	
	
	@Override
	public OccupationState getOccupationState() {
		return occupationLevel;
	}

	@Override
	public String getOccupierId() {
		return occupierId;
	}
	
	protected void setOccupationStatus(OccupationState occupationLevel, String senderId) {
		this.occupationLevel = occupationLevel;
		this.occupierId = senderId;
		notifyChange();
	}


	@Override
	public OperationModeInfo getOperationMode() {		
		return operationMode.getInfo();
	}
	
	@Override
	public List<OperationModeInfo> getOperationModes() {			
		OperationModeInfo[] result = operationModes.values().stream().map( new Function<OperationMode, OperationModeInfo>() {
			@Override
			public OperationModeInfo apply(OperationMode t) {
				return t.getInfo();
			}			
		}).toArray(OperationModeInfo[]::new);
		
		return Arrays.asList(result);
	}
	
	@Override
	public String getWorkState() {
		return workState != null ? workState : "";
	}

	public void setWorkState(String workState) {
		this.workState = workState;
		notifyChange();
	}
	
	@Override
	public ErrorStatus getErrorStatus() {
		return new ErrorStatus.Builder().errorCode(errorCode).errorMessage(errorMessage).build();
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage != null ? errorMessage : "";
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorStatus(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		notifyChange();
	}
	
	@Override
	public ExecutionState getExecutionState() {		
		return packmlUnit.getExecutionState();
	}

	@Override
	public ExecutionMode getExecutionMode() {		
		return packmlUnit.getExecutionMode();
	}

	@Override
	public ComponentInfo getInfo() {
		ControlComponentInfo i = new ControlComponentInfo(super.getInfo());
		
		i.setProperty(StringConstants.executionState, getExecutionState().toString());
		i.setProperty(StringConstants.executionMode, getExecutionMode().toString());
		i.setProperty(StringConstants.operationMode, getOperationMode().getShortName());
		i.setProperty(StringConstants.workState, getWorkState());
		i.setProperty(StringConstants.occupationLevel, getOccupationState().toString());
		i.setProperty(StringConstants.occupierId, getOccupierId());
		i.setProperty(StringConstants.errorCode, getErrorCode()+"");
		i.setProperty(StringConstants.errorMessage, getErrorMessage());
		
		i.setProperty("assetId", config.getProperty("asset.id", ""));
		i.setProperty("aasId", config.getProperty("aas.id", ""));
		i.setProperty("submodelId", config.getProperty("submodel.id", ""));
				
		return i;
	}

	@Override
	public ComponentOrderStatus free(String senderId) {
		return occupy(OccupationCommand.FREE, senderId);
	}

	@Override
	public ComponentOrderStatus occupy(String senderId) {
		return occupy(OccupationCommand.OCCUPY, senderId);
	}

	@Override
	public ComponentOrderStatus occupyPriority(String senderId) {
		return occupy(OccupationCommand.PRIO, senderId);
	}

	@Override
	public ComponentOrderStatus occupy(OccupationCommand cmd, String senderId) {
		LOGGER.info("occupy {} (senderId: {})", cmd, senderId);
		ComponentOrderStatus status = canOccupyLevel(senderId, cmd);
		if (status.getStatus() == OrderStatus.ACCEPTED) {
			switch (cmd) {
			case FREE:
				setOccupationStatus(OccupationState.FREE, "");
				break;
			case OCCUPY:
				setOccupationStatus(OccupationState.OCCUPIED, senderId);
				break;
			case PRIO:
				setOccupationStatus(OccupationState.PRIORITY, senderId);
				break;
//			case LOCAL:
//				setOccupationStatus(OccupationState.LOCAL, senderId);
//				break;
			default:
			}
			status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("ok").build();	
		}
		LOGGER.info("occupy - finished with status {} ({})", status.getStatus(), status.getMessage());
		return status;
	}

	@Override
	public ComponentOrderStatus setOperationMode(String opMode, String senderId) {
		LOGGER.info("setOperationMode {} (senderId: {})", opMode, senderId);
		ComponentOrderStatus status = canSetOperationMode(opMode, senderId);		
		if (status.getStatus() == OrderStatus.ACCEPTED) {		
			OperationMode newMode = operationModes.get(opMode);
			this.operationMode = newMode;
			status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("ok").build();	
			notifyChange();		
		}
		LOGGER.info("setOperationMode - finished with status {} ({})", status.getStatus(), status.getMessage());
		return status;
	}
	
	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode, String senderId) {
		LOGGER.info("setExecutionMode {} (senderId: {})", mode, senderId);
		ComponentOrderStatus status = canSetExecutionMode(mode, senderId);		
		if (status.getStatus() == OrderStatus.ACCEPTED) {		
			status = packmlUnit.setExecutionMode(mode, senderId);		
			if (status.getStatus() == OrderStatus.ACCEPTED) {
				notifyChange();
				status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("ok").build();	
			}
		}
		LOGGER.info("setExecutionMode - finished with status {} ({})", status.getStatus(), status.getMessage());
		return status;		
	}

	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String senderId) {
		LOGGER.info("raiseExecutionCommand {} (senderId: {})", command, senderId);
		ComponentOrderStatus status = canRaiseExecutionCommand(command, senderId);
		if (status.getStatus() == OrderStatus.ACCEPTED) {
			status = packmlUnit.raiseExecutionCommand(command, senderId);
		}
		LOGGER.info("raiseExecutionCommand - finished with status {} ({})", status.getStatus(), status.getMessage());
		return status;
	}
	
	@Override
	public ComponentOrderStatus reset(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.RESET, senderId);
	}

	@Override
	public ComponentOrderStatus start(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.START, senderId);
	}

	@Override
	public ComponentOrderStatus stop(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.STOP, senderId);
	}

	@Override
	public ComponentOrderStatus hold(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.HOLD, senderId);
	}

	@Override
	public ComponentOrderStatus unhold(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.UNHOLD, senderId);
	}

	@Override
	public ComponentOrderStatus suspend(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.SUSPEND, senderId);
	}

	@Override
	public ComponentOrderStatus unsuspend(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.UNSUSPEND, senderId);
	}

	@Override
	public ComponentOrderStatus abort(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.ABORT, senderId);
	}

	@Override
	public ComponentOrderStatus clear(String senderId) {
		return raiseExecutionCommand(ExecutionCommand.CLEAR, senderId);
	}
	
	/*
	 * default WaitStatesHandler implementation -> notify Basys Middleware
	 */

	@Override
	public void onStopped() {
		LOGGER.debug("onStopped - start");
		LOGGER.debug("onStopped - finished");
	}

	@Override
	public void onIdle() {
		LOGGER.debug("onIdle - start");
		LOGGER.debug("onIdle - finished");
	}

	@Override
	public void onComplete() {
		LOGGER.debug("onComplete - start");
		LOGGER.debug("onComplete - finished");
	}

	@Override
	public void onHeld() {
		LOGGER.debug("onHeld - start");
		LOGGER.debug("onHeld - finished");
	}

	@Override
	public void onSuspended() {
		LOGGER.debug("onSuspended - start");
		LOGGER.debug("onSuspended - finished");
	}

	@Override
	public void onAborted() {
		LOGGER.debug("onAborted - start");
		LOGGER.debug("onAborted - finished");
	}

	/*
	 * default ActiveStatesHandler implementation -> notify Basys Middleware
	 */

	@Override
	public void onResetting() {
		LOGGER.debug("onResetting - start");
		setErrorStatus(0,"OK"); // triggers notifyChange()		
		if (operationMode!=null) {
			operationMode.onResetting();
		}
		LOGGER.debug("onResetting - finished");
	}

	@Override
	public void onStarting() {
		LOGGER.debug("onStarting - start");
		if (operationMode!=null) {
			operationMode.onStarting();
		}
		LOGGER.debug("onStarting - finished");
	}

	@Override
	public void onExecute() {
		LOGGER.debug("onExecute - start");
		if (operationMode!=null) {
			operationMode.onExecute();
		}
		LOGGER.debug("onExecute - finished");
	}

	@Override
	public void onCompleting() {
		LOGGER.debug("onCompleting - start");
		if (operationMode!=null) {
			operationMode.onCompleting();
		}
		LOGGER.debug("onCompleting - finished");
	}

	@Override
	public void onHolding() {
		LOGGER.debug("onHolding - start");
		if (operationMode!=null) {
			operationMode.onHolding();
		}
		LOGGER.debug("onHolding - finished");
	}

	@Override
	public void onUnholding() {
		LOGGER.debug("onUnholding - start");
		if (operationMode!=null) {
			operationMode.onUnholding();
		}
		LOGGER.debug("onUnholding - finished");
	}

	@Override
	public void onSuspending() {
		LOGGER.debug("onSuspending - start");
		if (operationMode!=null) {
			operationMode.onSuspending();
		}
		LOGGER.debug("onSuspending - finished");
	}

	@Override
	public void onUnsuspending() {
		LOGGER.debug("onUnsuspending - start");
		if (operationMode!=null) {
			operationMode.onUnsuspending();
		}
		LOGGER.debug("onUnsuspending - finished");
	}

	@Override
	public void onAborting() {
		LOGGER.debug("onAborting - start");
		if (operationMode!=null) {
			operationMode.onAborting();
		}
		LOGGER.debug("onAborting - finished");
	}

	@Override
	public void onClearing() {
		LOGGER.debug("onClearing - start");
		if (operationMode!=null) {
			operationMode.onClearing();
		}
		LOGGER.debug("onClearing - finished");
	}

	@Override
	public void onStopping() {
		LOGGER.debug("onStopping - start");		
		if (operationMode!=null) {
			operationMode.onStopping();
		}
		LOGGER.debug("onStopping - finished");
	}
	
	/*
	 * 
	 */


	protected ComponentOrderStatus canOccupyLevel(String senderId, OccupationCommand cmd) {
		ComponentOrderStatus status = null;	
		
		if (disableOccupationCheck) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("occupation check disabled").build();	
			return status;		
		}
		
		if (senderId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenInvalid).message("senderId must not be null").build();	
			return status;
		} 
		
		switch (cmd) {
		case FREE:
			if (!senderId.equals(getOccupierId())) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenRejected).message("senderId does not match current occupier").build();				
			}
			break;
		case OCCUPY:
			if (getOccupationState() == OccupationState.LOCAL || getOccupationState() == OccupationState.PRIORITY || getOccupationState() == OccupationState.OCCUPIED) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.RequestNotAllowed).message("component occupied by different senderId").build();				
			}
			break;
		case PRIO:
			if (getOccupationState() == OccupationState.LOCAL || getOccupationState() == OccupationState.PRIORITY) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.RequestNotAllowed).message("component occupied (with priority or locally) by different senderId").build();					
			}
			break;
//		case LOCAL:
//			if (getOccupationState() == OccupationState.LOCAL) {
//				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("component occupied (locally) by different senderId").build();							
//			}
//			break;
		default:
			
		}
		
		if (status == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}
		return status;
	}

	protected ComponentOrderStatus canSetExecutionMode(ExecutionMode mode, String senderId) {
		ComponentOrderStatus status;	
		
		if (disableExecutionModeChange) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.RequestNotAllowed).message("execution mode change disabled").build();	
			return status;			
		} 
		
		if (!disableOccupationCheck) {
			if (senderId == null) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenInvalid).message("senderId must not be null").build();	
				return status;
			} else if (!senderId.equals(getOccupierId())) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenRejected).message("senderId does not match current occupier").build();
				return status;
			}
		}
		
		if (!operationMode.getExecutionModes().contains(mode)) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.NotSupported).message("execution mode not supported").build();
		} else {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}
		return status;
	}
	
	protected ComponentOrderStatus canRaiseExecutionCommand(ExecutionCommand command, String senderId) {
		ComponentOrderStatus status;		
		
		if (!disableOccupationCheck) {
			if (senderId == null) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenInvalid).message("senderId must not be null").build();	
				return status;
			} else if (!senderId.equals(getOccupierId())) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenRejected).message("senderId does not match current occupier").build();
				return status;
			}
		}
		
		if (!operationMode.getExecutionCommands().contains(command)) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.NotSupported).message("execution command not supported").build();
		} else {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}
		return status;
	}
	
	protected ComponentOrderStatus canSetOperationMode(String opMode, String senderId) {
		ComponentOrderStatus status;		
		
		if (!disableOccupationCheck) {
			if (senderId == null) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenInvalid).message("senderId must not be null").build();	
				return status;
			} else if (!senderId.equals(getOccupierId())) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.IdentityTokenRejected).message("senderId does not match current occupier").build();
				return status;
			}
		}
			
		if (!operationModes.containsKey(opMode)) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.NotFound).message("operation mode not found").build();
		} else if (getExecutionState() != ExecutionState.IDLE) {
			// see page 42. change opMode in COMPLETED, ABORTED or STOPPED
			// but this means the new opMode has to reset the device for the old opMode
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).statusCode(OrderStatusCodes.InvalidState).message("operation mode can only be set in IDLE execution state").build();
		} else {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}			
		return status;
	}
	
	@Override
	protected void notifyChange() {
		ComponentInfo info = getInfo();
		context.getEventBus().post(info);

		Gson gsonObj = new Gson();
		String payload =  gsonObj.toJson(info);
		
		EventMessage message = EventMessage.builder()
				.withObservableReference(new Reference(getSubmodelId(), KeyElements.SUBMODEL, true))
				.withTimestamp(info.getTimestamp())
				.withTopic(getSubmodelId().getId() + "/update")
				.withPayload(payload)
				.build();
		
		context.getEventBus().post(message);
	}

	@Override
	public List<ParameterInfo> getParameters() throws ComponentException {
		return parameterSpace.getParameters();
	}

	@Override
	public ParameterInfo getParameter(String name) throws ComponentException {
		return parameterSpace.getParameter(name);
	}

	@Override
	public Object getParameterValue(String name) throws ComponentException {
		return parameterSpace.getParameterValue(name);
	}

	@Override
	public void setParameterValue(String name, Object value) throws ComponentException {
		parameterSpace.setParameterValue(name, value);		
	}

	@Override
	public void notifyStateChange(PackMLStatusInterface status) {
		notifyChange();		
	}
	
	IIdentifier instanceSubmodelId = null;
	
	@Subscribe
	public void onComponentManagerEvent(ComponentManagerEvent ev) {		
		if (ev.getType() == Type.COMPONENT_ADDED) {
			Component component = ev.getComponent();
			if (component == this) {
				//create and register config submodel
				ConnectedAssetAdministrationShellManager aasManager = new ConnectedAssetAdministrationShellManager(AasComponentContext.getStaticContext().getAasRegistry());
				Submodel instanceSubmodel = ControlComponentSubmodelFactory.createInstanceSubmodel(this);
				instanceSubmodelId = instanceSubmodel.getIdentification();
				aasManager.deleteSubmodel(getAasId(), instanceSubmodelId);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				aasManager.createSubmodel(getAasId(), instanceSubmodel);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		else if (ev.getType() == Type.COMPONENT_DELETED) {		
			Component component = ev.getComponent();
			if (component == this) {
				//TODO: delete and unregister instance submodel.
				ConnectedAssetAdministrationShellManager aasManager = new ConnectedAssetAdministrationShellManager(AasComponentContext.getStaticContext().getAasRegistry());
				aasManager.deleteSubmodel(getAasId(), instanceSubmodelId);
				instanceSubmodelId = null;
				
				// unregister submodel
				//((AasComponentContext) context).getAasRegistry().delete(getAasId(),  new Identifier(getSubmodelId().getIdType(), getSubmodelId().getId().replace("control-component", "control-component-instance")));		
				
			}
		}
	}
	
}
