package de.dfki.cos.basys.controlcomponent.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.eventbus.EventBus;

import de.dfki.cos.basys.controlcomponent.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.ComponentOrder;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.core.ControlComponent;
import de.dfki.cos.basys.controlcomponent.core.ControlComponentException;
import de.dfki.cos.basys.controlcomponent.core.OperationMode;
import de.dfki.cos.basys.controlcomponent.core.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.impl.ComponentOrderStatusImpl;
import de.dfki.cos.basys.controlcomponent.packml.PackMLActiveStatesHandler;
import de.dfki.cos.basys.controlcomponent.packml.PackMLUnit;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;

public abstract class BaseControlComponent implements ControlComponent, PackMLActiveStatesHandler, PackMLWaitStatesHandler {
	
	public final Logger LOGGER;

	// TODO: only once per OPC-UA-Server --> ComponentContext 
	private EventBus eventBus;
	// TODO: only once per OPC-UA-Server --> ComponentContext
	private ScheduledExecutorService scheduledExecutorService =  Executors.newScheduledThreadPool(32);
	
	protected ComponentConfiguration config;
	
	protected boolean simulated, resetOnComplete, resetOnStopped, initialStartOnIdle, initialSuspendOnExecute = false;
	
	protected Set<ExecutionCommand> allowedExecutionCommands = new HashSet<>(Arrays.asList(ExecutionCommand.RESET, ExecutionCommand.START, ExecutionCommand.STOP));
	protected Set<ExecutionMode> allowedExecutionModes = new HashSet<>(Arrays.asList(ExecutionMode.PRODUCTION));	
	private Lock lock;
	private Condition executeCondition;
	PackMLStatesHandlerFacade handlerFacade = null;	
	
	protected boolean connectedToExternal = false;
	protected boolean observeExternalConnection = false;
	private ScheduledFuture<?> externalConnectionHandle = null;
	private boolean activated = false;
	
	private List<OperationMode> operationModes = new ArrayList<>();
	
	private OccupationLevel occupationLevel = OccupationLevel.FREE;
	private String occupierId = null;
	private OperationMode operationMode = null;
	private String workState = null;
	private String errorMessage = "OK";
	private int errorCode = 0;
	
	private PackMLUnit packmlUnit;
	
	public BaseControlComponent(ComponentConfiguration config) {
		this.config = config;
		LOGGER = LoggerFactory.getLogger("basys.component." + config.getName().replaceAll(" ", "-"));

		
//		if (config.getSimulationConfiguration() == null) {
//			config.setSimulationConfiguration(new SimulationConfigurationImpl.Builder().build());
//		} else {
//			LOGGER.debug("using provided simulation config");
//		}
		

		allowedExecutionModes = new HashSet<>(Arrays.asList(ExecutionMode.PRODUCTION, ExecutionMode.SIMULATION));	

		if (config.getProperty("resetOnComplete") != null) {
			resetOnComplete = Boolean.parseBoolean(config.getProperty("resetOnComplete").getValue());
			LOGGER.info("resetOnComplete = " + resetOnComplete);
		}
		
		if (config.getProperty("resetOnStopped") != null) {
			resetOnStopped = Boolean.parseBoolean(config.getProperty("resetOnStopped").getValue());
			LOGGER.info("resetOnStopped = " + resetOnStopped);
		}		

		if (config.getProperty("observeExternalConnection") != null) {
			observeExternalConnection = Boolean.parseBoolean(config.getProperty("observeExternalConnection").getValue());
			LOGGER.info("observeExternalConnection = " + observeExternalConnection);
		}

		if (config.getProperty("initialStartOnIdle") != null) {
			initialStartOnIdle = Boolean.parseBoolean(config.getProperty("initialStartOnIdle").getValue());
			LOGGER.info("initialStartOnIdle = " + initialStartOnIdle);
		}
		
		if (config.getProperty("initialSuspendOnExecute") != null) {
			initialSuspendOnExecute = Boolean.parseBoolean(config.getProperty("initialSuspendOnExecute").getValue());
			LOGGER.info("initialSuspendOnExecute = " + initialSuspendOnExecute);
		}
		
		if (config.getProperty("simulated") != null) {
			simulated = Boolean.parseBoolean(config.getProperty("simulated").getValue());
			LOGGER.info("simulated = " + simulated);			
		}
	}

	@Override
	public String getId() {
		return config.getId();
	}

	@Override
	public String getName() {		
		return config.getName();
	}

