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
import org.eclipse.basyx.aas.registration.api.IAASRegistryService;
import org.eclipse.basyx.aas.registration.proxy.AASRegistryProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.aas.services.ServletContainerComponent;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerImpl;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;

public class Main {
	
	public static Logger LOGGER = LoggerFactory.getLogger("ControlComponent-Server-Main");

	private static ControlComponentServer server;
	private static ComponentManagerImpl componentManager;

	private static Properties serverConfig = new Properties(ControlComponentServer.getDefaultConfig());
	private static Properties componentManagerConfig = new Properties();
	private static Properties servletContainerConfig = new Properties(ServletContainerComponent.getDefaultConfig());
	
	private static String aasRegistryEndpoint = "http://localhost:4999";
	
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
		
		Option aarRegistryOption = new Option("r", "aas-registry", true, "aas registry rest endpoint");
		aarRegistryOption.setRequired(false);
		options.addOption(aarRegistryOption);

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

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);

			System.exit(1);
		}

		// 0. create AAS registry client
		IAASRegistryService aasRegistry = new AASRegistryProxy(aasRegistryEndpoint);
		context.setAasRegistry(aasRegistry);
		
		ServletContainerComponent servletContainer = new ServletContainerComponent(servletContainerConfig);
		servletContainer.activate(context);		
		
		// 1. start the OPC-UA server
		server = new ControlComponentServer(serverConfig);
		server.startup().get();			
		
		// 2. create component manager
		componentManager = new ComponentManagerImpl(componentManagerConfig);
		context.setComponentManager(componentManager);		
		componentManager.activate(context);			
		if (servletContainer != null)
			componentManager.addComponent(servletContainer);

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
