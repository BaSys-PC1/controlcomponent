# A BaSys 4.2 Control Component Implementation #

The rational behind the BaSys Control Component concept is explained in depth in the [BaSyx Wiki](http://wiki.eclipse.org/BaSyx_/_Documentation_/_API_/_ControlComponent) as well as in a [Technical Report (in German)](http://publications.rwth-aachen.de/record/728724).

In short, a control component consists of a set of operation modes that are independent from each other. An operation mode offers a high-level capability of an hardware asset that can be used in a production process, hence real-time coordination between operation modes is not required. Additionally, an operation mode can offer lower level capabilities that are only accessible in non-production execution modes, e.g. maintenance.

Typically, you want to create control components on top of existing traditional I3.0 hardware like embedded controllers and PLCs. Such a control component exposes its status and services including operation modes e.g. via an OPC-UA information model.

Newer production and logistics hardware like collaborative robots and automated guided vehicles provide powerful application programming interfaces (API) in the form of Web services, e.g. via HTTP-REST, XML-RPC and the like.

The purpose of this implementation is to create control components and corresponding operation modes for these assets in the Java programming language. By leveraging the provided API, a developer can concentrate on the pure control component and does not need to be an OPC-UA expert. The OPC-UA server and information model is created automatically. An example of the current structure of the information model is shown in the following screenshot. Please note that the structure will (slightly) change in the future due to consolidation and standardisation efforts in the BaSys 4.2 project. However, as a user of the provided client library, you won't even notice that.

<img src='/docs/opcua-information-model.png?raw=true' width='100%' height='100%'>

## SDK Outline ##

This SDK contains server- and client-side software modules for implementing and interacting with Control Components.
 - The [_core_](modules/core) module provides the main API for implementing Control Components.
 - The [_server_](modules/server) module implements the OPC-UA server, its information model as well as AAS submodels for Control Components. As a CC developer, you don't need to touch this.
 - The [_spring_](modules/spring) module provides a Spring Boot integration layer that a CC developer should use in a concret CC implementation.
 - The [_example_](modules/example) module implements an example CC by means of the aforementioned modules.
 - The [_client_](modules/client) module provides a Java-based OPC-UA client for interacting with a CC via the OPC-UA server. This client is used e.g. in the [Control Component Agent](https://github.com/BaSys-PC1/process-control/blob/main/modules/cc-task-manager/src/main/java/de/dfki/cos/basys/processcontrol/cctaskmanager/util/ControlComponentAgent.java ).

## How-To implement a BaSys 4.2 Control Component ##

In principle, you need to 
 - design a service interface for the asset (= a functional Java interface) that abstracts from the concrete communication protocol and API of the actual component,
 - implement the service interface together with a service connection interface that takes into account the concrete communication protocol and API of the actual component, 
 - a set of operation modes that make use of the service interface, and
 - a control component that bundles everything together.
  
As an example, have a look into the [ExampleControlComponent](modules/example).

1. Design a service interface that abstracts from the concrete communication protocol and API of the actual component.

```java
public interface CalculationService {
	double calculateHypothenuseLength(double a, double b);
	long calculateFibonacci(int n);
}
``` 

2. Implement the service interface together with the typed ServiceProvider interface that may take into account the concrete communication protocol and API of the actual component. Have a look into concrete [control component implementations](https://github.com/dfkibasys/p4p-control-components/tree/master/modules) for more complex examples.
```java
public class CalculationServiceImpl implements CalculationService, ServiceProvider<CalculationService> {

	@Override //from ServiceProvider
	public boolean connect(ComponentContext context, String connectionString) {
		return true;
	}

	@Override //from ServiceProvider
	public void disconnect() {

	}

	@Override //from ServiceProvider
	public boolean isConnected() {		
		return true;
	}

	@Override //from ServiceProvider
	public CalculationService getService() {
		return this;
	}

	@Override //from CalculationService
	public double calculateHypothenuseLength(double a, double b) {		
		return Math.sqrt(a*a + b*b);
	}

	@Override //from CalculationService
	public long calculateFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1l;
		} else {
			return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
		}
	}
}

```  

3. Create a set of Operation Modes that extends BaseOperationMode. As type parameter specify the service interface from step 1. By applying the @OperationMode Java annotation, you can specify relevant meta-data for the OPC-UA information model: a (short) name, a description, as well as a set of supported Execution Commands and allowed Execution Modes.
```java
@OperationMode(
	name = "fibonacci", 
	shortName = "FIB", 
	description = "given a number n, this operation mode calculates the n-th fibonacci number", 
	allowedCommands = { ExecutionCommand.RESET,	ExecutionCommand.START,	ExecutionCommand.STOP }, 
	allowedModes = { ExecutionMode.AUTO, ExecutionMode.SIMULATE })
public class FibonacciOperationMode extends BaseOperationMode<CalculationService> {

    public FibonacciOperationMode(BaseControlComponent<CalculationService> component) {
		super(component);
	}


```  

4. Inside the operation mode, specify a set of required variables in terms of input and output parameters. By applying the @Parameter Java annotation, you can specify relevant meta-data for the OPC-UA information model: a name and - depending on the parameter direction - access rights.
```java
	@Parameter(name = "fib_n", direction = ParameterDirection.IN)
	private int n = 0;

	@Parameter(name = "fib_result", direction = ParameterDirection.OUT)
	private long result = 0;
    
``` 

5. Implement the neccessary on*() handler methods according to the underlying PackML state automaton and the supported execution commands. Here, you propably want to access the service interface. As the service implementation returned by the getService() method might change during run-time depending on the Execution Mode (in SIMULATE you can use a simulation-specific implementaion whereas in AUTO you typically need to connect to hardware assets), you must not store a reference to the returned java object.
```java
    @Override
    public void onResetting() {
        ...
    }
    
    @Override
    public void onStarting() {
        ...
    }
    
    @Override
    public void onExecute() {
        result = getService(CalculationService.class).calculateFibonacci(n);
    }
    
    @Override
    public void onCompleting() {
        ...
    }
```
6. Instead of implementing a dedicated service interface for simulation you might want to use the internal service mock mechanism based on Mockito. Just configure the service interface by overriding the empty `configureServiceMock` method. 

```java
    @Override
	protected void configureServiceMock(BaxterService serviceMock) {
        ...
    }
}
```
An example can be found e.g. [here](https://github.com/dfkibasys/p4p-control-components/blob/master/modules/baxter/src/main/java/de/dfki/cos/basys/p4p/controlcomponent/baxter/opmodes/BaseBaxterOperationMode.java). Also note that this configuration is done in the base operation mode for that particular asset such that the service mock is available to all derived concrete operation modes. **Hint**: Other common logic can also be placed in there.

7. With the introduction of the Spring Boot integration, you do not need to provide a custom ControlComponent implementation (e.g. derived from BaseControlComponent) anymore. Instead, you just need to provide a SpringBootApplication as main entry point for your control component. Important here is to add the Java package `de.dfki.cos.basys.controlcomponent.spring` to the `scanBasePackages` list and place the service interface/implementations as well as the implemented operation modes in distinct subfolders/packages `service` resp. `opmodes` below the ControlComponentApplication. The `opmodes` package can then be specificed in the application config for class path scanning that will automatically register found operation mode implementations to a generic control component instance. 
```java
@SpringBootApplication(scanBasePackages = "de.dfki.cos.basys.controlcomponent.spring")
public class ControlComponentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlComponentApplication.class, args);
	}

}
```  

## How-To configure a BaSys 4.2 Control Component ##

The configuration of a control component is accomplished by a Spring-based configuration, either as `application.properties` or `application.yml` file. 

```yml
# The control component automatically generates and hosts AAS control component submodels that are accessible via REST
server:
  # Standard Spring property, the HTTP server port that will be opened
  port: 8088
  # The endpoint that is accessible by clients in a network
  accessibleEndpoint: http://localhost:8088
basys:
  # The AAS registry configutation  
  aasRegistry:
    # The registry type/version, basys or dotaas (latest Plattform I4.0 spec)  
    type: dotaas
    service:
      # The REST endpoint of the AAS registry
      connectionString: http://localhost:8080
  # The event transmitter configuration for sending AAS events via mqtt
  eventTransmitter:
    # for now only mqtt is possible
    type: mqtt
    service:
      # leave as is
      implementationJavaClass: de.dfki.cos.basys.aas.event.mqtt.MqttEventTransmitter
      # The MQTT broker endpoint 
      connectionString: tcp://localhost:1883
  opcuaServer:
    # Folder for storing and managing server and client certificates
    # If your client (e.g. the UaExpert) connects to the OPC-UA server via a secure connection, 
    # make sure to trust the client certificate. For this you have to move the initially rejected 
    # certificate from the `$certsFolder/pki/rejected` folder to the `$certsFolder/pki/trusted/certs` 
    # folder, disconnect (or close) your client, restart your server and reconnect (or start) your client again.
    certsFolder: ./certs
    # the TCP port of the created OPC-UA server
    tcpPort: 12685
    # the HTTPS port of the created OPC-UA server
    httpsPort: 8443
  controlcomponent:
    # the ID of the component
    id: calculation_cc
    # the name of the component
    name: Calculation Control Component
    # all operation modes in this package will be instantiated an registed to a generic control component instance
    operationModeJavaPackage: de.dfki.cos.basys.controlcomponent.example.opmodes
    # start execution mode
    executionMode: SIMULATE
    # configuration for AUTO execution mode
    auto:
      # allow to change the execution mode
      disableExecutionModeChange: false
      # check if control component is properly occupied by the sender of a command
      disableOccupationCheck: false
      # service configuration for AUTO
      service:          
        # the service implementation class that will be instantiated
        implementationJavaClass: de.dfki.cos.basys.controlcomponent.example.service.CalculationServiceImpl
        # the service connection string (mandatory)
        connectionString: autoConnectionString
        # additional values
        prop1: value1
    # configuration for SIMULATE execution mode
    simulate:
      # if in SIMULATE stay there (might be a safety issue otherwise)
      disableExecutionModeChange: true
      # just ignore occupation (makes debugging easier via a generic OPC-UA client)
      disableOccupationCheck: true
      # service configuration for SIMULATE. If ommited as in the example here, an internal Mockito-based service mock mechanism is used instead.
      #service:
      #  implementationJavaClass: de.dfki.cos.basys.controlcomponent.example.service.CalculationServiceImpl
      #  connectionString: simulateConnectionString
      #  prop1: anothervalue2

```  


## How-To deploy a BaSys 4.2 Control Component ##

The control component can be deployed and launched as individual Spring Boot Java application - also inside a Docker Container/Kubernetes Pod. If you rely on or stick to our CI chain, just place a Dockerfile in src/main/docker as in [this example](https://github.com/dfkibasys/p4p-control-components/tree/master/modules/mir/src/main/docker)

```dockerfile
FROM openjdk:11-jre-slim as builder
COPY maven/${project.build.finalName}.jar ./
RUN java -Djarmode=layertools -jar ${project.build.finalName}.jar extract

FROM openjdk:11-jre-slim
WORKDIR /workspace
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
RUN true
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 8088
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/.urandom", "org.springframework.boot.loader.JarLauncher"]
```