	@Override
	public void activate() throws ControlComponentException {
		LOGGER.info("activate");
		if (!activated) {		

			lock = new ReentrantLock();
			executeCondition = lock.newCondition();	
			
			eventBus = new EventBus(getId());			
			
			registerOperationModes();
			
			handlerFacade = new PackMLStatesHandlerFacade(this);
			
			packmlUnit = new PackMLUnit(getName());
			packmlUnit.setActiveStatesHandler(handlerFacade);
			packmlUnit.setWaitStatesHandler(handlerFacade);		
			packmlUnit.initialize();
			
			if (simulated) {
				packmlUnit.setExecutionMode(ExecutionMode.SIMULATION, "PACKML");
				observeExternalConnection = false;
				LOGGER.info("set component to SIMULATION mode");
			}
			
			
			if (config.getExternalConnectionString() != null && !config.getExternalConnectionString().equals("")) {
								
				LOGGER.info("connectToExternal: " + config.getExternalConnectionString());
				try {
					if (canConnectToExternal()) {
						connectToExternal();
						setConnectedToExternal(true);
					} else {
						LOGGER.warn("component cannot connectToExternal(), skip ...");
					}
					LOGGER.debug("connectToExternal - finished");
				} catch (ControlComponentException e) {
					LOGGER.error(e.getMessage());
					LOGGER.warn("component could not connectToExternal()");
					//e.printStackTrace();
					setConnectedToExternal(false);
				}
				
				observeExternalConnection();
				
			}

			setActivated(true);
			LOGGER.info("activate - finished");
		} else {
			LOGGER.info("already activated");
		}
	}

	@Override
	public void deactivate() throws ControlComponentException {
		LOGGER.info("deactivate");
		if (activated) {
			if (connectedToExternal) {				
				unobserveExternalConnection();
				disconnectFromExternal();
				setConnectedToExternal(false);
			}
			setActivated(false);
			LOGGER.info("deactivate - finished");
		} else {
			LOGGER.info("already deactivated");
		}
	}
	
	@Override
	public boolean isActivated() {
		return activated;
	}
	
	private void setActivated(boolean value) {
		activated = value;
		sendChangeEvent();
	}
	
	@Override
	public boolean isConnectedToExternal() {
		return connectedToExternal;
	}
	
	private void setConnectedToExternal(boolean value) {
		connectedToExternal = value;
		sendChangeEvent();
	}

