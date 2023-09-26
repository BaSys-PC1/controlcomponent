package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.*;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseOperationMode<T> implements de.dfki.cos.basys.controlcomponent.OperationMode {
	
	protected final Logger LOGGER;
	protected String name, shortName;
	
	protected BaseControlComponent<T> component;
	
	protected Lock lock;
	protected Condition executeCondition;
	
	protected Map<String, Field> parameters;

	private T serviceMock = null;
	
	public BaseOperationMode(BaseControlComponent<T> component) {
		this.component = component;
		
		OperationMode annotation = this.getClass().getAnnotation(OperationMode.class);
		this.name = annotation.name();	
		this.shortName = annotation.shortName();
		this.lock = new ReentrantLock();
		this.executeCondition = lock.newCondition();	
		
		LOGGER = LoggerFactory.getLogger(component.LOGGER.getName() + "." + getName());
		
		// initialize parameters map
		getParameters();
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getShortName() {
		return shortName;
	}

	public OperationModeInfo getInfo() {
		OperationMode annotation = this.getClass().getAnnotation(OperationMode.class);
		OperationModeInfo info = new OperationModeInfo.Builder()
				.name(annotation.name())
				.shortName(annotation.shortName())
				.description(annotation.description())
				.executionModes(Arrays.asList(annotation.allowedModes()))
				.executionCommands(Arrays.asList(annotation.allowedCommands()))
				.parameters(getParameters()).build();
		return info;
	}
	
	@Override
	public ParameterInfo getParameter(String name) {
		List<ParameterInfo> parameters = getParameters();
		Optional<ParameterInfo> result = parameters.stream().filter(p -> p.getName().equals(name)).findFirst();
		if (result.isPresent())
			return result.get();
		else return null;
	}
	
	@Override
	public List<ParameterInfo> getParameters() {
		
		if (parameters == null) {
			parameters = new HashMap<>();
			List<Field> fields = FieldUtils.getFieldsListWithAnnotation(this.getClass(), Parameter.class);
			for (Field field : fields) {
				Parameter p = field.getAnnotation(Parameter.class);
				field.setAccessible(true);
				parameters.put(p.name(), field);
			}
		}
		
		List<ParameterInfo> infos = new ArrayList<>(parameters.size());
		for (Field field : parameters.values()) {
			Parameter p = field.getAnnotation(Parameter.class);
			try {
				Object value = field.get(this);
				
				ParameterInfo info = new ParameterInfo.Builder()
						.name(p.name())
						.access(p.direction())
						.type(field.getType().getSimpleName())
						.value(value)
						.build();
				
				infos.add(info);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(this.getClass(), Parameter.class);
		
		return infos;
	}
	
	@Override
	public List<ParameterInfo> getInputParameters() {		
		List<ParameterInfo> parameters = getParameters();
		ParameterInfo[] result = parameters.stream().filter(p -> p.getAccess() != ParameterDirection.OUT).toArray(ParameterInfo[]::new);
		return Arrays.asList(result);		
	}
	@Override
	public List<ParameterInfo> getOutputParameters() {
		List<ParameterInfo> parameters = getParameters();
		ParameterInfo[] result = parameters.stream().filter(p -> p.getAccess() != ParameterDirection.IN).toArray(ParameterInfo[]::new);
		return Arrays.asList(result);				
	}
	
	public List<ParameterInfo> getParameters(ParameterDirection access) {
		List<ParameterInfo> parameters = getParameters();
		ParameterInfo[] result = parameters.stream().filter(p -> p.getAccess() == access).toArray(ParameterInfo[]::new);		
		return Arrays.asList(result);	
	}

	@Override
	public List<ExecutionCommand> getExecutionCommands() {
		OperationMode annotation = this.getClass().getAnnotation(OperationMode.class);
		return Arrays.asList(annotation.allowedCommands());
	}

	@Override
	public List<ExecutionMode> getExecutionModes() {
		OperationMode annotation = this.getClass().getAnnotation(OperationMode.class);
		return Arrays.asList(annotation.allowedModes());
	}

//	public <T> T getService() {
//		return component.getService();
//	}
	
	public T getService(Class<T> serviceInterface) {
		if (component.getCurrentExecutionMode().getExecutionMode() == ExecutionMode.SIMULATE
				&& component.getCurrentExecutionMode().getService() == null) {
			if (serviceMock == null) {
				serviceMock = Mockito.mock(serviceInterface);
				configureServiceMock(serviceMock);
			}
			return serviceMock;
		}
		
		return component.getCurrentExecutionMode().getService();
	}
		
	protected void configureServiceMock(T serviceMock) {
		
	}
	
	protected void awaitExecuteComplete() {
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
	
	protected void signalExecuteComplete() {
		lock.lock();
		executeCondition.signalAll();
		lock.unlock();
	}
	
	protected void sleep(long millis) {
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Default implementations
	 */
	

	@Override
	public void onHolding() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnholding() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuspending() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsuspending() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAborting() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClearing() {
		// TODO Auto-generated method stub

	}


}
