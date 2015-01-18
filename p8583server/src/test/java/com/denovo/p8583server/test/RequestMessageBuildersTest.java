package com.denovo.p8583server.test;

import com.denovo.p8583.P8583Field;
import com.denovo.p8583.P8583Pack;
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
        P8583Pack pack = builders.pack(bytes);
        for (int fieldIndex : pack.getFieldIndexs()){
            System.out.println(fieldIndex + "." + pack.getString(fieldIndex));
        }
    }
    @Test
    public void pack() throws Exception {
        RequestMessageBuilders builders = new RequestMessageBuilders();
        builders.register("0800" , new SignInRequestMessageBuilder());

        byte[] bytes = Hex.decodeHex("600003000060220000000008000020000000c20012000452383038373938383830383630353737383038373938383800320000000000000000000000000000000000000000000000000000000000000000001100002199001000432020203031202020202020202020202020202020202020333333332020202020202020202020202020".toCharArray());
        P8583Pack pack = builders.pack(bytes);
        assertEquals("000452", pack.getString(11));
        assertEquals("80879888", pack.getString(41));
        assertEquals("086057780879888", pack.getString(42));
        assertEquals("00002199001", pack.getString(60));
        System.out.println("'" +  pack.getString(63) + "'");
        assertEquals("   01                  3333              \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000", pack.getString(63));
    }
}
