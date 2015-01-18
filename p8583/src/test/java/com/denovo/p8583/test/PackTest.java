package com.denovo.p8583.test;

import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessageBuilders;
import com.denovo.p8583.ResponseMessageEncoders;
import com.denovo.p8583.SignInResponseMessageParser;
import com.denovo.p8583.requestMessageBuilders.SignInRequestMessageBuilder;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by moon.wa on 2015/1/18.
 */
public class PackTest {
    @Test
    public void pack() throws Exception {
        RequestMessageBuilders builders = new RequestMessageBuilders();
        builders.register("0800" , new SignInRequestMessageBuilder());

        String bytes_string = "600003000060220000000008000020000000c20012000452383038373938383830383630353737383038373938383800320000000000000000000000000000000000000000000000000000000000000000001100002199001000432020203031202020202020202020202020202020202020333333332020202020202020202020202020";
        byte[] bytes = Hex.decodeHex(bytes_string.toCharArray());
        P8583Pack pack = builders.pack(bytes);
        assertEquals("000452", pack.getString(11));
        assertEquals("80879888", pack.getString(41));
        assertEquals("086057780879888", pack.getString(42));
        assertEquals("00002199001", pack.getString(60));
        assertEquals("   01                  3333              \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000", pack.getString(63));


        // re-pack
        ResponseMessageEncoders encoders = new ResponseMessageEncoders();
        encoders.register("0800", new SignInResponseMessageParser());
        bytes = encoders.pack(  pack);
        assertEquals(bytes_string, new String(Hex.encodeHex(bytes)));

        // re-verify
        pack = builders.pack(bytes);
        assertEquals("000452", pack.getString(11));
        assertEquals("80879888", pack.getString(41));
        assertEquals("086057780879888", pack.getString(42));
        assertEquals("00002199001", pack.getString(60));
        assertEquals("   01                  3333              \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000", pack.getString(63));

    }
}
