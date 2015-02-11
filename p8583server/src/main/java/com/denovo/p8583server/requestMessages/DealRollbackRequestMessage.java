package com.denovo.p8583server.requestMessages;

import com.denovo.p8583.P8583Field;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackRequestMessage extends DefaultRequestMessage {
    public DealRollbackRequestMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
    private String batchNo;
    private String serialNo;
    private String mac;
    private String operator;
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private byte[] data;
    public ArrayList<P8583Field> getP8583Pack() {
        return p8583Pack;
    }

    public void setP8583Pack(ArrayList<P8583Field> p8583Pack) {
        this.p8583Pack = p8583Pack;
    }

    private ArrayList<P8583Field> p8583Pack;
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


    public void setDealamount(double  dealamount) {
        this.dealamount = dealamount;
    }

    public double  getDealamount() {

        return dealamount;
    }

    private  double  dealamount;
}
