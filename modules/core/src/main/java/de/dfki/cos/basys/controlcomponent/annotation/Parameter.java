package de.dfki.cos.basys.controlcomponent.annotation;

import de.dfki.cos.basys.controlcomponent.ParameterDirection;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Parameter {	
	public String name();
	public ParameterDirection direction(); 
	//public ParameterType type();
}
