/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.server;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCurve.Config;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.identity.CompositeValidator;
import org.eclipse.milo.opcua.sdk.server.identity.UsernameIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.X509IdentityValidator;
import org.eclipse.milo.opcua.sdk.server.util.HostnameUtil;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.security.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.security.DefaultCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.DefaultTrustListManager;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateGenerator;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedHttpsCertificateBuilder;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerImpl;
import de.dfki.cos.basys.controlcomponent.server.aas.SubmodelHost;
import de.dfki.cos.basys.controlcomponent.server.examples.ExampleNamespace;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_USERNAME;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_X509;

public class Main {

    private static Properties defaultConfig = new Properties();
    private static ControlComponentServer server;
    private static ComponentManagerImpl componentManager;
    private static SubmodelHost submodelHost;


    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
        
        defaultConfig.setProperty("certsFolder", new File(System.getProperty("java.io.tmpdir"), "opcua_server_security").toString());
        defaultConfig.setProperty("componentConfigFolder", "src/test/resources/components");
        defaultConfig.setProperty("recursive", "false");
        defaultConfig.setProperty("watchFolder", "true");
        defaultConfig.setProperty("async", "false");
        defaultConfig.setProperty("tcpPort", "12685");
        defaultConfig.setProperty("httpsPort", "8443");
    }

    public static void main(String[] args) throws Exception {
    	 Options options = new Options();

         Option configFileOption = new Option("c", "config", true, "path to a config.properties file");
         configFileOption.setRequired(false);
         options.addOption(configFileOption); 

         Option certsFolderOption = new Option("x", "certsFolder", true, "folder containing server and client certificates");
         certsFolderOption.setRequired(false);
         options.addOption(certsFolderOption); 

         Option componentsFolderOption = new Option("cf", "componentConfigFolder", true, "folder containing component configurations");
         componentsFolderOption.setRequired(false);
         options.addOption(componentsFolderOption); 

         Option recursiveOption = new Option("r", "recursive", false, "scan folder recursively");
         recursiveOption.setRequired(false);
         options.addOption(recursiveOption);
         
         Option watchFolderOption = new Option("w", "watchFolder", false, "watch folder for new and deleted files");
         watchFolderOption.setRequired(false);
         options.addOption(watchFolderOption);

         Option asyncOption = new Option("a", "async", false, "create components asynchronously");
         asyncOption.setRequired(false);
         options.addOption(asyncOption);

         Option tpcPortOption = new Option("tcp", "tpcPort", true, "the server's TCP port");
         tpcPortOption.setRequired(false);
         options.addOption(tpcPortOption); 
         
         Option httpsPortOption = new Option("https", "httpsPort", true, "the server's HTTPS port");
         httpsPortOption.setRequired(false);
         options.addOption(httpsPortOption); 
         
         CommandLineParser parser = new DefaultParser();
         HelpFormatter formatter = new HelpFormatter();
         CommandLine cmd;
         
         Properties config = new Properties(defaultConfig);
         
         try {
             cmd = parser.parse(options, args);
             if (cmd.hasOption("c")) {
            	 File configFile = new File(cmd.getOptionValue("c"));            	
            	 config.load(new FileInputStream(configFile));            	
             }

             if (cmd.hasOption("x")) {
            	 config.setProperty("certsFolder", cmd.getOptionValue("x"));
             }
             if (cmd.hasOption("cf")) {
            	 config.setProperty("componentConfigFolder", cmd.getOptionValue("cf"));
             }
             if (cmd.hasOption("r")) {
            	 config.setProperty("recursive", cmd.getOptionValue("r"));
             }
             if (cmd.hasOption("w")) {
            	 config.setProperty("watchFolder", cmd.getOptionValue("w"));
             }
             if (cmd.hasOption("a")) {
            	 config.setProperty("async", cmd.getOptionValue("a"));
             }
             if (cmd.hasOption("tcp")) {
            	 config.setProperty("tcpPort", cmd.getOptionValue("tcp"));
             }
             if (cmd.hasOption("https")) {
            	 config.setProperty("httpsPort", cmd.getOptionValue("https"));
             }
             
         } catch (ParseException e) {
             System.out.println(e.getMessage());
             formatter.printHelp("utility-name", options);

             System.exit(1);
         }

    	server = new ControlComponentServer(config);
        server.startup().get();

        submodelHost = new SubmodelHost(config);
        submodelHost.startup();
        
        Properties componentManagerConfig = new Properties(config);
      	componentManagerConfig.put(StringConstants.id, "component-manager");
    	componentManagerConfig.put(StringConstants.name, "component-manager");
    	componentManagerConfig.put(StringConstants.serviceConnectionString, config.getProperty("componentConfigFolder"));
		componentManagerConfig.put("recursive", config.getProperty("recursive"));
		componentManagerConfig.put("watchFolder", config.getProperty("watchFolder"));
		componentManagerConfig.put("async", config.getProperty("async"));       	
        	
		componentManager = new ComponentManagerImpl(componentManagerConfig); 
		componentManager.activate(ComponentContext.getStaticContext());

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("Shutdown hook ran!");
                try {
					componentManager.deactivate();
	                submodelHost.shutdown();
	                server.shutdown().get();
				} catch (ComponentException | InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                
            }
        });

//      final CompletableFuture<Void> future = new CompletableFuture<>();
//      Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));
//      future.get();
      
    }

}
