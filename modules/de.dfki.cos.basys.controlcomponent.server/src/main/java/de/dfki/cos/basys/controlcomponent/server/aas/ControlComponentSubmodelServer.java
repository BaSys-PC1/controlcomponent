package de.dfki.cos.basys.controlcomponent.server.aas;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServlet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Tomcat;
import org.eclipse.basyx.aas.manager.ConnectedAssetAdministrationShellManager;
import org.eclipse.basyx.aas.metamodel.map.descriptor.SubmodelDescriptor;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.restapi.SubmodelProvider;
import org.eclipse.basyx.vab.exception.provider.ProviderException;
import org.eclipse.basyx.vab.exception.provider.ResourceNotFoundException;
import org.eclipse.basyx.vab.modelprovider.api.IModelProvider;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.aas.component.SubmodelComponent;
import de.dfki.cos.basys.aas.event.impl.EventMessage;
import de.dfki.cos.basys.aas.services.ServletContainerComponent;
import de.dfki.cos.basys.aas.services.VABHTTPCorsInterface;
import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

public class ControlComponentSubmodelServer extends ServletContainerComponent {

	private Gson gson = new Gson();

	public ControlComponentSubmodelServer(Properties config) {
		super(config);
		
	}

	@Subscribe
	public void onComponentManagerEvent1(ComponentManagerEvent ev) {		
		if (ev.getType() == Type.COMPONENT_ADDED) {
			Component component = ev.getComponent();
			if (component instanceof BaseControlComponent) {
				BaseControlComponent cc = (BaseControlComponent)component;
				
				String aasId = ControlComponentSubmodelFactory.getAasId(cc);
				
				Submodel interfaceSubmodel = ControlComponentSubmodelFactory.createInterfaceSubmodel(cc);
				hostSubmodel(aasId, interfaceSubmodel);
				
				Submodel instanceSubmodel = ControlComponentSubmodelFactory.createInstanceSubmodel(cc);
				hostSubmodel(aasId, instanceSubmodel);
				
				/*
				//alternatively: host instance submodel remotely				
				ConnectedAssetAdministrationShellManager aasManager = new ConnectedAssetAdministrationShellManager(AasComponentContext.getStaticContext().getAasRegistry());				
				try {
					//IdentifierType not really important, will be deleted in DotAAS Part 1, rev 3
					aasManager.deleteSubmodel(new Identifier(IdentifierType.CUSTOM, getAasId()), new Identifier(IdentifierType.CUSTOM, getInstanceSubmodelId()));
				}
				catch (ResourceNotFoundException e) {
					// nothing to delete
					//e.printStackTrace();
				}				
				//IdentifierType not really important, will be deleted in DotAAS Part 1, rev 3
				aasManager.createSubmodel(new Identifier(IdentifierType.CUSTOM, getAasId()), instanceSubmodel);		
				*/
			}
		}
		else if (ev.getType() == Type.COMPONENT_DELETED) {		
			Component component = ev.getComponent();
			if (component instanceof BaseControlComponent) {
				BaseControlComponent cc = (BaseControlComponent)component;
				
				String aasId = ControlComponentSubmodelFactory.getAasId(cc);
				
				Submodel interfaceSubmodel = ControlComponentSubmodelFactory.createInterfaceSubmodel(cc);
				deleteSubmodel(aasId, interfaceSubmodel);
				
				Submodel instanceSubmodel = ControlComponentSubmodelFactory.createInstanceSubmodel(cc);
				deleteSubmodel(aasId, instanceSubmodel);
				
				/*
				//alternatively: delete instance submodel remotely				
				ConnectedAssetAdministrationShellManager aasManager = new ConnectedAssetAdministrationShellManager(AasComponentContext.getStaticContext().getAasRegistry());
				aasManager.deleteSubmodel(new Identifier(IdentifierType.CUSTOM, aasId), instanceSubmodel.getIdentification());
				*/
			}
		}
	}
	
	@Subscribe
	public void onComponentInfo(ControlComponentInfo info) {						
		
		EventMessage message = ControlComponentSubmodelFactory.createEventMessage(info);
		
		String payload = gson.toJson(info);		
		message.setPayload(payload);
		
		context.getEventBus().post(message);
	}

}
