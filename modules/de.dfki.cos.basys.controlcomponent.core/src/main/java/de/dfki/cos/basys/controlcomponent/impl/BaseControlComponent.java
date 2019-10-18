package de.dfki.cos.basys.controlcomponent.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.common.component.OrderStatus;
import de.dfki.cos.basys.common.component.impl.BaseComponent;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.SharedParameterSpace;
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;

public class BaseControlComponent extends BaseComponent implements ControlComponent, PackMLActiveStatesHandler, PackMLWaitStatesHandler {

	protected boolean simulated, resetOnComplete, resetOnStopped, initialStartOnIdle, initialSuspendOnExecute = false;
	
	//protected Set<ExecutionCommand> allowedExecutionCommands = new HashSet<>(Arrays.asList(ExecutionCommand.RESET, ExecutionCommand.START, ExecutionCommand.STOP));
	//protected Set<ExecutionMode> allowedExecutionModes = new HashSet<>(Arrays.asList(ExecutionMode.PRODUCTION));	

	//protected PackMLStatesHandlerFacade handlerFacade = null;	
	protected SharedParameterSpaceImpl parameterSpace;

	private Map<String, OperationMode> operationModes = new HashMap<>();
	private OccupationLevel occupationLevel = OccupationLevel.FREE;
	private String occupierId = "INIT";
	private OperationMode operationMode = null;
	private String workState = "";
	private String errorMessage = "OK";
	private int errorCode = 0;
	
	private PackMLUnit packmlUnit;

	
	public BaseControlComponent(Properties config) {
		super(config);
		this.parameterSpace = new SharedParameterSpaceImpl(this);
		
//		if (config.getSimulationConfiguration() == null) {
//			config.setSimulationConfiguration(new SimulationConfigurationImpl.Builder().build());
//		} else {
//			LOGGER.debug("using provided simulation config");
//		}
		
		//allowedExecutionModes = new HashSet<>(Arrays.asList(ExecutionMode.PRODUCTION, ExecutionMode.SIMULATION));	

//		if (config.getProperties().get("resetOnComplete") != null) {
//			resetOnComplete = Boolean.parseBoolean(config.getProperties().get("resetOnComplete"));
//			LOGGER.info("resetOnComplete = " + resetOnComplete);
//		}
//		
//		if (config.getProperties().get("resetOnStopped") != null) {
//			resetOnStopped = Boolean.parseBoolean(config.getProperties().get("resetOnStopped"));
//			LOGGER.info("resetOnStopped = " + resetOnStopped);
//		}		
//
//		if (config.getProperties().get("initialStartOnIdle") != null) {
//			initialStartOnIdle = Boolean.parseBoolean(config.getProperties().get("initialStartOnIdle"));
//			LOGGER.info("initialStartOnIdle = " + initialStartOnIdle);
//		}
//		
//		if (config.getProperties().get("initialSuspendOnExecute") != null) {
//			initialSuspendOnExecute = Boolean.parseBoolean(config.getProperties().get("initialSuspendOnExecute"));
//			LOGGER.info("initialSuspendOnExecute = " + initialSuspendOnExecute);
//		}
//		
//		if (config.getProperties().get("simulated") != null) {
//			simulated = Boolean.parseBoolean(config.getProperties().get("simulated"));
//			LOGGER.info("simulated = " + simulated);			
//		}
				
	}

