package com.denovo.p8583.handlerbuilders;

import com.denovo.p8583.*;
import com.denovo.p8583.requestMessages.SignInRequestMessage;

public class SigninHandlerBuilder implements HandlerBuilder {

    public MessageHandler build(RequestMessage requestMessage) {
        return new SigninHandler((SignInRequestMessage)requestMessage);
    }
}
