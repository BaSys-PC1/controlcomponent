package de.dfki.cos.basys.controlcomponent.packml;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OrderStatus;

public class PackMLUnit implements PackMLStatusInterface, PackMLCommandInterface, PackMLActiveStatesHandler, PackMLWaitStatesHandler {

	protected final Logger LOGGER;// = LoggerFactory.getLogger(PackMLUnit.class.getName());

	//private String occupierId = null;
	private ExecutionMode mode = ExecutionMode.PRODUCTION;

	private boolean initialized = false;
//	private boolean wait = false;
	private PackML packml = null;
	private ExecutorService executor;
	private CompletableFuture<Void> currentTask;
	
	private PackMLActiveStatesHandler actHandler = null;
	private PackMLWaitStatesHandler waitHandler = null;
	private PackMLStateChangeNotifier changeNotifier = null;


	public PackMLUnit(String name) {
		LOGGER = LoggerFactory.getLogger("basys.component." + name.replaceAll(" ", "-") + ".packml");
		packml = new PackML(this);
	}

	public void initialize() {
		if (!initialized) {
			executor = Executors.newCachedThreadPool();
			// executor = Executors.newFixedThreadPool(2);
			// executor = Executors.newSingleThreadExecutor();
	
			packml.initialize();
			initialized = true;
		}
	}

	public void dispose() {
		if (initialized) {
			packml.dispose();
	
			try {
				System.out.println("attempt to shutdown executor");
				executor.shutdown();
				executor.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				System.err.println("tasks interrupted");
			} finally {
				if (!executor.isTerminated()) {
					System.err.println("cancel non-finished tasks");
				}
				executor.shutdownNow();
				System.out.println("shutdown finished");
			}
			initialized = false;
		}
	}

//	public String getOccupierId() {
//		return occupierId;
//	}
//	
//	public void setOccupierId(String occupierId) {
//		this.occupierId = occupierId;
//	}
		
	public void setActiveStatesHandler(PackMLActiveStatesHandler actHandler) {
		this.actHandler = actHandler;
	}

	public void setWaitStatesHandler(PackMLWaitStatesHandler waitHandler) {
		this.waitHandler = waitHandler;
	}
	
	public void setChangeNotifier(PackMLStateChangeNotifier changeNotifier) {
		this.changeNotifier = changeNotifier;
	}

	public void cancelCurrentTask(boolean immediately) {
		LOGGER.debug("cancelCurrentTask");
		if (currentTask != null) {
			if (!currentTask.isDone()) {
				// try {
				currentTask.completeExceptionally(new PackMLException("Execution cancelled"));
				// currentTask.cancel(immediately);
				// currentTask.join();
				// } catch (Exception e) {
				//
				// }
			}
			currentTask = null;
		}
	}

	private void schedule(Runnable r) {

		currentTask = CompletableFuture.runAsync(r, executor).thenAccept((_void_) -> {
			packml.raiseLifecycleEvent("done");
		}).handle((result, ex) -> {
			if (ex != null) {
				LOGGER.error(ex.getMessage(), ex);				
			}
			return null;
		});
//
//		if (wait) {
//			try {
//				currentTask.get();
//			} catch (InterruptedException | ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				// throw new PackMLException(e);
//			}
//		}

	}

	/*
	 * StatusInterface
	 */
	
	private ExecutionState state = ExecutionState.STOPPED;
	public void setExecutionState(ExecutionState state) {
		this.state = state;
		this.changeNotifier.notifyStateChange(this);
	}
	
	public ExecutionState getExecutionState() {
		return state;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		return mode;
	}

