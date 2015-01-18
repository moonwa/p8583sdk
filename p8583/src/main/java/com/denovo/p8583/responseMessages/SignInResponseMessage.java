package com.denovo.p8583.responseMessages;

import com.denovo.p8583.DefaultRequestMessage;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583.requestMessages.SignInRequestMessage;

public class SignInResponseMessage extends DefaultResponseMessage {
    public SignInResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
    private String batchNo;
    private String transportationKey;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTransportationKey() {
        return transportationKey;
    }

    public void setTransportationKey(String transportationKey) {
        this.transportationKey = transportationKey;
    }

}