	@Override
	protected void doActivate() {
		//lock = new ReentrantLock();
		//executeCondition = lock.newCondition();	
		//handlerFacade = new PackMLStatesHandlerFacade(this);
		
		DefaultOperationMode defaultMode = new DefaultOperationMode(this);		
		this.operationMode = defaultMode; 
		this.operationModes.put(defaultMode.getName(), defaultMode);	
		
		packmlUnit = new PackMLUnit(getName());
		packmlUnit.setActiveStatesHandler(this);
		packmlUnit.setWaitStatesHandler(this);
		packmlUnit.initialize();
				
		if (simulated) {
			packmlUnit.setExecutionMode(ExecutionMode.SIMULATION, occupierId);
			//observeExternalConnection = false;
			LOGGER.info("set component to SIMULATION mode");
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

	protected void registerOperationModes() {

	};
	
	public ComponentOrderStatus registerOperationMode(OperationMode operationMode) {
		return registerOperationMode(operationMode, "INIT");
	}
	
	@Override
	public ComponentOrderStatus registerOperationMode(OperationMode operationMode, String occupierId) {
		ComponentOrderStatus status = null;
		
		if (occupierId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();				
		} else if (!occupierId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();
		} else {
			if (operationModes.containsKey(operationMode.getName())) {					
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("operation mode with name '" + operationMode.getName() + "' already registered. unregister first.").build();
			} else {
				operationModes.put(operationMode.getName(), operationMode);
				parameterSpace.registerOperationMode(operationMode);
				if (!"INIT".equals(occupierId))
					notifyChange();
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("operation mode registered").build();
			}
		}
		
		return status;
	}
	@Override
	public ComponentOrderStatus unregisterOperationMode(String operationModeName, String occupierId) {
		ComponentOrderStatus status = null;
		
		if (occupierId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();				
		} else if (!occupierId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();
		} else {
			if (operationModes.containsKey(operationModeName)) {
				operationModes.remove(operationModeName);
				parameterSpace.unregisterOperationMode(operationMode);
				notifyChange();
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("operation mode unregistered").build();
			} else {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("no operation mode with name '" + operationModeName + "'").build();
			}
		}
	
		return status;
	}
	
//	public void awaitExecuteComplete() {
//		lock.lock();
//		try {
//			executeCondition.await();						
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			return;
//		} finally {
//			lock.unlock();
//		}
//	}
//	
//	public void signalExecuteComplete() {
//		lock.lock();
//		executeCondition.signalAll();
//		lock.unlock();
//	}
	
	@Override
	public OccupationStatus getOccupationStatus() {
		return new OccupationStatus.Builder().level(getOccupationLevel()).occupierId(getOccupierId()).build();
	}	
	
	@Override
	public OccupationLevel getOccupationLevel() {
		return occupationLevel;
	}

	@Override
	public String getOccupierId() {
		return occupierId;
	}
	
	protected void setOccupationStatus(OccupationLevel occupationLevel, String occupierId) {
		this.occupationLevel = occupationLevel;
		this.occupierId = occupierId;
		notifyChange();
	}


	@Override
	public OperationModeInfo getOperationMode() {		
		return operationMode.getInfo();
	}
	
	@Override
	public List<OperationModeInfo> getOperationModes() {	
//		List<OperationModeInfo> result = new ArrayList<>(operationModes.size());
//		for (OperationMode opmode : operationModes.values()) {
//			result.add(opmode.getInfo());
//		}
//		return result;		
		
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
		return workState;
	}

	protected void setWorkState(String workState) {
		this.workState = workState;
		notifyChange();
	}
	
	@Override
	public ErrorStatus getErrorStatus() {
		return new ErrorStatus.Builder().errorCode(errorCode).errorMessage(errorMessage).build();
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	protected void setErrorStatus(int errorCode, String errorMessage) {
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
		ComponentInfo i = super.getInfo();
		
		ControlComponentInfo info = new ControlComponentInfo(i)				
				//.connectedToExternal(getConnectionManager().isConnected())
				.setErrorStatus(getErrorStatus())
				.setExecutionMode(getExecutionMode())
				.setExecutionState(getExecutionState())
				.setOccupationStatus(getOccupationStatus())
				.setOperationMode(operationMode.getName())
				.setWorkState(getWorkState());	
		
		return info;
	}
	
//	@Override
//	public ComponentOrderStatus executeOrder(ComponentOrder order) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ComponentOrderStatus free(String occupierId) {
		return occupy(OccupationLevel.FREE, occupierId);
	}

	@Override
	public ComponentOrderStatus occupy(String occupierId) {
		return occupy(OccupationLevel.OCCUPIED, occupierId);
	}

	@Override
	public ComponentOrderStatus occupyPriority(String occupierId) {
		return occupy(OccupationLevel.PRIORITY, occupierId);
	}

	@Override
	public ComponentOrderStatus occupyLocal(String occupierId) {
		return occupy(OccupationLevel.LOCAL, occupierId);
	}

	@Override
	public ComponentOrderStatus occupy(OccupationLevel level, String occupierId) {
		LOGGER.info("occupy " + level + " (occupierId=" + occupierId + ")");
		ComponentOrderStatus status = canOccupyLevel(occupierId, level);
		if (status.getStatus() == OrderStatus.ACCEPTED) {
			if (level == OccupationLevel.FREE)
				setOccupationStatus(level, "");
			else {
				setOccupationStatus(level, occupierId);
			}
			status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("ok").build();	
			
		}
		return status;
	}

	@Override
	public ComponentOrderStatus setOperationMode(String opMode, String occupierId) {
		ComponentOrderStatus status = canSetOperationMode(opMode, occupierId);		
		if (status.getStatus() == OrderStatus.ACCEPTED) {		
			OperationMode newMode = operationModes.get(opMode);
			this.operationMode = newMode;
			status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("ok").build();	
			notifyChange();		
		}
		return status;
	}
	
	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode, String occupierId) {
		ComponentOrderStatus status = canSetExecutionMode(mode, occupierId);		
		if (status.getStatus() == OrderStatus.ACCEPTED) {		
			status = packmlUnit.setExecutionMode(mode, occupierId);		
			if (status.getStatus() == OrderStatus.ACCEPTED) {
				notifyChange();
				status = new ComponentOrderStatus.Builder().status(OrderStatus.DONE).message("ok").build();	
			}
		}
		return status;		
	}

	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String occupierId) {
		ComponentOrderStatus status = canRaiseExecutionCommand(command, occupierId);
		if (status.getStatus() == OrderStatus.ACCEPTED) {
			status = packmlUnit.raiseExecutionCommand(command, occupierId);
		}
		return status;
	}
	
