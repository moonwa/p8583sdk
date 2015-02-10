package com.denovo.p8583server.handlerbuilders;

import com.denovo.p8583.HandlerBuilder;
import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583server.handlers.SignInOutHandler;
import com.denovo.p8583server.requestMessages.SignInOutRequestMessage;


/**
 * Created by Administrator on 2015/2/9.
 */
public class SignInOutHandlerBuilder implements HandlerBuilder {

    public MessageHandler build(RequestMessage requestMessage) {
        return new SignInOutHandler((SignInOutRequestMessage)requestMessage);
    }
}
