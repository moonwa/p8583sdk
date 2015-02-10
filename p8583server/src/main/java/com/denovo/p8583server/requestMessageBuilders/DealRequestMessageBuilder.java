package com.denovo.p8583server.requestMessageBuilders;

import com.denovo.p8583.Encoder;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583server.requestMessages.DealRequestMessage;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/1/24.
 */
public class DealRequestMessageBuilder extends DefaultRequestMessagePackBuilder {
    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        DealRequestMessage request = new DealRequestMessage(p8583Pack.getMessageType(), p8583Pack.getTpud());
        super.update(p8583Pack, request);
        request.setSerialNo(getUpdateSerialNo(p8583Pack));
        request.setBatchNo(getBatchNo(p8583Pack));
        request.setDealType(getDealType(p8583Pack));
        request.setCardNumber(getCardNumber(p8583Pack));
        request.setCardOrPhoneNumberPassWork(getCardNumberPassWork(p8583Pack));
        request.setOpenCode(getOpanCode(p8583Pack));
        request.setMac(getMac(p8583Pack));
        request.setDealamount(getDealamount(p8583Pack));
        request.setPhoneNumber(getPhoneNumber(p8583Pack));
        request.setOperator(getOperator(p8583Pack));
        request.setP8583Pack(p8583Pack.p8583Fields);
        request.setData(p8583Pack.data);
        return request;
    }
    private String getUpdateSerialNo(P8583Pack p8583Pack)throws Exception {
        if (p8583Pack.getHasValue(59)) {
            return p8583Pack.getString(59);
        }
        return  null;

    }

private  String getPhoneNumber(P8583Pack p8583Pack)throws Exception {
    if (p8583Pack.getHasValue(5)) {
        return p8583Pack.getString(5);
    }
    return  null;
}
    private Double getDealamount(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(4)){
            Double d = Double.parseDouble(p8583Pack.getString(4));
           return  Double.parseDouble(new DecimalFormat("0.00").format(d))/100;
        }
        return 0.00;

    }

    private String getMac(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(47)){
            return p8583Pack.getString(47);
        }
        return null;

    }

    private String getOpanCode(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(62)){
            return p8583Pack.getString(62);
        }
        return null;
    }


    private String getOperator(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(63)){
            return p8583Pack.getString(63).trim();
        }
        return null;
    }

    private String getCardNumber(P8583Pack p8583Pack) throws Exception {

        if (p8583Pack.getHasValue(35))
        {

            String text = p8583Pack.getString(35);
            if(text.length()<=0){
                if(p8583Pack.getHasValue(2)){
                    return p8583Pack.getString(2);
                }
            }else {
                return text;
            }

        }else {
                if(p8583Pack.getHasValue(2)){
                    return p8583Pack.getString(2);
                }
        }
        return null;
    }

    private String getCardNumberPassWork(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(26))
        {
            String text = p8583Pack.getString(26);
            if (text == "" || text == "00")
                return "";
            if(p8583Pack.getHasValue(52))
                return p8583Pack.getString(52);
        }
        return null;


        }

    private String getDealType(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(3))
        {
            String text = p8583Pack.getString(3);
            return  text;

        }
        return null;
    }
}
