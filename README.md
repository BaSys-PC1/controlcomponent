# A BaSys 4.2 Control Component Implementation #

The rational behind the BaSys Control Component concept is explained in depth in the [BaSyx Wiki](http://wiki.eclipse.org/BaSyx_/_Documentation_/_API_/_ControlComponent) as well as in a [Technical Report (in German)](http://publications.rwth-aachen.de/record/728724).

In short, a control component consists of a set of operation modes that are independent from each other. An operation mode offers a high-level capability of an hardware asset that can be used in a production process, hence real-time coordination between operation modes is not required. Additionally, an operation mode can offer lower level capabilities that are only accessible in non-production execution modes, e.g. maintenance.

Typically, you want to create control components on top of existing traditional I3.0 hardware like embedded controllers and PLCs. Such a control component exposes its status and services including operation modes e.g. via an OPC-UA information model.

Newer production and logistics hardware like collaborative robots and automated guided vehicles provide powerful application programming interfaces (API) in the form of Web services, e.g. via HTTP-REST, XML-RPC and the like.

The purpose of this implementation is to create control components and corresponding operation modes for these assets in the Java programming language. By leveraging the provided API, a developer can concentrate on the pure control component and does not need to be an OPC-UA expert. The OPC-UA server and information model is created automatically. An example is shown in the following screenshot:

<img src='/docs/opcua-information-model.png?raw=true' width='100%' height='100%'>

## How-To implement a BaSys 4.2 Control Component ##

In principle, you need to 
 - design a service interface for the asset (= a functional Java interface), that abstracts from the concrete communication protocol and API of the actual component,
 - implement the service interface together with a service connection interface that takes into account the concrete communication protocol and API of the actual component, 
 - a set of operation modes, and a control component, that bundles everything together.

1. Design and implement a Funtional Client that abstracts from the concrete communication protocol and API of the actual component.

```java
public interface MyServiceInterface
{
    boolean doSomething();
}

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

3. Inside the operation mode, specify a set of required variables in terms of input and output parameters. By applying the @Parameter Java annotation, you can specify relevant meta-data for the OPC-UA information model: a name and access rights.
```java
    @Parameter(name = "wo", access = VariableAccess.WRITE_ONLY)
    public String inputStringParameter = "writeOnlyString";
    
    @Parameter(name = "ro", access = VariableAccess.READ_ONLY)
    private int outputIntParameter = 42;
    
    @Parameter(name = "wr", access = VariableAccess.READ_WRITE)
    protected boolean inoutBooleanParameter = false;
``` 

4. Implement the neccessary on*() handler methods according to the underlying PackML state automaton and the supported execution commands. Here you propably want to access the functional client.
```java
    @Override
    public void onResetting() {
        ...
    }
    
    @Override
    public void onStarting() {
        getComponent().getFunctionalClient(MyFunctionalClient.class).doSomething();
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


5. Create a class MyControlComponent that extends BaseControlComponent. The custom functional client is indirectly created inside the contructor by means of a ConnectionManager that connects the functional client to its back-end on component activation. The method registerOperationModes() gives you an anchor to create and assign operation modes to a control component inside its implementation.
```java
public class MyControlComponent extends BaseControlComponent {
	
    public ProcessControllerComponent(Properties config) {
        super(config);
        connectionManager = new ConnectionManagerImpl(config, new Supplier<MyFunctionalClient>() {
            @Override
            public MyFunctionalClient get() {
                MyFunctionalClient client = new MyFunctionalClientImpl(config);
                // TODO do other setup and config tasks
                return client;
            }
        });	
    }
	
    **alternatively**
    
    public ProcessControllerComponent(Properties config) {
        super(config);
        connectionManager = new ConnectionManagerImpl(config, MyFunctionalClientImpl::new);
    }
    
    @Override
    protected void registerOperationModes() {
        OperationMode myOpMode = new MyOperationMode(this);
        registerOperationMode(myOpMode);
    }
}
```  

## How-To configure a BaSys 4.2 Control Component ##

The configuration of a control component is accomplished by Java Properties object. It must at least contain an id and a name. If the control component needs to connect to a back-end via the functional client, a connectionString is required. The format of the connectionString is specific for the functional client. If the component is created via Java reflection, you have to also specify the implementationJavaClass. 

```java
Properties config = new Properties();
config.put(StringConstants.id, "component-1");
config.put(StringConstants.name, "MyControlComponent");
config.put(StringConstants.implementationJavaClass, "my.namespace.MyControlComponent");
config.put(StringConstants.connectionString, "some url");
```  


## How-To deploy a BaSys 4.2 Control Component ##
* TODO: explain in more depth *

Currently a control component can be deployed on an OPC-UA server. For this you have to 
1. provide a Properties file for your control component and put it in a folder,
2. have all required jar-Files on the classpath
3. execute the OPC-UA server
```bash
java ControlComponentServer -f "folder"
```