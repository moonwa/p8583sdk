package com.denovo.p8583server.requestMessageBuilders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583server.requestMessages.DealRollbackRequestMessage;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackRequestMessageBuilder extends DefaultRequestMessagePackBuilder {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);

    }

    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        DealRollbackRequestMessage request = new DealRollbackRequestMessage(p8583Pack.getMessageType(), p8583Pack.getTpud());
        super.update(p8583Pack, request);
        request.setSerialNo(getUpdateSerialNo(p8583Pack));
        request.setMac(getMac(p8583Pack));
        request.setOperator(getOperator(p8583Pack));
        request.setDealamount(getDealamount(p8583Pack));
        return request;
    }
    private Double getDealamount(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(4)){

            Double d = Double.parseDouble(p8583Pack.getString(4));
            return  Double.parseDouble(new DecimalFormat("0.00").format(d))/100;
        }
        return 0.00;

    }
    private String getUpdateSerialNo(P8583Pack p8583Pack)throws Exception {
        if (p8583Pack.getHasValue(59)) {
            return p8583Pack.getString(59);
        }
        return  "";

    }
    private String getMac(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(47)){
            return p8583Pack.getString(47);
        }
        return "";

    }
    private String getOperator(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(63)){
            return p8583Pack.getString(63).trim();
        }
        return "";
    }

}
