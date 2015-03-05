package com.denovo.p8583server.requestMessageBuilders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583server.requestMessages.PosRegisteredRequestMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2015/1/23.
 */
public class PosRegisteredRequestMessageBuilder extends DefaultRequestMessagePackBuilder {

    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
    }
    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        PosRegisteredRequestMessage request = new PosRegisteredRequestMessage(p8583Pack.getMessageType(), p8583Pack.getTpud());
        super.update(p8583Pack, request);
        request.setSerialNo(getSerialNo(p8583Pack));
        request.setBatchNo(getBatchNo(p8583Pack));
        request.setPosAddress(setPosAddress(p8583Pack));
        request.setMac(getMac(p8583Pack));
        return request;
    }

    private String setPosAddress(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(62))
        {
            return p8583Pack.getString(62);
          //  if (StringUtils.isBlank(text))
            //    return null;
            //return text.substring(3, 20).trim();
        }
        return null;
    }
    private String getMac(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(47)){
            return p8583Pack.getString(47);
        }
        return null;

    }
}
