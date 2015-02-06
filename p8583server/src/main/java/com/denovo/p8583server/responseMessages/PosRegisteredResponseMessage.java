package com.denovo.p8583server.responseMessages;

/**
 * Created by Administrator on 2015/1/22.
 */
public class PosRegisteredResponseMessage extends DefaultResponseMessage {
    public PosRegisteredResponseMessage(String messageType, byte[] tpud) {
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
