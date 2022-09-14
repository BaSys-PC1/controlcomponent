package de.dfki.cos.basys.controlcomponent.spring.configuration;

import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Properties;

@Configuration
public class ControlComponentServerConfig {

    @Value("${basys.opcuaServer.certsFolder:./certs}")
    private String certsFolder;

    @Value("${basys.opcuaServer.tcpPort:12685}")
    private int tcpPort;

    @Value("${basys.opcuaServer.httpsPort:8443}")
    private int httpsPort;

    @Autowired
    private ControlComponent controlComponent;

    @Bean(destroyMethod = "shutdown")
    public ControlComponentServer getServer() throws Exception {
        Properties config = new Properties(ControlComponentServer.getDefaultConfig());
        config.setProperty("certsFolder", new File(certsFolder).toString());
        config.setProperty("tcpPort", tcpPort+"");
        config.setProperty("httpsPort", httpsPort+"");
        var server = new ControlComponentServer(config);
        server.addControlComponent(controlComponent);
        server.startup();//.get();
        return server;
    }

}
