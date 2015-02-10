package com.denovo.p8583server.requestMessageBuilders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583server.requestMessages.SignInOutRequestMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2015/2/9.
 */
public class SignInOutRequestMessageBuilder extends DefaultRequestMessagePackBuilder {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
    }

    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        SignInOutRequestMessage request = new SignInOutRequestMessage(p8583Pack.getMessageType(), p8583Pack.getTpud());
        super.update(p8583Pack, request);

        request.setUserName(getUserName(p8583Pack));
        request.setPassword(getPassword(p8583Pack));
        request.setSerialNo(getSerialNo(p8583Pack));
        request.setBatchNo(getBatchNo(p8583Pack));
        request.setMac(getMac(p8583Pack));
        return request;
    }
    private String getMac(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(47))
        {
            String text = p8583Pack.getString(47);
            if (StringUtils.isBlank(text))
                return null;
            return text;
        }
        return null;
    }

    private String getUserName(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(63))
        {
            String text = p8583Pack.getString(63);
            if (StringUtils.isBlank(text))
                return null;
            return text.substring(3, 20).trim();
        }
        return null;
    }
    private String getPassword(P8583Pack p8583Pack) throws Exception {
        if (p8583Pack.getHasValue(63))
        {
            String text = p8583Pack.getString(63);
            if (StringUtils.isBlank(text))
                return null;
            return text.substring(21, 40).trim();
        }
        return null;
    }

}
