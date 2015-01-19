package com.denovo.p8583.test;

/**
 * Created by moonwa on 15-1-18.
 */
class SignInRequestMessage extends DefaultRequestMessage {
    private String userName;
    private String password;
    private String serialNo;
    private String batchNo;
    private byte[] mac;

    public SignInRequestMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
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

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }
}
