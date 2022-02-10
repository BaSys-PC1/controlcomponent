package de.dfki.cos.basys.controlcomponent.server;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.basyx.aas.aggregator.api.IAASAggregator;
import org.eclipse.basyx.aas.aggregator.proxy.AASAggregatorProxy;
import org.eclipse.basyx.aas.aggregator.restapi.AASAggregatorProvider;
import org.eclipse.basyx.aas.manager.api.IAssetAdministrationShellManager;
import org.eclipse.basyx.aas.metamodel.map.AssetAdministrationShell;
import org.eclipse.basyx.aas.registration.api.IAASRegistry;
import org.eclipse.basyx.aas.registration.proxy.AASRegistryProxy;
import org.eclipse.basyx.aas.registry.compatibility.DotAASRegistryProxy;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.vab.coder.json.connector.JSONConnector;
import org.eclipse.basyx.vab.protocol.http.connector.HTTPConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.aas.services.ServletContainerComponent;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerImpl;
import de.dfki.cos.basys.controlcomponent.server.aas.ControlComponentSubmodelServer;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;

public class Main {
	
	public static Logger LOGGER = LoggerFactory.getLogger("ControlComponent-Server-Main");

	private static ControlComponentServer server;
	private static ComponentManagerImpl componentManager;

	private static Properties serverConfig = new Properties(ControlComponentServer.getDefaultConfig());
	private static Properties componentManagerConfig = new Properties();
	private static Properties servletContainerConfig = new Properties(ServletContainerComponent.getDefaultConfig());

	private static String aasRegistryType = "basyx";
	private static String aasRegistryEndpoint = "http://localhost:4999";
	private static String aasAggregatorEndpoint = "http://localhost:5080";
	
	private static AasComponentContext context = AasComponentContext.getStaticContext();
	
	private static String configFolderPath = "config/";
	
	public static void main(String[] args) throws Exception {		
		Options options = new Options();

		Option configFileOption = new Option("c", "config", true, "path to a config folder");
		configFileOption.setRequired(false);
		options.addOption(configFileOption);

		Option certsFolderOption = new Option("xf", "certsFolder", true, "folder containing server and client certificates");
		certsFolderOption.setRequired(false);
		options.addOption(certsFolderOption);

		Option componentsFolderOption = new Option("cf", "componentConfigFolder", true, "folder containing component configurations");
		componentsFolderOption.setRequired(false);
		options.addOption(componentsFolderOption);

		Option aasRegistryTypeOption = new Option("rt", "aas-registry-type", true, "aas registry type: 'basyx' or 'dotaas'; if not specified, no registration will be performed");
		aasRegistryTypeOption.setRequired(false);
		options.addOption(aasRegistryTypeOption);
		
		Option aasRegistryOption = new Option("r", "aas-registry", true, "aas registry rest endpoint");
		aasRegistryOption.setRequired(false);
		options.addOption(aasRegistryOption);
		
		Option aasHostOption = new Option("a", "aas-aggregator", true, "aas aggregator rest endpoint");
		aasHostOption.setRequired(false);
		options.addOption(aasHostOption);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;			 
		
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("c")) {
				configFolderPath = cmd.getOptionValue("c");
			}
			
			Path serverConfigPath = Paths.get(configFolderPath, "opcua-server.properties");
			if (serverConfigPath.toFile().exists()) {
				serverConfig.load(new FileInputStream(serverConfigPath.toFile()));
				LOGGER.info("opcua-server.properties loaded: " + serverConfigPath.toFile());
			} else {
				LOGGER.warn("opcua-server.properties not found in " + serverConfigPath.toFile() + ". Using defaults.");
			}
			
			Path componentManagerConfigPath = Paths.get(configFolderPath, "component-manager.properties");
			if (componentManagerConfigPath.toFile().exists()) {
				componentManagerConfig.load(new FileInputStream(componentManagerConfigPath.toFile()));		
				LOGGER.info("component-manager.properties loaded: " + componentManagerConfigPath.toFile());
			} else {
				LOGGER.warn("component-manager.properties not found in " + componentManagerConfigPath.toFile() + ". Using defaults.");
			}
			
			Path servletContainerConfigPath = Paths.get(configFolderPath, "servlet-container.properties");
			if (servletContainerConfigPath.toFile().exists()) {
				servletContainerConfig.load(new FileInputStream(servletContainerConfigPath.toFile()));
				LOGGER.info("servlet-container.properties loaded: " + servletContainerConfigPath.toFile());
			} else {
				LOGGER.warn("servlet-container.properties not found in " + servletContainerConfigPath.toFile() + ". Using defaults.");
			}

			if (cmd.hasOption("xf")) {
				serverConfig.setProperty("certsFolder", cmd.getOptionValue("xf"));
			}

			if (cmd.hasOption("cf")) {
				componentManagerConfig.setProperty("serviceConnectionString", cmd.getOptionValue("cf"));
			}

			if (cmd.hasOption("r")) {
				aasRegistryEndpoint = cmd.getOptionValue("r");
			}
			
			if (cmd.hasOption("rt")) {
				aasRegistryType = cmd.getOptionValue("rt");
			}
			
			if (cmd.hasOption("a")) {
				aasAggregatorEndpoint = cmd.getOptionValue("a");
			}

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

		// 0. create AAS registry and manager client
		IAASRegistry aasRegistry = null;		
		if ("dotaas".equals(aasRegistryType)) {
			aasRegistry = new DotAASRegistryProxy(aasRegistryEndpoint);
			context.setAasRegistry(aasRegistry);
			LOGGER.info("DotAASRegistry loaded at \"" + aasRegistryEndpoint + "\"");
		} else if ("basyx".equals(aasRegistryType)) {
			aasRegistry = new AASRegistryProxy(aasRegistryEndpoint);
			context.setAasRegistry(aasRegistry);
			LOGGER.info("BaSyx AAS Registry loaded at \"" + aasRegistryEndpoint + "\"");
		} else { // defaulting to none
			LOGGER.info("no registry type specified");
		}		
		
		IAASAggregator aasAggregator = new AASAggregatorProxy(new JSONConnector(new HTTPConnector(aasAggregatorEndpoint)));
		context.setAasAggregator(aasAggregator);
				
		ControlComponentSubmodelServer ccSubmodelServer = new ControlComponentSubmodelServer(servletContainerConfig);
		ccSubmodelServer.activate(context);		
		
		// 1. start the OPC-UA server
		server = new ControlComponentServer(serverConfig);
		server.startup().get();			
		
		// 2. create component manager
		componentManager = new ComponentManagerImpl(componentManagerConfig);
		context.setComponentManager(componentManager);		
		componentManager.activate(context);			
		if (ccSubmodelServer != null)
			componentManager.addComponent(ccSubmodelServer);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Shutdown hook ran!");
				try {
					if (componentManager != null)
						componentManager.deactivate();
					if (server != null)
						server.shutdown().get();
				} catch (ComponentException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
