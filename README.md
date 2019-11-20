# A BaSys 4.2 Control Component Implementation #

The rational behind the BaSys Control Component concept is explained in depth in the [BaSyx Wiki](http://wiki.eclipse.org/BaSyx_/_Documentation_/_API_/_ControlComponent) as well as in a [Technical Report (in German)](http://publications.rwth-aachen.de/record/728724).

In short, a control component consists of a set of operation modes that are independent from each other. An operation mode offers a high-level capability of an hardware asset that can be used in a production process, hence real-time coordination between operation modes is not required. Additionally, an operation mode can offer lower level capabilities that are only accessible in non-production execution modes, e.g. maintenance.

Typically, you want to create control components on top of existing traditional I3.0 hardware like embedded controllers and PLCs. Such a control component exposes its status and services including operation modes e.g. via an OPC-UA information model.

Newer production and logistics hardware like collaborative robots and automated guided vehicles provide powerful application programming interfaces (API) in the form of Web services, e.g. via HTTP-REST, XML-RPC and the like.

The purpose of this implementation is to create control components and corresponding operation modes for these assets in the Java programming language. By leveraging the provided API, a developer can concentrate on the pure control component and does not need to be an OPC-UA expert. The OPC-UA server and information model is created automatically. An example of the current structure of the information model is shown in the following screenshot. Please note that the structure will (slightly) change in the future due to consolidation and standardisation efforts in the BaSys 4.2 project. However, as a user of the provided client library, you won't even notice that.

<img src='/docs/opcua-information-model.png?raw=true' width='100%' height='100%'>

## How-To implement a BaSys 4.2 Control Component ##

In principle, you need to 
 - design a service interface for the asset (= a functional Java interface), that abstracts from the concrete communication protocol and API of the actual component,
 - implement the service interface together with a service connection interface that takes into account the concrete communication protocol and API of the actual component, 
 - a set of operation modes, that make use of the service interface and
 - a control component, that bundles everything together.
  
As an example, have a look into the [ExampleControlComponent](modules/de.dfki.cos.basys.controlcomponent.example) or the [ControlComponent for the MiR platform](modules/de.dfki.cos.basys.controlcomponent.mir).

1. Design a service interface that abstracts from the concrete communication protocol and API of the actual component.

```java
public interface MyServiceInterface
{
    boolean doSomething();
}
``` 
2. Implement the service interface together with a service connection interface that takes into account the concrete communication protocol and API of the actual component
```java
public class MyServiceImpl implements MyServiceInterface, ServiceConnection 
{
    public MyServiceImpl() {}
    
    public MyServiceImpl(Properties config) {}

    //from ServiceConnection
    @Override
    public boolean connect(ComponentContext context, String connectionString) {
        // TODO connect to component
        return true;
    }

    //from ServiceConnection
    @Override
    public void disconnect() {
        // TODO disconnect from component
    }
    
    //from ServiceConnection
    @Override
    public boolean isConnected() {
        // TODO check if connected to component
        return true;
    }
    
    //from MyServiceInterface
    public boolean doSomething() {
        // TODO call method on component and return some kind of status.
        return true;
    };
}
```  


2. Create a set of Operation Modes that extends BaseOperationMode. By applying the @OperationMode Java annotation, you can specify relevant meta-data for the OPC-UA information model: a (short) name, a description, as well as a set of supported Execution Commands and allowed Execution Modes.
```java
@OperationMode(description = "this is sample operation mode", name = "mymode", shortName = "mymode", 
		allowedCommands = { ExecutionCommand.RESET, ExecutionCommand.START, ExecutionCommand.STOP }, 
		allowedModes = { ExecutionMode.PRODUCTION, ExecutionMode.SIMULATION })
public class MyOperationMode extends BaseOperationMode {

    public MyOperationMode(MyControlComponent component) {
        super(component);
    }
    ...
```  

3. Inside the operation mode, specify a set of required variables in terms of input and output parameters. By applying the @Parameter Java annotation, you can specify relevant meta-data for the OPC-UA information model: a name and - depending on the parameter direction - access rights.
```java
    @Parameter(name = "in", direction = ParameterDirection.IN)
    public String inputStringParameter = "writeOnlyString";
    
    @Parameter(name = "out", direction = ParameterDirection.OUT)
    private int outputIntParameter = 42;
    
``` 

4. Implement the neccessary on*() handler methods according to the underlying PackML state automaton and the supported execution commands. Here, you propably want to access the service interface. As the service implementation returned by the getService() method might change during run-time (currently not yet implemented), e.g. due to different execution modes, you must not store a reference to the returned java object.
```java
    @Override
    public void onResetting() {
        ...
    }
    
    @Override
    public void onStarting() {
        getService(MyServiceInterface.class).doSomething();
    }
    
    @Override
    public void onExecute() {
        ...
    }
    
    @Override
    public void onCompleting() {
        ...
    }
```


5. Create a class MyControlComponent that extends BaseControlComponent. The custom service implementation is created inside the contructor by means of a ServiceManager that connects the service implementation to its back-end on component activation. The method registerOperationModes() gives you an anchor to create and assign operation modes to a control component inside its implementation.
```java
public class MyControlComponent extends BaseControlComponent {
	
    public ProcessControllerComponent(Properties config) {
        super(config);
        serviceManager = new ServiceManagerImpl<MyServiceInterface>(config, new Supplier<MyServiceImpl>() {
            @Override
            public MyServiceImpl get() {
                MyServiceImpl client = new MyServiceImpl(config);
                // TODO do other setup and config tasks
                return client;
            }
        });	
    }
	
    **alternatively**
    
    public ProcessControllerComponent(Properties config) {
        super(config);
        serviceManager = new ServiceManagerImpl<MyServiceInterface>(config, MyServiceImpl::new);
    }
    
    @Override
    protected void registerOperationModes() {
        OperationMode myOpMode = new MyOperationMode(this);
        registerOperationMode(myOpMode);
    }
}
```  

## How-To configure a BaSys 4.2 Control Component ##

The configuration of a control component is accomplished by Java Properties object. It must at least contain an id and a name. If the control component needs to connect to a back-end via a service implementation, a serviceConnectionString is required. The format of the serviceConnectionString is specific for the service implementation. If the component is created via Java reflection, you have to also specify the implementationJavaClass. 

```java
Properties config = new Properties();
config.put(StringConstants.id, "component-1");
config.put(StringConstants.name, "MyControlComponent");
config.put(StringConstants.implementationJavaClass, "my.namespace.MyControlComponent");
config.put(StringConstants.serviceConnectionString, "some url");
```  


## How-To deploy a BaSys 4.2 Control Component ##

Currently, control components can be deployed on a custom [OPC-UA server implementation](modules/modules/de.dfki.cos.basys.controlcomponent.server). Add your control component implementations as a maven dependency to the server's [POM file](modules/modules/de.dfki.cos.basys.controlcomponent.server/pom.xml) and perform a `mvn clean package` command on the command line in the server project folder. This creates a ZIP archive containing all dependencies in the target folder. Unzip the archive in a destination folder of your choice and execute the batch file [`run_server.bat`](https://basys.dfki.dev/gitlab/i40/basys/controlcomponent/blob/develop/modules/de.dfki.cos.basys.controlcomponent.server/src/main/command/run_server.bat).

### Server configuration ###

The default server configuration is stored in the file [`config.properties`](https://basys.dfki.dev/gitlab/i40/basys/controlcomponent/blob/develop/modules/de.dfki.cos.basys.controlcomponent.server/src/main/command/config.properties). It defines the following parameters and values, which can be overridden either in the file itself or by applying corresponding command line arguments as shown in [`run_server.bat`](https://basys.dfki.dev/gitlab/i40/basys/controlcomponent/blob/develop/modules/de.dfki.cos.basys.controlcomponent.server/src/main/command/run_server.bat)

```properties
#Folder for storing and managing server and client certificates
certsFolder = certs/
#Folder for storing und managing configuration files of control components
componentConfigFolder = components/
#whether the componentConfigFolder should be scanned for config files recursively
recursive = false
#whether the addidions or deletions of config files in the componentConfigFolder should be handled appropriately during runtime (hot deployment)
watchFolder = true
# whether components are created asynchronously during start-up
async = false
# the TCP port of the OPC-UA server
tcpPort = 12685
# the HTTPS port of the OPC-UA server
httpsPort = 8443
```  

In order to create control component instances on server start-up, you have to provide appropriate config files and put them in the `$componentConfigFolder`, e.g. either in  .properties format (please pay attention to not using "..." in the value)

```properties
# the ID of the component
id=example-component
# the name of the component
name=example-component
# the connections string connecting to the actual back-end service
serviceConnectionString=http://...
# the Java class name of the implemented control component for allowing the ComponentManager creating an instance via Java reflection
implementationJavaClass=de.dfki.cos.basys.controlcomponent.example.ExampleControlComponent
# additional arbitrary custom properties are possible
key_a = val_a
```  

or JSON format

```json
{
  "id": "example-component",
  "name": "example-component",
  "serviceConnectionString": "http://...",
  "implementationJavaClass": "de.dfki.cos.basys.controlcomponent.example.ExampleControlComponent",
  "key_a": "val_a"
}
```  

If your client (e.g. the UaExpert) connects to the OPC-UA server via a secure connection, make sure to trust the client certificate. For this you have to move the initially rejected certificate from the `$certsFolder/pki/rejected` folder to the `$certsFolder/pki/trusted/certs` folder, disconnect (or close) your client, restart your server and reconnect (or start) your client again.
