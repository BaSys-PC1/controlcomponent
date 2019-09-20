package de.dfki.cos.basys.controlcomponent.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface OperationMode {
	public String name();
	public String shortName();
	public String description();
	public ExecutionCommand[] allowedCommands() default {ExecutionCommand.START, ExecutionCommand.STOP, ExecutionCommand.RESET };
	public ExecutionMode[] allowedModes() default {ExecutionMode.PRODUCTION, ExecutionMode.SIMULATION };
}
