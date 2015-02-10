package com.denovo.p8583server.responseMessages;

/**
 * Created by Administrator on 2015/2/9.
 */
public class SignInOutResponseMessage extends DefaultResponseMessage {

    public SignInOutResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
    private String batchNo;
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}
