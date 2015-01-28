package com.denovo.p8583server.requestMessages;

/**
 * Created by Administrator on 2015/1/21.
 */
public class PosRegisteredRequestMessage extends DefaultRequestMessage {
    public PosRegisteredRequestMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }


    private String serialNo;
    private String batchNo;
    private String mac;
    private String posAddress;




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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPosAddress() {
        return posAddress;
    }

    public void setPosAddress(String posAddress) {
        this.posAddress = posAddress;
    }
}
