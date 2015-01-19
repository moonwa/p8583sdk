package com.denovo.p8583.test;
class SignInResponseMessage extends DefaultResponseMessage {
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
