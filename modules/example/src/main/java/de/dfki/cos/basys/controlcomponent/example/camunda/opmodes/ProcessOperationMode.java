package de.dfki.cos.basys.controlcomponent.example.camunda.opmodes;

import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.annotation.OperationMode;
import de.dfki.cos.basys.controlcomponent.annotation.Parameter;
import de.dfki.cos.basys.controlcomponent.camunda.opmodes.CamundaOperationMode;
import de.dfki.cos.basys.controlcomponent.camunda.service.CamundaService;
import de.dfki.cos.basys.controlcomponent.impl.BaseControlComponent;

@OperationMode(name = "Test Process Operation Mode", shortName = "TEST", description = "Starts a BPMN process and tracks its execution")
public class ProcessOperationMode extends CamundaOperationMode {
    public ProcessOperationMode(BaseControlComponent<CamundaService> component) {
        super(component);
    }

    @Parameter(name = "string_var", direction = ParameterDirection.IN)
    private String stringVar = "qwertz";

    @Parameter(name = "time", direction = ParameterDirection.OUT)
    private String time;

    @Parameter(name = "accept", direction = ParameterDirection.OUT)
    private Boolean accept;


}
