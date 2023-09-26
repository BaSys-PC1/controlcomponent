package de.dfki.cos.basys.controlcomponent.camunda;

import de.dfki.cos.basys.controlcomponent.camunda.service.CamundaServiceImpl;
import org.camunda.community.rest.client.dto.HistoricProcessInstanceDto;
import org.camunda.community.rest.client.dto.StartProcessInstanceDto;
import org.camunda.community.rest.client.dto.VariableValueDto;
import org.camunda.community.rest.client.invoker.ApiException;
import org.junit.Test;

import java.util.Properties;

public class CamundaServiceTest {

    private final String connectionString = "http://camunda.dockerhost/engine-rest";

    @Test
    public void testProcessStart() {
        Properties config = new Properties();
        config.put("endpoint", connectionString);

        CamundaServiceImpl service = new CamundaServiceImpl(config);
        service.connect(null, connectionString);

        StartProcessInstanceDto startProcessInstanceDto = new StartProcessInstanceDto();
        startProcessInstanceDto.businessKey("businesskey");
        startProcessInstanceDto.putVariablesItem(
                "aString",
                new VariableValueDto()
                        .type("string"));
        startProcessInstanceDto.putVariablesItem(
                "aNumber",
                new VariableValueDto()
                        .type("long"));
        startProcessInstanceDto.putVariablesItem(
                "aDouble",
                new VariableValueDto()
                        .type("double"));
        startProcessInstanceDto.putVariablesItem(
                "aVar",
                new VariableValueDto()
                        .type("boolean")
                        .value(true));
        try {
            var processInstanceWithVariablesDto = service.getProcessDefinitionApi().startProcessInstanceByKey("TestProcess_Key",startProcessInstanceDto );


            var processInstance = service.getHistoricProcessInstanceApi().getHistoricProcessInstance(processInstanceWithVariablesDto.getId());
            while (processInstance.getState() != HistoricProcessInstanceDto.StateEnum.COMPLETED) {
                Thread.sleep(1000);
                processInstance = service.getHistoricProcessInstanceApi().getHistoricProcessInstance(processInstanceWithVariablesDto.getId());
                System.out.println("ping");
            }

            var processInstanceVars = service.getHistoricVariableInstanceApi().getHistoricVariableInstances(null,null,null,null,null,null,null, processInstanceWithVariablesDto.getId(), null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,true);

            System.out.println("Process instance ended");

        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
