package com.denovo.p8583;

import com.denovo.p8583.requestMessages.SignInRequestMessage;

/**
 * Created by moonwa on 15-1-18.
 */
public class SigninHandler implements MessageHandler {
    private SignInRequestMessage requestMessage;

    public SigninHandler(SignInRequestMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public ResponseMessage handle() {
        return null;
    }
}
