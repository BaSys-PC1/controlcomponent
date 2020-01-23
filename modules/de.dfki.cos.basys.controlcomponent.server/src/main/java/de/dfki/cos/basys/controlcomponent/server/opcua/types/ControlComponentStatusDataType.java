package de.dfki.cos.basys.controlcomponent.server.opcua.types;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import com.google.common.base.MoreObjects;

import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentNamespace;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;

public class ControlComponentStatusDataType implements UaStructure {

    protected final Integer errorCode;
    protected final String errorMessage;
    protected final String executionMode;
    protected final String executionState;
    protected final String occupationState;
    protected final String occupierId;
    protected final String operationMode;
    protected final String workState;

	public ControlComponentStatusDataType() {
		super();
		this.errorCode = 0;
		this.errorMessage = null;
		this.executionMode = null;
		this.executionState = null;
		this.occupationState = null;
		this.occupierId = null;
		this.operationMode = null;
		this.workState = null;
	}
    
	public ControlComponentStatusDataType(Integer errorCode, String errorMessage, String executionMode,
			String executionState, String occupationState, String occupierId, String operationMode, String workState) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.executionMode = executionMode;
		this.executionState = executionState;
		this.occupationState = occupationState;
		this.occupierId = occupierId;
		this.operationMode = operationMode;
		this.workState = workState;
	}

	public ControlComponentStatusDataType(ControlComponent component) {
		super();
		this.errorCode = component.getErrorCode();
		this.errorMessage = component.getErrorMessage();
		this.executionMode = component.getExecutionMode().toString();
		this.executionState = component.getExecutionState().toString();
		this.occupationState = component.getOccupationState().toString();
		this.occupierId = component.getOccupierId();
		this.operationMode = component.getOperationMode().getName();
		this.workState = component.getWorkState();
	}
	
    @Override
    public NodeId getTypeId() { return NodeIds.StatusDataType; }

    @Override
    public NodeId getBinaryEncodingId() { return NodeIds.StatusDataType_Encoding_DefaultBinary; }

    @Override
    public NodeId getXmlEncodingId() { return NodeIds.StatusDataType_Encoding_DefaultXml; }
    
	
	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getOccupationState() {
		return occupationState;
	}

	public String getOccupierId() {
		return occupierId;
	}

	public String getExecutionMode() {
		return executionMode;
	}

	public String getExecutionState() {
		return executionState;
	}

	public String getOperationMode() {
		return operationMode;
	}

	public String getWorkState() {
		return workState;
	}
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ERRCODE", errorCode)
            .add("ERRMSG", errorMessage)
            .add("EXMODE", executionMode)
            .add("EXSTATE", executionState)
            .add("OCCST", occupationState)
            .add("OCCUPIER", occupierId)
            .add("OPMODE", operationMode)
            .add("WORKST", workState)
            .toString();
    }
       
    
    public static class Codec extends BuiltinDataTypeCodec<ControlComponentStatusDataType> {

        @Override
        public Class<ControlComponentStatusDataType> getType() {
            return ControlComponentStatusDataType.class;
        }

        @Override
        public ControlComponentStatusDataType decode(UaDecoder decoder) throws UaSerializationException {
			Integer errorCode = decoder.readInt32("ERRCODE");
			String errorMessage = decoder.readString("ERRMSG");
			String executionMode = decoder.readString("EXMODE");
			String executionState = decoder.readString("EXST");
			String occupationState = decoder.readString("OCCST");
			String occupierId = decoder.readString("OCCUPIER");
			String operationMode = decoder.readString("OPMODE");
			String workState = decoder.readString("WORKST");
        	        	
            return new ControlComponentStatusDataType(errorCode, errorMessage, executionMode, executionState, occupationState, occupierId, operationMode, workState);
        }

        @Override
        public void encode(ControlComponentStatusDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeInt32("ERRCODE", value.errorCode);
            encoder.writeString("ERRMSG", value.errorMessage);
            encoder.writeString("EXMODE", value.executionMode);
            encoder.writeString("EXST", value.executionState);
            encoder.writeString("OCCST", value.occupationState);
            encoder.writeString("OCCUPIER", value.occupierId);
            encoder.writeString("OPMODE", value.operationMode);
            encoder.writeString("WORKST", value.workState);
        }

    }
}
