package de.dfki.cos.basys.controlcomponent.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.SharedParameterSpace;
import de.dfki.cos.basys.controlcomponent.annotation.Parameter;

public class SharedParameterSpaceImpl implements SharedParameterSpace {

	private final Logger LOGGER;
	private ControlComponent component = null;
	private Map<String, ParameterInstance> parameters = new HashMap<>();

	public SharedParameterSpaceImpl(BaseControlComponent component) {
		LOGGER = LoggerFactory.getLogger(component.LOGGER.getName() + ".SharedParameterSpace");
	}

	@Override
	public List<ParameterInfo> getParameters() throws ComponentException {
		List<ParameterInfo> result = new ArrayList<>(parameters.size());
		for (ParameterInstance inst : parameters.values()) {
			ParameterInfo info = inst.toParameterInfo();
			result.add(info);
		}
		return result;
	}

	@Override
	public ParameterInfo getParameter(String name) throws ComponentException {
		if (parameters.containsKey(name)) {
			return parameters.get(name).toParameterInfo();
		} else {
			return null;
		}
	}

	@Override
	public Object getParameterValue(String name) throws ComponentException {
		ParameterInstance inst = parameters.get(name);
		if (inst != null) {
			return inst.getValue();
		} else {
			throw new ComponentException("Could not find parameter " + name);
		}
	}

	@Override
	public void setParameterValue(String name, Object value) throws ComponentException {
		ParameterInstance inst = parameters.get(name);
		if (inst != null) {
			inst.setValue(value);
		} else {
			throw new ComponentException("Could not find parameter " + name);
		}

	}

	protected void registerOperationMode(OperationMode mode) {
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(mode.getClass(), Parameter.class);
		for (Field field : fields) {
			Parameter p = field.getAnnotation(Parameter.class);
			field.setAccessible(true);
			parameters.put(p.name(),new ParameterInstance(mode, field));
		}
	}

	protected void unregisterOperationMode(OperationMode mode) {
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(mode.getClass(), Parameter.class);
		for (Field field : fields) {
			Parameter p = field.getAnnotation(Parameter.class);
			field.setAccessible(false);
			parameters.remove(p.name());
		}
	}

	private class ParameterInstance {
		Parameter annotation;
		Field parameter;
		OperationMode mode;
		private ParameterInstance(OperationMode mode, Field parameter) {
			this.mode = mode;
			this.parameter = parameter;
			this.annotation = parameter.getAnnotation(Parameter.class);
		}
		
		public Object getValue() throws ComponentException {			
			try {
				return parameter.get(mode);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				LOGGER.error(e.getLocalizedMessage());
				throw new ComponentException("Could not retrieve value of parameter " + annotation.name(),e);			
			}			
		}
		
		public void setValue(Object value) throws ComponentException {
			try {
				parameter.set(mode, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				LOGGER.error(e.getLocalizedMessage());
				throw new ComponentException("Could not retrieve value of parameter " + annotation.name(),e);			
			}	
		}
		
		public ParameterInfo toParameterInfo() throws ComponentException {		
				
			ParameterInfo info = new ParameterInfo.Builder()
				.name(annotation.name())
				.access(annotation.access())
				.type(parameter.getType().getSimpleName())
				.value(getValue())
				.build();
				
			return info;
		}
	}
}
