package com.denovo.p8583.test;

import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessageBuilders;
import com.denovo.p8583.ResponseMessageEncoders;
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

        String bytes_string = "600003000060220000000008000020000000c200120004523830383739383838303836303537373830383739383838003200000000000000000000000000000000000000000000000000000000000000000011000021990010004320202030312020202020202020202020202020202020203333333320202020202020202020202020202020";
        byte[] bytes = Hex.decodeHex(bytes_string.toCharArray());
        P8583Pack pack = builders.pack(bytes);
        assertEquals("000452", pack.getString(11));
        assertEquals("80879888", pack.getString(41));
        assertEquals("086057780879888", pack.getString(42));
        assertEquals("00002199001", pack.getString(60));
        assertEquals("   01                  3333                ", pack.getString(63));

        // re-pack
        ResponseMessageEncoders encoders = new ResponseMessageEncoders();
        encoders.register("0800", new SignInResponseMessageParser());
        bytes = encoders.pack(pack);
        assertEquals(bytes_string, new String(Hex.encodeHex(bytes)));
    }
    @Test
    public void packWith0810() throws Exception {
        RequestMessageBuilders builders = new RequestMessageBuilders();
        builders.register("0810" , new SignInRequestMessageBuilder());

        String bytes_string = "600003000060220000000008100020000000c200120004523830383739383838303836303537373830383739383838003200000000000000000000000000000000000000000000000000000000000000000011000021990010004320202030312020202020202020202020202020202020203333333320202020202020202020202020202020";
        byte[] bytes = Hex.decodeHex(bytes_string.toCharArray());
        P8583Pack pack = builders.pack(bytes);
        ResponseMessageEncoders encoders = new ResponseMessageEncoders();
        encoders.register("0810", new SignInResponseMessageParser());
        bytes = encoders.pack(pack);
        assertEquals(bytes_string, new String(Hex.encodeHex(bytes)));
    }
}
