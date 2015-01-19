package com.denovo.p8583server.handlerbuilders;

import com.denovo.p8583.*;
import com.denovo.p8583server.requestMessages.SignInRequestMessage;
import com.denovo.p8583server.handlers.SigninHandler;

public class SigninHandlerBuilder implements HandlerBuilder {

    public MessageHandler build(RequestMessage requestMessage) {
        return new SigninHandler((SignInRequestMessage)requestMessage);
    }
}
