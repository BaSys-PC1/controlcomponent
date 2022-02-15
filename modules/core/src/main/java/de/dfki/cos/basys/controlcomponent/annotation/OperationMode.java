package de.dfki.cos.basys.controlcomponent.annotation;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface OperationMode {
	public String name();
	public String shortName();
	public String description();
	public ExecutionCommand[] allowedCommands() default {ExecutionCommand.START, ExecutionCommand.STOP, ExecutionCommand.RESET };
	public ExecutionMode[] allowedModes() default {ExecutionMode.PRODUCTION, ExecutionMode.SIMULATE };
}
