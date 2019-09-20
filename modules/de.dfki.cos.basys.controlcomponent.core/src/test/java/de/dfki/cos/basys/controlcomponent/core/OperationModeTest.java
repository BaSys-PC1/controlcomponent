package de.dfki.cos.basys.controlcomponent.core;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OperationModeTest {

	OperationMode opMode = null;
	
	@Before
	public void setUp() throws Exception {
		opMode = new TestOperationMode();
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParameters() {
		OperationModeInfo info = opMode.getInfo();
		
		List<ParameterInfo> parameters = opMode.getParameters();
		
		
		List<ParameterInfo> inputParameters = opMode.getInputParameters();
		
		List<ParameterInfo> outputParameters = opMode.getOutputParameters();

		
	}

}
