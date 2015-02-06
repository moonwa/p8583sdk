package com.denovo.p8583server.responseMessagesEncoders;

import com.denovo.p8583.*;
import com.denovo.p8583.fields.AsciiFixLengthP8583Field;
import com.denovo.p8583.fields.AsciiVarLengthP8583Field;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.handlers.handlercommon.Globals;
import com.denovo.p8583server.responseMessages.DealResponseMessage;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2015/1/26.
 */
public class DealResponseMessageEncoder  extends DefaultResponseMessageParser{
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
    }
    @Override
    public void update(ResponseMessage message, P8583Pack pack) throws Exception {
        DealResponseMessage msg = (DealResponseMessage) message;
         int[] nums= msg.getPoss();
        for(int n : nums){
            if(msg.getP8583Fields().get(n-1).getHasValue()){
                pack.setString(n,msg.getP8583Fields().get(n-1).getString());
            }
        }

        byte[] result=new byte[0];
        if(msg.getResult()!=0) {
            pack.setString(47, msg.getResultMsg());
        }else {
            pack.setString(12, new SimpleDateFormat("HHmmss").format(new Date()));
            pack.setString(13, new SimpleDateFormat("MMdd").format(new Date()));
            pack.setString(32, "00");
            pack.setString(44, "0001");
            pack.setString(47, msg.getSerialNo());
            pack.setString(62,SetAccount(GetTheCode(msg.getOpenCode()),0,0,0));
            pack.setMessageType("0210");
            super.update(message, pack);
            ResponseMessageEncoders d = new ResponseMessageEncoders();
            String text = pack.display();
            pack.setString(64, Ledes.MACEncrypt(d.pack(pack), Globals.GetKeyEntry(msg.getClientId().trim(), msg.getTerminalId().trim()).getKey2(), 0));

        }

        }
    private static String SetAccount(String theCode, double amount, double zero, double remainPoint)
    {
      return  DecimalToIntLeft(mul(amount,100), 10) + DecimalToIntLeft(new BigDecimal(zero), 12) + DecimalToIntLeft(mul(remainPoint,100), 10) + theCode;
    }
    private static String DecimalToIntLeft(BigDecimal v, int width)
    {
        return StringUtils.leftPad(v.toString().replace(".", ""), width, '0');
    }
    public static BigDecimal mul(double d1, double d2)
    {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return new BigDecimal(b1.multiply(b2).doubleValue());
    }

    private String GetTheCode(String opCode)
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
        else
        {
            return "";
        }
    }

}
