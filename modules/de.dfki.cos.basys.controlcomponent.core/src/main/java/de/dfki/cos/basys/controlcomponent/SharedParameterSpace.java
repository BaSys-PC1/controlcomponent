package de.dfki.cos.basys.controlcomponent;

import java.util.List;

import de.dfki.cos.basys.common.component.ComponentException;

public interface SharedParameterSpace {
	
	List<ParameterInfo> getParameters() throws ComponentException;
	ParameterInfo getParameter(String name) throws ComponentException;
	
	Object getParameterValue(String name) throws ComponentException;
	void setParameterValue(String name, Object value) throws ComponentException;	
	
}
