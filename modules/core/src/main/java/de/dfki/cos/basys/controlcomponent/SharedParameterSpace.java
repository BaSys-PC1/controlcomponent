package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.common.component.ComponentException;

import java.util.List;

public interface SharedParameterSpace {
	
	List<ParameterInfo> getParameters() throws ComponentException;
	ParameterInfo getParameter(String name) throws ComponentException;
	
	Object getParameterValue(String name) throws ComponentException;
	void setParameterValue(String name, Object value) throws ComponentException;	
	
}
