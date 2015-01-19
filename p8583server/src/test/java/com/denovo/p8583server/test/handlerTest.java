package com.denovo.p8583server.test;

import com.denovo.p8583server.handlers.SigninHandler;
import com.denovo.p8583server.requestMessages.SignInRequestMessage;
import com.denovo.p8583server.responseMessages.SignInResponseMessage;
import org.junit.Test;

/**
 * Created by 013495 on 2015/1/19.
 */
public class HandlerTest {
    @Test
    public void signInHandlerTest() throws Exception {
        SignInRequestMessage requestMessage = new SignInRequestMessage("0800", new byte[0], new byte[0]);
        requestMessage.setPassword("abc123");
        requestMessage.setUserName("hi26");
        requestMessage.setTerminalId("P0000055");
        requestMessage.setClientId("800006");
        SigninHandler handler = new SigninHandler(requestMessage);
        SignInResponseMessage msg = (SignInResponseMessage) handler.handle();
    }
}
