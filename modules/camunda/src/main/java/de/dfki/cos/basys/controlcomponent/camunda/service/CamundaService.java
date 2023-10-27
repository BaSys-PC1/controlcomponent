package de.dfki.cos.basys.controlcomponent.camunda.service;

import org.camunda.community.rest.client.api.*;

public interface CamundaService {
    MessageApi getMessageApi();
    ProcessInstanceApi getProcessInstanceApi();
    ProcessDefinitionApi getProcessDefinitionApi();
    HistoricProcessInstanceApi getHistoricProcessInstanceApi();
    HistoricVariableInstanceApi getHistoricVariableInstanceApi();

}
