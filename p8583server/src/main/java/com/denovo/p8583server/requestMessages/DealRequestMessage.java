package com.denovo.p8583server.requestMessages;

import com.denovo.p8583.P8583Field;
import com.denovo.p8583.P8583Pack;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/1/24.
 */
public class DealRequestMessage extends DefaultRequestMessage {
    private String serialNo;
    private String batchNo;
    private String mac;
    private String cardNumber;
    private String cardOrPhoneNumberPassWork;
    private String dealType;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;

    public void setOperator(String operator) {
        this.operator = operator;
    }

    private String openCode;

    public String getOperator() {
        return operator;
    }

    private String operator;

    public void setDealamount(double  dealamount) {
        this.dealamount = dealamount;
    }

    public double  getDealamount() {

        return dealamount;
    }

    private  double  dealamount;

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }



    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getDealType() {
        return dealType;
    }

    public DealRequestMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public  String  getCardNumber(){
        return  cardNumber;
    }

    public  void   setCardNumber(String cardNumber){
         this.cardNumber=cardNumber;
    }


    public  String  getCardOrPhoneNumberPassWork(){
        return  cardOrPhoneNumberPassWork;
    }

    public  void   setCardOrPhoneNumberPassWork(String cardOrPhoneNumberPassWork){
        this.cardOrPhoneNumberPassWork=cardOrPhoneNumberPassWork;
    }
}
