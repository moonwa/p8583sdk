package com.denovo.p8583server.test;

import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.RequestMessageBuilders;
import com.denovo.p8583.requestMessageBuilders.SignInRequestMessageBuilder;
import com.denovo.p8583.requestMessages.SignInRequestMessage;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by moonwa on 15-1-18.
 */
public class RequestMessageBuildersTest {
    @Test
    public void buildSignIn() throws Exception {
        RequestMessageBuilders builders = new RequestMessageBuilders();
        builders.register("0800" , new SignInRequestMessageBuilder());

        byte[] bytes = Hex.decodeHex("600003000060220000000008000020000000c20012000452383038373938383830383630353737383038373938383800320000000000000000000000000000000000000000000000000000000000000000001100002199001000432020203031202020202020202020202020202020202020333333332020202020202020202020202020".toCharArray());
        SignInRequestMessage signinRequestMessage = (SignInRequestMessage) builders.build(bytes);
        assertEquals("000453", signinRequestMessage.getSerialNo());
    }
}
