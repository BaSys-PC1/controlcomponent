package de.dfki.cos.basys.controlcomponent.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterType;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Parameter {	
	public String name();
	public ParameterDirection direction() default ParameterDirection.IN; 
	//public ParameterType type();
}
