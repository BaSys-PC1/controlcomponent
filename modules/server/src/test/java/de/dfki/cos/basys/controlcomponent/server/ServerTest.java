package de.dfki.cos.basys.controlcomponent.server;

import org.eclipse.basyx.submodel.metamodel.connected.ConnectedSubmodel;
import org.eclipse.basyx.submodel.metamodel.connected.submodelelement.operation.ConnectedOperation;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetype.ValueType;
import org.eclipse.basyx.vab.factory.java.ModelProxyFactory;
import org.eclipse.basyx.vab.protocol.api.IConnectorFactory;
import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnectorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ServerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testInvokeOperation() {
		//fail("Not yet implemented");


		IConnectorFactory provider = new HTTPConnectorFactory();
		ModelProxyFactory proxyFactory = new ModelProxyFactory(provider);
		String addr = "http://localhost:8088/interface/submodel";
		//String addr = "http://localhost:8088/interface/submodel/submodelElements/Operations/STOP/";
		ConnectedSubmodel sm = new ConnectedSubmodel(proxyFactory.createProxy(addr));

		ConnectedOperation op = (ConnectedOperation) sm.getSubmodelElement("Operations/STOP");
		//var inputs = op.getInputVariables();

		Property sender = new Property("senderId", ValueType.String);
		sender.setValue("daniel");

		var result = op.invoke(sender);

		var value = op.getEmbeddedDataSpecifications();

//		//ConnectedOperation op = new ConnectedOperation(new VABElementProxy("http://localhost:8088/interface/submodel",new SubmodelElementProvider(fac.getConnector(addr))));
//		var inv = op.get
//		var inputs = op.getInputVariables();
//		var outputs = op.getOutputVariables();
		System.out.println("done");
	}

}
