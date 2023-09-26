package de.dfki.cos.basys.controlcomponent.camunda.service;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ServiceProvider;
import org.camunda.community.rest.client.api.*;
import org.camunda.community.rest.client.invoker.ApiClient;

import java.util.Properties;

public class CamundaServiceImpl implements CamundaService, ServiceProvider<CamundaService> {

    private final Properties config;

    private ApiClient apiClient;
    private MessageApi messageApi;
    private ProcessInstanceApi processInstanceApi;
    private ProcessDefinitionApi processDefinitionApi;
    private HistoricProcessInstanceApi historicProcessInstanceApi;
    private HistoricVariableInstanceApi historicVariableInstanceApi;

    public CamundaServiceImpl(Properties config) {
        this.config = config;
    }

    @Override
    public boolean connect(ComponentContext context, String connectionString) {
        apiClient = new ApiClient();
        apiClient.setBasePath(connectionString);
        messageApi = new MessageApi(apiClient);
        processInstanceApi = new ProcessInstanceApi(apiClient);
        processDefinitionApi = new ProcessDefinitionApi(apiClient);
        historicProcessInstanceApi = new HistoricProcessInstanceApi(apiClient);
        historicVariableInstanceApi = new HistoricVariableInstanceApi(apiClient);
        return isConnected();
    }

    @Override
    public void disconnect() {
        apiClient = null;
    }

    @Override
    public boolean isConnected() {
        return apiClient != null;
    }

    @Override
    public CamundaService getService() {
        return this;
    }

    @Override
    public MessageApi getMessageApi() {
        return messageApi;
    }

    @Override
    public ProcessInstanceApi getProcessInstanceApi() {
        return processInstanceApi;
    }

    @Override
    public ProcessDefinitionApi getProcessDefinitionApi() {
        return processDefinitionApi;
    }

    @Override
    public HistoricProcessInstanceApi getHistoricProcessInstanceApi() {
        return historicProcessInstanceApi;
    }

    @Override
    public HistoricVariableInstanceApi getHistoricVariableInstanceApi() {
        return historicVariableInstanceApi;
    }
}