	@Override
	public ComponentOrderStatus reset(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.RESET, occupierId);
	}

	@Override
	public ComponentOrderStatus start(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.START, occupierId);
	}

	@Override
	public ComponentOrderStatus stop(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.STOP, occupierId);
	}

	@Override
	public ComponentOrderStatus hold(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.HOLD, occupierId);
	}

	@Override
	public ComponentOrderStatus unhold(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.UNHOLD, occupierId);
	}

	@Override
	public ComponentOrderStatus suspend(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.SUSPEND, occupierId);
	}

	@Override
	public ComponentOrderStatus unsuspend(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.UNSUSPEND, occupierId);
	}

	@Override
	public ComponentOrderStatus abort(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.ABORT, occupierId);
	}

	@Override
	public ComponentOrderStatus clear(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.CLEAR, occupierId);
	}
	
	/*
	 * default WaitStatesHandler implementation -> notify Basys Middleware
	 */

	@Override
	public void onStopped() {
		LOGGER.debug("onStopped - start");
		notifyChange();
		LOGGER.debug("onStopped - finished");
	}

	@Override
	public void onIdle() {
		LOGGER.debug("onIdle - start");
		notifyChange();
		LOGGER.debug("onIdle - finished");
	}

	@Override
	public void onComplete() {
		LOGGER.debug("onComplete - start");
		notifyChange();
		LOGGER.debug("onComplete - finished");
	}

	@Override
	public void onHeld() {
		LOGGER.debug("onHeld - start");
		notifyChange();
		LOGGER.debug("onHeld - finished");
	}

	@Override
	public void onSuspended() {
		LOGGER.debug("onSuspended - start");
		notifyChange();
		LOGGER.debug("onSuspended - finished");
	}

	@Override
	public void onAborted() {
		LOGGER.debug("onAborted - start");
		notifyChange();
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
		notifyChange();
		if (operationMode!=null) {
			operationMode.onStarting();
		}
		LOGGER.debug("onStarting - finished");
	}

	@Override
	public void onExecute() {
		LOGGER.debug("onExecute - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onExecute();
		}
		LOGGER.debug("onExecute - finished");
	}

	@Override
	public void onCompleting() {
		LOGGER.debug("onCompleting - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onCompleting();
		}
		LOGGER.debug("onCompleting - finished");
	}

	@Override
	public void onHolding() {
		LOGGER.debug("onHolding - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onHolding();
		}
		LOGGER.debug("onHolding - finished");
	}

	@Override
	public void onUnholding() {
		LOGGER.debug("onUnholding - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onUnholding();
		}
		LOGGER.debug("onUnholding - finished");
	}

	@Override
	public void onSuspending() {
		LOGGER.debug("onSuspending - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onSuspending();
		}
		LOGGER.debug("onSuspending - finished");
	}

	@Override
	public void onUnsuspending() {
		LOGGER.debug("onUnsuspending - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onUnsuspending();
		}
		LOGGER.debug("onUnsuspending - finished");
	}

	@Override
	public void onAborting() {
		LOGGER.debug("onAborting - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onAborting();
		}
		LOGGER.debug("onAborting - finished");
	}

	@Override
	public void onClearing() {
		LOGGER.debug("onClearing - start");
		notifyChange();
		if (operationMode!=null) {
			operationMode.onClearing();
		}
		LOGGER.debug("onClearing - finished");
	}

	@Override
	public void onStopping() {
		LOGGER.debug("onStopping - start");		
		notifyChange();
		if (operationMode!=null) {
			operationMode.onStopping();
		}
		LOGGER.debug("onStopping - finished");
	}
	
	/*
	 * 
	 */


	protected ComponentOrderStatus canOccupyLevel(String occupierId, OccupationLevel level) {
		ComponentOrderStatus status = null;		
		if (occupierId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();	
			return status;
		} 
		
		switch (level) {
		case FREE:
			if (!occupierId.equals(getOccupierId())) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();				
			}
			break;
		case OCCUPIED:
			if (getOccupationLevel() == OccupationLevel.LOCAL || getOccupationLevel() == OccupationLevel.PRIORITY || getOccupationLevel() == OccupationLevel.OCCUPIED) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("component occupied by different occupierId").build();				
			}
			break;
		case PRIORITY:
			if (getOccupationLevel() == OccupationLevel.LOCAL || getOccupationLevel() == OccupationLevel.PRIORITY) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("component occupied (with priority or locally) by different occupierId").build();					
			}
			break;
		case LOCAL:
			if (getOccupationLevel() == OccupationLevel.LOCAL) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("component occupied (locally) by different occupierId").build();							
			}
			break;
		default:
			
		}
		
		if (status == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}
		return status;
	}

	protected ComponentOrderStatus canSetExecutionMode(ExecutionMode mode, String occupierId) {
		ComponentOrderStatus status;		
		if (occupierId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();	
			return status;
		} else if (!occupierId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();
			return status;
		}
		
		if (simulated) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("command not allowed in static simulation").build();
		} else if (!operationMode.getExecutionModes().contains(mode)) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("command not allowed").build();
		} else {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}
		return status;
	}
	
	protected ComponentOrderStatus canRaiseExecutionCommand(ExecutionCommand command, String occupierId) {
		ComponentOrderStatus status;		
		if (occupierId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();	
			return status;
		} else if (!occupierId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();
			return status;
		}
		
		if (!operationMode.getExecutionCommands().contains(command)) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("command not allowed").build();
		} else {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}
		return status;
	}
	
	protected ComponentOrderStatus canSetOperationMode(String opMode, String occupierId) {
		ComponentOrderStatus status;		
		if (occupierId == null) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();	
			return status;
		} else if (!occupierId.equals(getOccupierId())) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();
			return status;
		} 
			
		if (!operationModes.containsKey(opMode)) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("operation mode unknown").build();
		} else if (getExecutionState() != ExecutionState.IDLE) {
			// see page 42. change opMode in COMPLETED, ABORTED or STOPPED
			// but this means the new opMode has to reset the device for the old opMode
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("operation mode can only be set in IDLE execution state").build();
		} else {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("ok").build();
		}			
		return status;
	}
	
	@Override
	protected void notifyChange() {
		ComponentInfo info = getInfo();
		context.getEventBus().post(info);
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
	
}
