package de.dfki.cos.basys.controlcomponent.server.aas;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Tomcat;
import org.eclipse.basyx.submodel.metamodel.api.ISubModel;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.restapi.SubModelProvider;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;
import org.eclipse.basyx.vab.protocol.http.server.AASHTTPServer;
import org.eclipse.basyx.vab.protocol.http.server.BaSyxContext;
import org.eclipse.basyx.vab.protocol.http.server.VABHTTPInterface;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ControlComponent;

public class SubmodelHost extends AbstractLifecycle {

	private static Logger logger = LoggerFactory.getLogger(SubmodelHost.class);
	
	private Tomcat tomcat;	
	  
	private Map<String, HttpServlet> servletMapping = new HashMap<>();
	private Context rootCtx = null;
	/**
	 * Constructor
	 * 
	 * Create new Tomcat instance and add the provided servlet mappings
	 * 
	 * @param context
	 *            Basyx context with of url mappings to HTTPServlet
	 */
	public SubmodelHost(Properties config) {
		// Instantiate and setup Tomcat server
		tomcat = new Tomcat();
		tomcat.setPort(Integer.parseInt(config.getProperty("port",getDefaultConfig().getProperty("port"))));
		tomcat.setHostname(config.getProperty("hostname",getDefaultConfig().getProperty("hostname")));
		tomcat.getHost().setAppBase(".");

		// Create servlet context
		rootCtx = tomcat.addContext("", new File("").getAbsolutePath());
	}

	
	@Override
	protected void onStartup() {

		logger.trace("Starting SubmodelHost.....");
    	
		Thread serverThread = new Thread(() -> {
			try {
				tomcat.stop();

				// Adds listener that notifies the tomcat object when the server has started
				tomcat.getServer().addLifecycleListener(new LifecycleListener() {
					@Override
					public void lifecycleEvent(LifecycleEvent event) {
						if (event.getLifecycle().getState() == LifecycleState.STARTED) {
							synchronized (tomcat) {
								tomcat.notifyAll();
							}
						}
					}
				});

				tomcat.start();
				
				// Keeps the server thread alive until the server is shut down
				tomcat.getServer().await();
			} catch (LifecycleException e) {
				logger.error("Exception in start", e);
			}
		});
		serverThread.start();

    	ComponentContext.getStaticContext().getEventBus().register(this);	
		
		synchronized (tomcat) {
			try {
				tomcat.wait();
			} catch (InterruptedException e) {
				logger.error("Exception in start", e);
			}
		}
		
	}

	@Override
	protected void onShutdown() {
		logger.trace("Shutting down SubmodelHost ...");

		ComponentContext.getStaticContext().getEventBus().unregister(this);	
		
		try {
			tomcat.stop();
			tomcat.destroy();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in shutdown", e);
		}
		
	}
	
	@Subscribe
	public void onComponentManagerEvent(ComponentManagerEvent ev) {		
		if (ev.getType() == Type.COMPONENT_ADDED) {
			Component component = ev.getComponent();
			if (component instanceof ControlComponent) {
				SubModel submodel = ControlComponentSubmodelFactory.createSubmodel((ControlComponent) component);
				SubModelProvider subProvider =  new SubModelProvider(submodel);
				HttpServlet servlet = new VABHTTPInterface<IModelProvider>(subProvider);
				//servletMapping.put(component.getId(), servlet);
				// Add new Servlet and Mapping to tomcat environment
				Tomcat.addServlet(rootCtx, String.valueOf(servlet.hashCode()), servlet);
				rootCtx.addServletMappingDecoded("/" + component.getId() + "/*", String.valueOf(servlet.hashCode()));
				//TODO register submodel at AAS registry, for doing so, an AAS has to be there already
				
			}
		}
		else if (ev.getType() == Type.COMPONENT_DELETED) {			
			String id = ev.getValue();
			rootCtx.removeServletMapping("/" + id + "/*");		
			//TODO unregister submodel at AAS registry
			
		}
	}


	public static Properties getDefaultConfig() {
    	Properties defaultConfig = new Properties();
        defaultConfig.setProperty("port", "5080");
        defaultConfig.setProperty("hostname", "localhost");
    	return defaultConfig;
	}


}
