package de.dfki.cos.basys.controlcomponent.camunda.opmodes;

import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.controlcomponent.camunda.service.CamundaService;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;
import de.dfki.cos.basys.controlcomponent.impl.BaseOperationMode;
import org.camunda.community.rest.client.dto.HistoricProcessInstanceDto;
import org.camunda.community.rest.client.dto.ProcessInstanceWithVariablesDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.camunda.community.rest.client.invoker.ApiException;

public class CamundaOperationMode extends BaseOperationMode<CamundaService> {

    private String businessKey;
    private final String processKey = component.getId() + "." + getShortName();

    private ProcessInstanceWithVariablesDto processInstanceWithVariablesDto;

    public CamundaOperationMode(BaseControlComponent<CamundaService> component) {
        super(component);
    }

    @Override
    public void onResetting() {
        businessKey = null;
        processInstanceWithVariablesDto = null;
    }

    @Override
    public void onStarting() {
        businessKey =  processKey + "." + System.currentTimeMillis();
        StartProcessInstanceDto startProcessInstanceDto = new StartProcessInstanceDto();
        startProcessInstanceDto.businessKey(businessKey);
        for (var input : this.getInputParameters()) {
            startProcessInstanceDto.putVariablesItem(
                    input.getName(),
                    new VariableValueDto()
                            .type(input.getType())
                            .value(input.getValue())
            );
        }

        try {
            processInstanceWithVariablesDto = getService(CamundaService.class).getProcessDefinitionApi().startProcessInstanceByKey(processKey, startProcessInstanceDto );
        } catch (ApiException e) {
            component.setErrorStatus(3, e.getResponseBody());
            component.stop(component.getOccupierId());
        }
    }

    @Override
    public void onExecute() {
        // check is process instance is running
        // can we get intermediate results for internal work state?

        try {
            boolean executing = true;
            while (executing) {
                HistoricProcessInstanceDto processInstance = getService(CamundaService.class).getHistoricProcessInstanceApi().getHistoricProcessInstance(processInstanceWithVariablesDto.getId());
                switch (processInstance.getState()) {
                    case ACTIVE:
                        break;
                    case EXTERNALLY_TERMINATED:
                        component.setErrorStatus(4, "EXTERNALLY_TERMINATED");
                        executing = false;
                    case INTERNALLY_TERMINATED:
                        component.setErrorStatus(4, "INTERNALLY_TERMINATED");
                        executing = false;
                    case COMPLETED:
                        executing = false;
                        break;
                    case SUSPENDED:
                        break;
                    default:
                        break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

        if (component.getErrorCode() > 0) {
            component.stop(component.getOccupierId());
        }
    }

    @Override
    public void onCompleting() {
        // collect output vars from (finished) process instance.
        try {
            var processInstanceVars = getService(CamundaService.class).getHistoricVariableInstanceApi().getHistoricVariableInstances(null,null,null,null,null,null,null, processInstanceWithVariablesDto.getId(), null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,true);
            for (var param : this.getOutputParameters()) {
                var output = processInstanceVars.stream().filter(p -> p.getName().equals(param.getName())).findFirst();
                if (output.isPresent()) {
                    component.setParameterValue(param.getName(),output.get().getValue());
                } else {
                    LOGGER.warn("Output parameter {} not found in process instance variables", param.getName());
                }
            }
        } catch (ApiException | ComponentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStopping() {

    }
}
