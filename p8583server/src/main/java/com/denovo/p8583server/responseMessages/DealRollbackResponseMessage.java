package com.denovo.p8583server.responseMessages;

import com.denovo.p8583.P8583Field;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackResponseMessage extends DefaultResponseMessage {

    public DealRollbackResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
    private int[] poss;
    private ArrayList<P8583Field> p8583Fields;

    public void setPoss(int[] poss) {
        this.poss = poss;
    }

    public void setP8583Fields(ArrayList<P8583Field> p8583Fields) {
        this.p8583Fields = p8583Fields;
    }

    public int[] getPoss() {

        return poss;
    }

    public ArrayList<P8583Field> getP8583Fields() {
        return p8583Fields;
    }
    public void GetResponse(int[] poss,ArrayList<P8583Field> p8583Fields) throws Exception{

        this.setPoss(poss);
        this.setP8583Fields(p8583Fields);

    }
    private String batchNo;


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}
