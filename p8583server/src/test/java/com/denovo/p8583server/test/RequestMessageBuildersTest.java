package com.denovo.p8583server.test;

import com.denovo.p8583.RequestMessageBuilders;
import com.denovo.p8583server.requestMessageBuilders.SignInRequestMessageBuilder;
import com.denovo.p8583server.requestMessages.SignInRequestMessage;
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

        byte[] bytes = Hex.decodeHex("600003000060220000000008000020000000c2001200045238303837393838383038363035373738303837393838380032000000000000000000000000000000000000000000000000000000000000000000110000219900100043202020303120202020202020202020202020202020202033333333202020202020202020202020202020202020".toCharArray());
        SignInRequestMessage signinRequestMessage = (SignInRequestMessage) builders.build(bytes);
        assertEquals("000452", signinRequestMessage.getSerialNo());
        assertEquals("086057780879888", signinRequestMessage.getClientId());
        assertEquals("80879888", signinRequestMessage.getTerminalId());
        assertEquals("00002199001", signinRequestMessage.getBatchNo());
        assertEquals("01", signinRequestMessage.getUserName());
        assertEquals("3333", signinRequestMessage.getPassword());
    }

}
