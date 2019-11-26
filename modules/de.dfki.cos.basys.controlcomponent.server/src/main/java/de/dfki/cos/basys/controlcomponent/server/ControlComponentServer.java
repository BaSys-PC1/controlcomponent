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

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_USERNAME;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_X509;

public class ControlComponentServer {

//    private static final int TCP_BIND_PORT = Stack.DEFAULT_TCP_PORT;
//    private static final int HTTPS_BIND_PORT = 8443;
//
//    private static String certsFolder = new File(System.getProperty("java.io.tmpdir"), "opcua_server_security").toString();
//    private static String componentConfigFolder = "src/test/resources/components";
//    private static boolean recursive = false;
//    private static boolean async = false;

    private static Properties defaultConfig = new Properties();
    private Properties config = new Properties();
    
    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
        
        defaultConfig.setProperty("certsFolder", new File(System.getProperty("java.io.tmpdir"), "opcua_server_security").toString());
        defaultConfig.setProperty("componentConfigFolder", "src/test/resources/components");
        defaultConfig.setProperty("recursive", "false");
        defaultConfig.setProperty("watchFolder", "false");
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

    	ControlComponentServer server = new ControlComponentServer(config);

        server.startup().get();

        final CompletableFuture<Void> future = new CompletableFuture<>();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));
        future.get();
    }

    private final OpcUaServer server;
    private final ComponentManagerImpl componentManager;

    public ControlComponentServer() throws Exception {
		this(new Properties(defaultConfig));
	}

    public ControlComponentServer(Properties config) throws Exception {
    	this.config = config;
        File securityDir = new File(config.getProperty("certsFolder"));
        if (!securityDir.exists() && !securityDir.mkdirs()) {
            throw new Exception("unable to create certificate dir: " + securityDir);
        }
        LoggerFactory.getLogger(getClass()).info("certificate dir: {}", securityDir.getAbsolutePath());

        KeyStoreLoader loader = new KeyStoreLoader().load(securityDir);

        DefaultCertificateManager certificateManager = new DefaultCertificateManager(
            loader.getServerKeyPair(),
            loader.getServerCertificateChain()
        );

        File pkiDir = securityDir.toPath().resolve("pki").toFile();
        DefaultTrustListManager trustListManager = new DefaultTrustListManager(pkiDir);
        LoggerFactory.getLogger(getClass()).info("pki dir: {}", pkiDir.getAbsolutePath());

        DefaultCertificateValidator certificateValidator = new DefaultCertificateValidator(trustListManager);

        KeyPair httpsKeyPair = SelfSignedCertificateGenerator.generateRsaKeyPair(2048);

        SelfSignedHttpsCertificateBuilder httpsCertificateBuilder = new SelfSignedHttpsCertificateBuilder(httpsKeyPair);
        httpsCertificateBuilder.setCommonName(HostnameUtil.getHostname());
        HostnameUtil.getHostnames("0.0.0.0").forEach(httpsCertificateBuilder::addDnsName);
        X509Certificate httpsCertificate = httpsCertificateBuilder.build();

        UsernameIdentityValidator identityValidator = new UsernameIdentityValidator(
            true,
            authChallenge -> {
                String username = authChallenge.getUsername();
                String password = authChallenge.getPassword();

                boolean userOk = "user".equals(username) && "password1".equals(password);
                boolean adminOk = "admin".equals(username) && "password2".equals(password);

                return userOk || adminOk;
            }
        );

        X509IdentityValidator x509IdentityValidator = new X509IdentityValidator(c -> true);

        // If you need to use multiple certificates you'll have to be smarter than this.
        X509Certificate certificate = certificateManager.getCertificates()
            .stream()
            .findFirst()
            .orElseThrow(() -> new UaRuntimeException(StatusCodes.Bad_ConfigurationError, "no certificate found"));

        // The configured application URI must match the one in the certificate(s)
        String applicationUri = CertificateUtil
            .getSanUri(certificate)
            .orElseThrow(() -> new UaRuntimeException(
                StatusCodes.Bad_ConfigurationError,
                "certificate is missing the application URI"));

        Set<EndpointConfiguration> endpointConfigurations = createEndpointConfigurations(certificate);

        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
            .setApplicationUri(applicationUri)
            .setApplicationName(LocalizedText.english("BaSys OPC UA Control Component Server"))
            .setEndpoints(endpointConfigurations)
            .setBuildInfo(
                new BuildInfo(
                    "urn:dfki:basys:control-component-server",
                    "DFKI",
                    "BaSys OPC UA Control Component Server",
                    OpcUaServer.SDK_VERSION,
                    "", DateTime.now()))
            .setCertificateManager(certificateManager)
            .setTrustListManager(trustListManager)
            .setCertificateValidator(certificateValidator)
            .setHttpsKeyPair(httpsKeyPair)
            .setHttpsCertificate(httpsCertificate)
            .setIdentityValidator(new CompositeValidator(identityValidator, x509IdentityValidator))
            .setProductUri("urn:dfki:basys:control-component-server")            
            .build();

        server = new OpcUaServer(serverConfig);
        
        Properties componentManagerConfig = new Properties(config);
      	componentManagerConfig.put(StringConstants.id, "component-manager");
    	componentManagerConfig.put(StringConstants.name, "component-manager");
    	componentManagerConfig.put(StringConstants.serviceConnectionString, config.getProperty("componentConfigFolder"));
		componentManagerConfig.put("recursive", config.getProperty("recursive"));
		componentManagerConfig.put("watchFolder", config.getProperty("watchFolder"));
		componentManagerConfig.put("async", config.getProperty("async"));       	
        
		componentManager = new ComponentManagerImpl(componentManagerConfig); 
		
        ControlComponentNamespace ccNamespace = new ControlComponentNamespace(server);
        ccNamespace.startup();
    }

	private Set<EndpointConfiguration> createEndpointConfigurations(X509Certificate certificate) {
        Set<EndpointConfiguration> endpointConfigurations = new LinkedHashSet<>();

        List<String> bindAddresses = newArrayList();
        bindAddresses.add("0.0.0.0");

        Set<String> hostnames = new LinkedHashSet<>();
        hostnames.add(HostnameUtil.getHostname());
        hostnames.addAll(HostnameUtil.getHostnames("0.0.0.0"));

        for (String bindAddress : bindAddresses) {
            for (String hostname : hostnames) {
                EndpointConfiguration.Builder builder = EndpointConfiguration.newBuilder()
                    .setBindAddress(bindAddress)
                    .setHostname(hostname)
                    .setPath("/basys")
                    .setCertificate(certificate)
                    .addTokenPolicies(
                        USER_TOKEN_POLICY_ANONYMOUS,
                        USER_TOKEN_POLICY_USERNAME,
                        USER_TOKEN_POLICY_X509);


                EndpointConfiguration.Builder noSecurityBuilder = builder.copy()
                    .setSecurityPolicy(SecurityPolicy.None)
                    .setSecurityMode(MessageSecurityMode.None);

                endpointConfigurations.add(buildTcpEndpoint(noSecurityBuilder));
                endpointConfigurations.add(buildHttpsEndpoint(noSecurityBuilder));

                // TCP Basic256Sha256 / SignAndEncrypt
                endpointConfigurations.add(buildTcpEndpoint(
                    builder.copy()
                        .setSecurityPolicy(SecurityPolicy.Basic256Sha256)
                        .setSecurityMode(MessageSecurityMode.SignAndEncrypt))
                );

                // HTTPS Basic256Sha256 / Sign (SignAndEncrypt not allowed for HTTPS)
                endpointConfigurations.add(buildHttpsEndpoint(
                    builder.copy()
                        .setSecurityPolicy(SecurityPolicy.Basic256Sha256)
                        .setSecurityMode(MessageSecurityMode.Sign))
                );

                /*
                 * It's good practice to provide a discovery-specific endpoint with no security.
                 * It's required practice if all regular endpoints have security configured.
                 *
                 * Usage of the  "/discovery" suffix is defined by OPC UA Part 6:
                 *
                 * Each OPC UA Server Application implements the Discovery Service Set. If the OPC UA Server requires a
                 * different address for this Endpoint it shall create the address by appending the path "/discovery" to
                 * its base address.
                 */

                EndpointConfiguration.Builder discoveryBuilder = builder.copy()
                    .setPath("/milo/discovery")
                    .setSecurityPolicy(SecurityPolicy.None)
                    .setSecurityMode(MessageSecurityMode.None);

                endpointConfigurations.add(buildTcpEndpoint(discoveryBuilder));
                endpointConfigurations.add(buildHttpsEndpoint(discoveryBuilder));
            }
        }

        return endpointConfigurations;
    }

    private EndpointConfiguration buildTcpEndpoint(EndpointConfiguration.Builder base) {    	
        return base.copy()
            .setTransportProfile(TransportProfile.TCP_UASC_UABINARY)
            .setBindPort( Integer.parseInt(config.getProperty("tcpPort")))
            .build();
    }

    private EndpointConfiguration buildHttpsEndpoint(EndpointConfiguration.Builder base) {
        return base.copy()
            .setTransportProfile(TransportProfile.HTTPS_UABINARY)
            .setBindPort(Integer.parseInt(config.getProperty("httpsPort")))
            .build();
    }

    public OpcUaServer getServer() {
        return server;
    }

    public CompletableFuture<OpcUaServer> startup() {

		try {
			componentManager.activate(ComponentContext.getStaticContext());
		} catch (ComponentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return server.startup();
    }

    public CompletableFuture<OpcUaServer> shutdown() {
		try {
			componentManager.deactivate();
		} catch (ComponentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return server.shutdown();
    }

}
