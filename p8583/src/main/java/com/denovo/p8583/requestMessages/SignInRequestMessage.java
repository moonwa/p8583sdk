package com.denovo.p8583.requestMessages;

import com.denovo.p8583.DefaultRequestMessage;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;

/**
 * Created by moonwa on 15-1-18.
 */
public class SignInRequestMessage extends DefaultRequestMessage {
    private String userName;
    private String password;
    private String serialNo;
    private String batchNo;

    public SignInRequestMessage(String messageType, byte[] tpud, byte[] mac) {
        super(messageType, tpud, mac);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}
