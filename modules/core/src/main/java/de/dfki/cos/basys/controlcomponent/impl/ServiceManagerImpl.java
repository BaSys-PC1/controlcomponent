package de.dfki.cos.basys.controlcomponent.impl;

import com.google.common.base.Strings;
import de.dfki.cos.basys.common.component.*;
import de.dfki.cos.basys.controlcomponent.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Properties;

public class ServiceManagerImpl<T> implements ServiceManager<T> {
	public final Logger LOGGER;
	protected ServiceConfig config;
	private ComponentContext context = null;

	//private ScheduledFuture<?> connectionHandle = null;
	protected ServiceProvider<T> serviceProvider = null;
	
	public ServiceManagerImpl(ServiceConfig config) {
		this.config = config;
		this.LOGGER = LoggerFactory.getLogger(getName());

		Class c = null;
					
		try {
			c = Class.forName(config.getImplementationJavaClass());
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}		
	
		Constructor<de.dfki.cos.basys.common.component.ServiceProvider<T>> constructor = null;
		
		try {
			try {
				constructor = c.getConstructor(Properties.class);
				serviceProvider = constructor.newInstance(config);
			} catch (NoSuchMethodException e) {
				try {
					constructor = c.getConstructor();
					serviceProvider = constructor.newInstance();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ServiceManagerImpl(ServiceConfig config, de.dfki.cos.basys.common.component.ServiceProvider<T> serviceProvider) {
		this.config = config;
		this.LOGGER = LoggerFactory.getLogger(getName());

		this.serviceProvider = serviceProvider;
	}
	
//	public ServiceManagerImpl(Properties config, Supplier<? extends ServiceConnection> ctor) {
//		this.config = config;
//		this.LOGGER = LoggerFactory.getLogger("basys.component." + getName().replaceAll(" ", "-"));		
//
//		if (config.getProperty("observeConnection") != null) {
//			observeConnection = Boolean.parseBoolean(config.getProperty("observeConnection"));
//			LOGGER.info("observeConnection = " + observeConnection);
//		}	
//		
//		this.service = Objects.requireNonNull(ctor).get();
//	}
	
	private String getName() {		
		return config.getImplementationJavaClass().substring(config.getImplementationJavaClass().lastIndexOf(".")+1);
	}

	@Override
	public void connect(ComponentContext context) throws ComponentException {
		this.context = context;
		if (serviceProvider != null && !isConnected()) {
			LOGGER.debug("connect");
			if (Strings.isNullOrEmpty(config.getConnectionString())) {
				LOGGER.debug("connect - skipped due to missing connection string");
			} else {
				LOGGER.debug("provided connection string: " + config.getConnectionString());
				if (serviceProvider.connect(context, config.getConnectionString())) {
					LOGGER.debug("connect - finished");
					//setConnected(true);
				} else {
					LOGGER.warn("connect - not successful");
				}
			}
		} else {
			LOGGER.info("already connected");
		}
	}

	@Override
	public void disconnect() throws ComponentException {
		if (serviceProvider != null && isConnected()) {
			LOGGER.debug("disconnect");
			serviceProvider.disconnect();
			if (!serviceProvider.isConnected()) {
				LOGGER.debug("disconnect - finished");
				//setConnected(false);
			} else {
				LOGGER.warn("disconnect - not successful");
			}
		} else {
			LOGGER.info("already disconnected");
		}
	}
	
	@Override
	public boolean isConnected() {
		return (this.serviceProvider == null) ? false : serviceProvider.isConnected();
	}

	@Override
	public ServiceProvider<T> getServiceProvider() {
		return serviceProvider;
	}

	@Override
	public T getService() {
		return serviceProvider.getService();
	}
	
	@Override
	public T getServiceMockup() {
		return null;
	}


	
//	protected boolean doConnect( ) {
//		return true;
//	}
//	
//	protected boolean doDisconnect( ) {
//		return true;
//	}


//	protected void setConnected(boolean value) {
//		connected = value;
//		if (observeConnection) {
//			if (connected) {
//				observeConnection();
//			} else {
//				unobserveConnection();
//			}
//		}
//		notifyChange();
//	}
	

	
//	protected void notifyChange() {		
//	}
	

//	private void observeConnection() {
//		LOGGER.info("observeConnection()");
//		connectionHandle = context.getScheduledExecutorService().scheduleWithFixedDelay(new Runnable() {
//
//			@Override
//			public void run() {
//
//				if (!isConnected()) {
//					String cs = config.getProperty(StringConstants.connectionString);
//					LOGGER.info("connectToExternal: " + cs);
//					try {
//						if (canConnect()) {
//							connect(context);
//							// connectedToExternal = true;
//						} else {
//							LOGGER.warn("component cannot connectToExternal(), retry ...");
//						}
//						LOGGER.debug("connectToExternal - finished");
//					} catch (ComponentException e) {
//						LOGGER.error(e.getMessage());
//						LOGGER.warn("component could not connectToExternal(), retry ...");
//						e.printStackTrace();
//					}
//				}
//
//			}
//
//		}, 5000, 5000, TimeUnit.MILLISECONDS);
//
//	}
//
//	protected void unobserveConnection() {
//		LOGGER.info("unobserveConnection()");
//		if (connectionHandle != null) {
//			connectionHandle.cancel(true);
//		}
//	}

//	@Override
//	public void handleConnectionEstablished() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void handleConnectionLost() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void handleConnectionClosed() {
//		// TODO Auto-generated method stub
//		
//	}


}
