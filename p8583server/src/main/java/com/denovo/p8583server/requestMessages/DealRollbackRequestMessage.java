package com.denovo.p8583server.requestMessages;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackRequestMessage extends DefaultRequestMessage {
    public DealRollbackRequestMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }

    private String serialNo;
    private String mac;
    private String operator;
    public String getSerialNo() {
        return serialNo;
    }

    public String getMac() {
        return mac;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
