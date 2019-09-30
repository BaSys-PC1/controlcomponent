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
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;

public abstract class BaseControlComponent extends BaseComponent implements ControlComponent, PackMLActiveStatesHandler, PackMLWaitStatesHandler {

	protected boolean simulated, resetOnComplete, resetOnStopped, initialStartOnIdle, initialSuspendOnExecute = false;
	
	//protected Set<ExecutionCommand> allowedExecutionCommands = new HashSet<>(Arrays.asList(ExecutionCommand.RESET, ExecutionCommand.START, ExecutionCommand.STOP));
	//protected Set<ExecutionMode> allowedExecutionModes = new HashSet<>(Arrays.asList(ExecutionMode.PRODUCTION));	

	PackMLStatesHandlerFacade handlerFacade = null;	
	
	protected Map<String, OperationMode> operationModes = new HashMap<>();
	
	private OccupationLevel occupationLevel = OccupationLevel.FREE;
	private String occupierId = "INIT";
	private OperationMode operationMode = null;
	private String workState = null;
	private String errorMessage = "OK";
	private int errorCode = 0;
	
	private PackMLUnit packmlUnit;

	
	public BaseControlComponent(Properties config) {
		super(config);

		
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
		
		registerOperationModes();
		
		handlerFacade = new PackMLStatesHandlerFacade(this);
		
		packmlUnit = new PackMLUnit(getName());
		packmlUnit.setActiveStatesHandler(handlerFacade);
		packmlUnit.setWaitStatesHandler(handlerFacade);
		packmlUnit.initialize();
		
		if (simulated) {
			packmlUnit.setExecutionMode(ExecutionMode.SIMULATION, occupierId);
			//observeExternalConnection = false;
			LOGGER.info("set component to SIMULATION mode");
		}
	}
	

	protected void registerOperationModes() {
		DefaultOperationMode operationMode = new DefaultOperationMode(this);		
		this.operationMode = operationMode; 
		
	};
	
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
				//TODO: reflect opMode addition in variable space
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
				//TODO: reflect opMode removal in variable space
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
	public ControlComponentInfo getInfo() {
		ControlComponentInfo info = new ControlComponentInfo.Builder()
				.activated(isActivated())
				.connectedToExternal(getConnectionManager().isConnected())
				.errorStatus(getErrorStatus())
				.executionMode(getExecutionMode())
				.executionState(getExecutionState())
				.id(getId())
				.name(getName())
				.occupationStatus(getOccupationStatus())
				.operationMode(operationMode.getName())
				.workState(getWorkState())
				.build();	
		
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
				setOccupationStatus(level, null);
			else {
				setOccupationStatus(level, occupierId);
			}
		}
		return status;
	}

	@Override
	public ComponentOrderStatus setOperationMode(String opMode, String occupierId) {
		ComponentOrderStatus status = canSetOperationMode(opMode, occupierId);		
		if (status.getStatus() == OrderStatus.ACCEPTED) {		
			OperationMode newMode = operationModes.get(opMode);
			this.operationMode = newMode;
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
	}

	@Override
	public void onIdle() {
	}

	@Override
	public void onComplete() {
	}

	@Override
	public void onHeld() {
	}

	@Override
	public void onSuspended() {
	}

	@Override
	public void onAborted() {
	}

	
	/*
	 * default ActiveStatesHandler implementation -> trigger logic in selected operation mode
	 */

	@Override
	public void onResetting() {
		setErrorStatus(0,"OK");
		
		if (operationMode!=null) {
			operationMode.onResetting();
		}
	}

	@Override
	public void onStarting() {
		if (operationMode!=null) {
			operationMode.onStarting();
		}
	}

	@Override
	public void onExecute() {
		if (operationMode!=null) {
			operationMode.onExecute();
		}
	}

	@Override
	public void onCompleting() {
		if (operationMode!=null) {
			operationMode.onCompleting();
		}
	}

	@Override
	public void onHolding() {
		if (operationMode!=null) {
			operationMode.onHolding();
		}
	}

	@Override
	public void onUnholding() {
		if (operationMode!=null) {
			operationMode.onUnholding();
		}
	}

	@Override
	public void onSuspending() {
		if (operationMode!=null) {
			operationMode.onSuspending();
		}
	}

	@Override
	public void onUnsuspending() {
		if (operationMode!=null) {
			operationMode.onUnsuspending();
		}
	}

	@Override
	public void onAborting() {
		if (operationMode!=null) {
			operationMode.onAborting();
		}
	}

	@Override
	public void onClearing() {
		if (operationMode!=null) {
			operationMode.onClearing();
		}
	}

	@Override
	public void onStopping() {
		if (operationMode!=null) {
			operationMode.onStopping();
		}
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
		ControlComponentInfo info = getInfo();
		context.getEventBus().post(info);
	};

	
}
