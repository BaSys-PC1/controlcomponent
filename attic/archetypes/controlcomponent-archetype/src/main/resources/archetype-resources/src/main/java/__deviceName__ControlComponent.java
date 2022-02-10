#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Properties;

import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class ${deviceName}ControlComponent extends BaseControlComponent {

	public ${deviceName}ControlComponent(Properties config) {
		super(config);
	}
	
	@Override
	protected void registerOperationModes() {		
		OperationMode opMode = new ${opModeName}OperationMode(this);
		registerOperationMode(opMode);		
	}
	
}
