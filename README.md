# A BaSys 4.2 Control Component Implementation #

The rational behind the BaSys Control Component concept is explained in depth in the [BaSyx Wiki](http://wiki.eclipse.org/BaSyx_/_Documentation_/_API_/_ControlComponent) as well as in a [Technical Report (in German)](http://publications.rwth-aachen.de/record/728724).

In short, a control component consists of a set of operation modes that are independent from each other. An operation mode offers a higher level capability of an hardware asset that can be used in a production process, hence real-time coordination between operation modes is not required. 

Typically, you want to create control components on top of existing traditional I3.0 hardware like embedded controllers and PLCs. Such a control component exposes its status and services including operation modes e.g. via an OPC-UA information model.

Newer production and logistics hardware like collaborative robots and automated guided vehicles provide powerful application programming interfaces (API) in the form of Web services, e.g. via HTTP-REST, XML-RPC and the like.

The purpose of this implementation is to create control components and corresponding operation modes for these assets in the Java programming language. By leveraging the provided API, a developer can concentrate on the pure control component and does not need to be an OPC-UA expert. The OPC-UA server and information model is created automatically. An example is shown in the following screenshot:

<img src='/docs/opcua-information-model.png?raw=true' width='100%' height='100%'>

