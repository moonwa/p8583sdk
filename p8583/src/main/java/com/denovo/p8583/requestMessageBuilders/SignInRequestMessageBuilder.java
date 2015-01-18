package com.denovo.p8583.requestMessageBuilders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.fields.BinnaryVarLengthP8583Field;
import com.denovo.p8583.requestMessages.SignInRequestMessage;

/**
 * Created by moonwa on 15-1-18.
 */
public class SignInRequestMessageBuilder extends DefaultRequestMessagePackBuilder {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);
        fields.addP8583ItemAt(62, new BinnaryVarLengthP8583Field(72, 3, true));
    }

    @Override
    protected RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception {
        SignInRequestMessage request = new SignInRequestMessage(p8583Pack.getMessageType(), getMac(p8583Pack));
        request.setUserName(p8583Pack.getString(1));
        return request;
    }
}
