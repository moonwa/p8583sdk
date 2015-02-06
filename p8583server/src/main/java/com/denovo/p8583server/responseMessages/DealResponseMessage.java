package com.denovo.p8583server.responseMessages;

import com.denovo.p8583.Ledes;
import com.denovo.p8583.P8583Field;
import com.denovo.p8583.ResponseMessage;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/1/26.
 */
public class DealResponseMessage extends DefaultResponseMessage {

    public DealResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }

    private int[] poss;

   private ArrayList<P8583Field> p8583Fields;

    public int[] getPoss() {
        return poss;
    }

    public ArrayList<P8583Field> getP8583Fields() {
        return p8583Fields;
    }
    private String serialNo;
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    public void setPoss(int[] poss) {
        this.poss = poss;
    }

    public void setP8583Fields(ArrayList<P8583Field> p8583Fields) {
        this.p8583Fields = p8583Fields;
    }

    public void GetResponse(int[] poss,ArrayList<P8583Field> p8583Fields) throws Exception{
       this.setPoss(poss);
        this.setP8583Fields(p8583Fields);
    }
private String openCode;
    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public   String GetPassword(String cardNumber,String passWork,String key1){
        int len = cardNumber.length();
        return null;
    }

    public   String GetPassword1(String cardNumber,String passWork,String key1)throws Exception {
        String value1 = "000000000000";
        if (passWork.equals(""))
            return "";
        byte[] d = Ledes.DesDecryptB(passWork, key1);
        return Ledes.GetPasswordFromDecrypt(d);
    }


}