	private void observeExternalConnection() {
		if (observeExternalConnection) {
			LOGGER.info("observeExternalConnection()");
			externalConnectionHandle = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
				
				@Override
				public void run() {			
					
					if (!isConnectedToExternal()) {
						LOGGER.info("connectToExternal: " + config.getExternalConnectionString());
						try {
							if (canConnectToExternal()) {
								connectToExternal();
								//connectedToExternal = true;
							} else {
								LOGGER.warn("component cannot connectToExternal(), retry ...");
							}
							LOGGER.debug("connectToExternal - finished");
						} catch (ControlComponentException e) {
							LOGGER.error(e.getMessage());
							LOGGER.warn("component could not connectToExternal(), retry ...");
							e.printStackTrace();
						}
					}
					
				}
				
			}, 5000, 5000, TimeUnit.MILLISECONDS);
		}				
	}
	
	private void unobserveExternalConnection() {
		if (observeExternalConnection) {
			LOGGER.info("unobserveExternalConnection()");
			externalConnectionHandle.cancel(true);
		}
	}
	
	protected boolean canConnectToExternal() throws ControlComponentException {
		return true;
	}

	public void registerOperationModes() {
		
	};
	
	//public registerOperationMode(OperationModeConfiguration config)
	public void registerOperationMode(OperationMode operationMode) {
		operationModes.add(operationMode);
		sendChangeEvent();
	}
	public void unregisterOperationMode(String name) {
		Optional<OperationMode> opt = operationModes.stream().filter(opMode -> opMode.getName().equals(name)).findFirst();
		if (opt.isPresent()) {
			operationModes.remove(opt.get());
			sendChangeEvent();
		}
	}
	
	public abstract void connectToExternal() throws ControlComponentException;

	public abstract void disconnectFromExternal();
	
	public void awaitExecuteComplete() {
		lock.lock();
		try {
			executeCondition.await();						
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		} finally {
			lock.unlock();
		}
	}
	
	public void signalExecuteComplete() {
		lock.lock();
		executeCondition.signalAll();
		lock.unlock();
	}
	
	
	@Override
	public OccupationLevel getOccupationLevel() {
		return occupationLevel;
	}

	private void setOccupationLevel(OccupationLevel occupationLevel) {
		this.occupationLevel = occupationLevel;
		sendChangeEvent();
	}

	@Override
	public String getOccupierId() {
		return occupierId;
	}

	private void setOccupierId(String occupierId) {
		this.occupierId = occupierId;
		sendChangeEvent();
	}

	@Override
	public OperationModeInfo getOperationMode() {		
		return operationMode.getInfo();
	}
	
	@Override
	public List<OperationModeInfo> getOperationModes() {		
		OperationModeInfo[] result = operationModes.stream().map( new Function<OperationMode, OperationModeInfo>() {
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
		sendChangeEvent();
	}
	
	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	protected void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		sendChangeEvent();
	}
	
	@Override
	public int getErrorCode() {
		return errorCode;
	}
	
	protected void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		sendChangeEvent();
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
	public ComponentOrderStatus executeOrder(ComponentOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

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
		ComponentOrderStatus status = null;
		
		if (occupierId == null) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();
			return status;
		} else if (!canOccupyLevel(occupierId, level)) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("\"occupier '" + occupierId + "' lack sufficient rights").build();
			return status;
		}				

		status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("occupier '" + occupierId + "' could NOT occupy with level '" + level + "'").build();
				
		switch (level) {
		case FREE:
			if (occupierId.equals(this.occupierId)) {
				setOccupationLevel(level);
				setOccupierId(null);
				status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("occupier '" + occupierId + "' occupied with level '" + level + "'").build();				
			}
			break;
		case OCCUPIED:
			if (getOccupationLevel() == OccupationLevel.FREE) {
				setOccupationLevel(level);
				setOccupierId(occupierId);
				status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("occupier '" + occupierId + "' occupied with level '" + level + "'").build();				
			}
			break;
		case PRIORITY:
			if (getOccupationLevel() == OccupationLevel.FREE || getOccupationLevel() == OccupationLevel.OCCUPIED) {
				setOccupationLevel(level);
				setOccupierId(occupierId);
				status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("occupier '" + occupierId + "' occupied with level '" + level + "'").build();				
			}
			break;
		case LOCAL:
			if (getOccupationLevel() == OccupationLevel.FREE || getOccupationLevel() == OccupationLevel.OCCUPIED || getOccupationLevel() == OccupationLevel.PRIORITY) {
				setOccupationLevel(level);
				setOccupierId(occupierId);
				status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("occupier '" + occupierId + "' occupied with level '" + level + "'").build();				
			}
			break;
		default:
			
		}

		return status;
	}

	protected boolean canOccupyLevel(String occupierId, OccupationLevel level) {
		return true;
	}

	@Override
	public ComponentOrderStatus setOperationMode(String opMode, String occupierId) {
		ComponentOrderStatus status = null;
		if (occupierId == null) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("occupierId must not be null").build();				
		} else if (!occupierId.equals(getOccupierId())) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("occupierId does not match").build();
		} else if (getExecutionState() != ExecutionState.IDLE) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("operation mode can only be set in IDLE execution state").build();
		} else {
			Optional<OperationMode> matchingObject = operationModes.stream().
				    filter(op -> op.getName().equals(opMode)).
				    findFirst();
			if (matchingObject.isPresent()) {
				this.operationMode = matchingObject.get();
				status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("operation mode set").build();
				sendChangeEvent();
			} else {
				status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("operation mode unknown").build();	
			}
		}
		return status;
	}
	
	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode, String occupierId) {
		ComponentOrderStatus status = canSetExecutionMode(mode);		
		if (status.getStatus() == OrderStatus.ACCEPTED) {
		
			status = packmlUnit.setExecutionMode(mode, occupierId);
		
			if (status.getStatus() == OrderStatus.ACCEPTED) {
				sendChangeEvent();
			}	
			
		}
		return status;		
	}

	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String occupierId) {
		ComponentOrderStatus status = canRaiseExecutionCommand(command);
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

	protected ComponentOrderStatus canSetExecutionMode(ExecutionMode mode) {
		ComponentOrderStatus status;
		if (!allowedExecutionModes.contains(mode)) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("not allowed").build();
		} else if (simulated) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("not allowed").build();
		} else {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("allowed").build();
		}
		return status;
	}
	
	protected ComponentOrderStatus canRaiseExecutionCommand(ExecutionCommand command) {
		ComponentOrderStatus status;
		if (!allowedExecutionCommands.contains(command)) {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.REJECTED).message("not allowed").build();
		} else {
			status = new ComponentOrderStatusImpl.Builder().status(OrderStatus.ACCEPTED).message("allowed").build();
		}
		return status;
	}
	
	protected void sendChangeEvent() {
		
	};

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

}
