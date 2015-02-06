package com.denovo.p8583server.responseMessages;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/2/5.
 */
public class QueryResponseMessage extends DealResponseMessage {
    public QueryResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }

    public void setAmount(BigDecimal amount) {
        //this.amount = "0210156C"+ StringUtils.leftPad(Double.toString(amount * 100),'0');
      //  Double d = Double.parseDouble(p8583Pack.getString(4));
        //return  Double.parseDouble(new DecimalFormat("0.00").format(d))/100;
        this.amount = "0210156C" + StringUtils.leftPad((amount.multiply(new BigDecimal(100)).toString()).toString(),0);
    }

    public String getAmount() {

        return amount;
    }

    private String amount;

}
