package com.denovo.p8583server.responseMessagesEncoders;

import com.denovo.p8583.P8583Fields;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.ResponseMessage;

import com.denovo.p8583server.responseMessages.SignInOutResponseMessage;


/**
 * Created by Administrator on 2015/2/9.
 */
public class SignInOutResponseMessageEncoder   extends DefaultResponseMessageParser {
    @Override
    public void Init(P8583Fields fields) {
        super.Init(fields);

    }
    @Override
    public void update(ResponseMessage message, P8583Pack pack) throws Exception {
        SignInOutResponseMessage msg = (SignInOutResponseMessage) message;
        if(msg.getResult()!=0) {
            pack.setString(47, msg.getResultMsg());
        }
        super.update(message, pack);
    }
}