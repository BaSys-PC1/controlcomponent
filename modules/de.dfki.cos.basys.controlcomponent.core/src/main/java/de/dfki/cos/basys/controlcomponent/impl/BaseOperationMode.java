package de.dfki.cos.basys.controlcomponent.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.reflect.FieldUtils;

import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;

public abstract class BaseOperationMode implements de.dfki.cos.basys.controlcomponent.OperationMode {
	
	protected String name;
	//protected OperationModeInfo info = null;
	
	protected Lock lock;
	protected Condition executeCondition;
	
	public BaseOperationMode() {
		OperationMode annotation = this.getClass().getAnnotation(OperationMode.class);
		this.name = annotation.name();		
		this.lock = new ReentrantLock();
		this.executeCondition = lock.newCondition();		
	}

	@Override
	public String getName() {
		return name;
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
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(this.getClass(), Parameter.class);
		List<ParameterInfo> parameters = new ArrayList<>(fields.size());
		for (Field field : fields) {
			Parameter p = field.getAnnotation(Parameter.class);
								
			try {
				field.setAccessible(true);
				Object value = field.get(this);		
			
				ParameterInfo info = new ParameterInfo.Builder()
						.name(p.name())
						.direction(p.direction())
						.value(value)
						.build();
				
				parameters.add(info);
			
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return parameters;
	}
	
	@Override
	public List<ParameterInfo> getInputParameters() {		
		List<ParameterInfo> parameters = getParameters();
		ParameterInfo[] result = parameters.stream().filter(p -> p.getDirection() != ParameterDirection.OUT).toArray(ParameterInfo[]::new);		
		return Arrays.asList(result);		
	}
	@Override
	public List<ParameterInfo> getOutputParameters() {
		List<ParameterInfo> parameters = getParameters();
		ParameterInfo[] result = parameters.stream().filter(p -> p.getDirection() != ParameterDirection.IN).toArray(ParameterInfo[]::new);		
		return Arrays.asList(result);				
	}
	
	public List<ParameterInfo> getParameters(ParameterDirection direction) {
		List<ParameterInfo> parameters = getParameters();
		ParameterInfo[] result = parameters.stream().filter(p -> p.getDirection() == direction).toArray(ParameterInfo[]::new);		
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
