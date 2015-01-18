package com.denovo.p8583.requestMessageBuilders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.fields.BinaryVarLengthP8583Field;
import com.denovo.p8583.requestMessages.SignInRequestMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by moonwa on 15-1-18.
 */
public class SignInRequestMessageBuilder extends DefaultRequestMessagePackBuilder {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
        fields.addP8583ItemAt(62, new BinaryVarLengthP8583Field(72, 3, true));
    }

    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        SignInRequestMessage request = new SignInRequestMessage(p8583Pack.getMessageType(), p8583Pack.getTpud(), getMac(p8583Pack));
        super.update(p8583Pack, request);

        request.setUserName(getUserName(p8583Pack));
        request.setPassword(getPassword(p8583Pack));
        request.setSerialNo(getSerialNo(p8583Pack));
        request.setBatchNo(getBatchNo(p8583Pack));
        return request;
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
