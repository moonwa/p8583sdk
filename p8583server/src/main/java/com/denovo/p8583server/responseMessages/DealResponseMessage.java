package com.denovo.p8583server.responseMessages;

import com.denovo.p8583.Ledes;
import com.denovo.p8583.P8583Field;
import com.denovo.p8583.ResponseMessage;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/1/26.
 */
public class DealResponseMessage extends DefaultResponseMessage{


    private int[] poss;
    private ArrayList<P8583Field> p8583Fields;
    private  String mac;
    private String data;
    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String  getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }

    public Boolean getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(Boolean isQuery) {
        this.isQuery = isQuery;
    }

    private Boolean isQuery;

    private BigDecimal amount;
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    private double point;
    public double getPoint() {
        return point;
    }
    public void setPoint(double point) {
        this.point = point;
    }
    public DealResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
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

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    private String codeText;
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

    public   String GetPassword1(String passWork,String key1)throws Exception {
        if (passWork==null||passWork.equals(""))
            return "";
        byte[] d = Ledes.DesDecryptB(passWork, key1);
        return Ledes.GetPasswordFromDecrypt(d);
    }
    public  String SetAccount(String theCode, double amount, double zero, double remainPoint)
    {
        return  DecimalToIntLeft(mul(amount,100), 10) + DecimalToIntLeft(new BigDecimal(zero), 12) + DecimalToIntLeft(mul(remainPoint,100), 10) + theCode;
    }
    public  String DecimalToIntLeft(BigDecimal v, int width)
    {
        v.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return StringUtils.leftPad(v.toString().replace(".", ""), width, '0');
    }
    public  BigDecimal mul(double d1, double d2)
    {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(String.valueOf(d1));

        BigDecimal b2 = new BigDecimal(d2);
        return new BigDecimal(b1.multiply(b2).doubleValue());
    }
    public  BigDecimal add(double d1, double d2)
    {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2);
    }


    public String GetTheCode(String opCode)
    {
        if (opCode.equals("000000000000000000000000000000ONLYSALE"))
        {
            return "ONLYSALE";
        }
        else if (opCode.equals("000000000000000000000000000000INTEGRAL"))
        {
            return "INTEGRAL";
        }
        else if (opCode.equals("000000000000000000000000000000INTEGRAL_SALE"))
        {
            return "INTEGRAL_SALE";
        }
        else if (opCode.equals("000000000000000000000000000000RECHARGE"))
        {
            return "RECHARGE";
        }
        else if (opCode.equals("000000000000000000000000000000DETAIL"))
        {
            return "DETAIL";
        }
        else if (opCode.equals("000000000000000000000000000000REFUND"))
        {
            return "REFUND";
        }
        else if(opCode.equals("000000000000000000000000000000QUERY"))
        {
            return "QUERY";
        }
        else {
            return "";
        }
    }
}