	/*
	 * CommandInterface
	 */

	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String occupierId) {
		LOGGER.trace("raiseExecutionCommand('" + command + "', '" + occupierId + "')");
		ComponentOrderStatus status = null;
		
		switch (command) {
		case RESET:
			if (getExecutionState() == ExecutionState.IDLE) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else if (getExecutionState() == ExecutionState.COMPLETE || getExecutionState() == ExecutionState.STOPPED) {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			} else {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("not in COMPLETE or STOPPED state (" + getExecutionState() + ")").build();
			}
			break;
		case START:
			if (getExecutionState() == ExecutionState.STARTING || getExecutionState() == ExecutionState.EXECUTE) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else if (getExecutionState() == ExecutionState.IDLE) {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			} else {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("not in COMPLETE or STOPPED state").build();
			}
			break;
		case STOP:
			if (getExecutionState() == ExecutionState.STOPPING || getExecutionState() == ExecutionState.STOPPED) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;	
		case HOLD:
			if (getExecutionState() == ExecutionState.HOLDING || getExecutionState() == ExecutionState.HELD) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;	

		case UNHOLD:
			if (getExecutionState() == ExecutionState.UNHOLDING || getExecutionState() == ExecutionState.EXECUTE) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;
		case SUSPEND:
			if (getExecutionState() == ExecutionState.SUSPENDING || getExecutionState() == ExecutionState.SUSPENDED) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;	
		case UNSUSPEND:
			if (getExecutionState() == ExecutionState.UNSUSPENDING || getExecutionState() == ExecutionState.EXECUTE) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;	
		case ABORT:
			if (getExecutionState() == ExecutionState.ABORTING || getExecutionState() == ExecutionState.ABORTED) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;	
		case CLEAR:
			if (getExecutionState() == ExecutionState.CLEARING || getExecutionState() == ExecutionState.STOPPED) {
				status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message("already in state " + getExecutionState()).build();
			} else {
				packml.raiseLifecycleEvent(command.getLiteral().toLowerCase());
				status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("command accepted").build();
			}
			break;	
		default:
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("command not yet supported").build();
			break;
		}
		
		return status;		
	}
	
	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode, String occupierId) {
		LOGGER.trace("setExecutionMode('" + mode + "', '" + occupierId + "')");
		ComponentOrderStatus status = null;
		
		ExecutionState state = getExecutionState();
		if (getExecutionMode() == mode) {
			status = new ComponentOrderStatus.Builder().status(OrderStatus.NOOP).message(String.format("already in mode %s", mode)).build();
		} else if (mode == ExecutionMode.MANUAL && state == ExecutionState.ABORTED) {
			this.mode = mode;
			packml.raiseLifecycleEvent("switch_mode");
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("mode switched").build();
		} else if (state == ExecutionState.STOPPED) {
			this.mode = mode;
			packml.raiseLifecycleEvent("switch_mode");
			status = new ComponentOrderStatus.Builder().status(OrderStatus.ACCEPTED).message("mode switched").build();
		} else {
			// illegal state
			LOGGER.error(String.format("cannot switch to mode %s in state %s", mode, state));
			status = new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(String.format("cannot switch to mode %s in state %s", mode, state)).build();
		}
		return status;
	}
	

//	@Override
//	public synchronized OrderStatus setUnitConfig(UnitConfiguration config) {
//		if (getExecutionState() == ExecutionState.IDLE) {
//			this.config = config;
//			OrderStatus status = new ComponentOrderStatus.Builder().status(Status.ACCEPTED).message("config set").build();
//			return status;
//		} else {
//			// illegal state
//			LOGGER.error(String.format("cannot set config in state %s", getExecutionState()));
//			OrderStatus status = new ComponentOrderStatus.Builder().status(Status.REJECTED).message(String.format("cannot set config in state %s", getExecutionState())).build();
//			return status;
//		}
//	}

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
	 * ActiveStatesHandler facade
	 */

	@Override
	public void onResetting() {
		LOGGER.trace("onResetting()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onResetting();
				}
			});
		}
	}

	@Override
	public void onStarting() {
		LOGGER.trace("onStarting()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onStarting();
				}
			});
		}
	}

	@Override
	public void onExecute() {
		LOGGER.trace("onExecute()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onExecute();
				}
			});
		}
	}

	@Override
	public void onCompleting() {
		LOGGER.trace("onCompleting()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onCompleting();
				}
			});
		}
	}

	@Override
	public void onHolding() {
		LOGGER.trace("onHolding()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onHolding();
				}
			});
		}
	}

	@Override
	public void onUnholding() {
		LOGGER.trace("onUnholding()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onUnholding();
				}
			});
		}
	}

	@Override
	public void onSuspending() {
		LOGGER.trace("onSuspending()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onSuspending();
				}
			});
		}
	}

	@Override
	public void onUnsuspending() {
		LOGGER.trace("onUnsuspending()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onUnsuspending();
				}
			});
		}
	}

	@Override
	public void onAborting() {
		LOGGER.trace("onAborting()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onAborting();
				}
			});
		}
	}

	@Override
	public void onClearing() {
		LOGGER.trace("onClearing()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onClearing();
				}
			});
		}
	}

	@Override
	public void onStopping() {
		LOGGER.trace("onStopping()");
		if (actHandler != null) {
			schedule(new Runnable() {
				@Override
				public void run() {
					actHandler.onStopping();
				}
			});
		}
	}

	/*
	 * WaitStatesHandler facade
	 */

	@Override
	public void onStopped() {
		LOGGER.trace("onStopped()");
		if (waitHandler != null) {
			waitHandler.onStopped();
		}
	};

	@Override
	public void onIdle() {
		LOGGER.trace("onIdle()");
		if (waitHandler != null) {
			waitHandler.onIdle();
		}
	};

	@Override
	public void onComplete() {
		LOGGER.trace("onComplete()");
		if (waitHandler != null) {
			waitHandler.onComplete();
		}
	};

	@Override
	public void onHeld() {
		LOGGER.trace("onHeld()");
		if (waitHandler != null) {
			waitHandler.onHeld();
		}
	};

	@Override
	public void onSuspended() {
		LOGGER.trace("onSuspended()");
		if (waitHandler != null) {
			waitHandler.onSuspended();
		}
	};

	@Override
	public void onAborted() {
		LOGGER.trace("onAborted()");
		if (waitHandler != null) {
			waitHandler.onAborted();
		}
	};
	
}
