package de.dfki.cos.basys.controlcomponent.server.opcua.types;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.FolderType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ControlComponentStatusType extends BaseDataVariableType {

//    QualifiedProperty<Integer> ERRCODE = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "ERRCODE",
//            NodeId.parse("ns=0;i=27"),
//            ValueRanks.Scalar,
//            Integer.class
//        );
//        
//    QualifiedProperty<String> ERRMSG = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "ERRMSG",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
//    QualifiedProperty<String> EXMODE = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "EXMODE",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
//    QualifiedProperty<String> EXST = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "EXST",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
//    QualifiedProperty<String> OCCST = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "OCCST",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
//    QualifiedProperty<String> OCCUPIER = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "OCCUPIER",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
//    QualifiedProperty<String> OPMODE = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "OPMODE",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
//    QualifiedProperty<String> WORKST = new QualifiedProperty<>(
//            ControlComponentNamespace.NAMESPACE_URI,
//            "WORKST",
//            NodeId.parse("ns=0;i=12"),
//            ValueRanks.Scalar,
//            String.class
//        );
//        
	Integer getErrorCode();

	BaseDataVariableNode getErrorCodeNode();

	String getErrorMessage();

	BaseDataVariableNode getErrorMessageNode();

	String getExecutionMode();

	BaseDataVariableNode getExecutionModeNode();

	String getExecutionState();

	BaseDataVariableNode getExecutionStateNode();

	String getOccupationState();

	BaseDataVariableNode getOccupationStateNode();

	String getOccupierId();

	BaseDataVariableNode getOccupierIdNode();

	String getOperationMode();

	BaseDataVariableNode getOperationModeNode();

	String getWorkState();

	BaseDataVariableNode getWorkStateNode();
}
