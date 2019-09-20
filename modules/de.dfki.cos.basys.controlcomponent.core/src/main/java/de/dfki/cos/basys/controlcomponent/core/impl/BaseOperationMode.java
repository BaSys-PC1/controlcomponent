package de.dfki.cos.basys.controlcomponent.core.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.reflect.FieldUtils;

import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.core.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.core.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.core.ParameterInfo;

public abstract class BaseOperationMode implements de.dfki.cos.basys.controlcomponent.core.OperationMode {

	
	private String name;
	
	public BaseOperationMode() {
		OperationMode annotation = this.getClass().getAnnotation(OperationMode.class);
		this.name = annotation.name();		
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
				.modes(Arrays.asList(annotation.allowedModes()))
				.commands(Arrays.asList(annotation.allowedCommands()))
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
	

}
