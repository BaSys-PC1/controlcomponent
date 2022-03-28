package de.dfki.cos.basys.controlcomponent.server.aas;

import com.google.common.eventbus.Subscribe;
import com.google.gson.Gson;
import de.dfki.cos.basys.aas.event.impl.EventMessage;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentInfo;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;

public class EventTranslator {

    private ComponentContext context;

    public EventTranslator(ComponentContext context) {
        this.context = context;
    }

    @Subscribe
    public void onComponentInfo(ComponentInfo info) {

        Gson gsonObj = new Gson();
        String payload =  gsonObj.toJson(info);

        EventMessage message = EventMessage.builder()
                .withObservableReference(new Reference(new Identifier(IdentifierType.CUSTOM,ControlComponentSubmodelFactory.getInterfaceSubmodelId(info)), KeyElements.SUBMODEL, true))
                .withTimestamp(info.getTimestamp())
                .withTopic(ControlComponentSubmodelFactory.getInterfaceSubmodelId(info) + "/update")
                .withPayload(payload)
                .build();

        context.getEventBus().post(message);

    }
}
