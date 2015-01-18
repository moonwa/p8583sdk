package com.denovo.p8583;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moonwa on 15-1-14.
 */
public class Encoder {
    public static byte[] fromBcd(byte value) {
        return StringUtils.leftPad(Integer.toHexString(value), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE)  / 4, Integer.SIZE / 4).getBytes();
    }

    public static byte[] fromBcds(byte[] bytes) {
        byte[] results = new byte[bytes.length * 2];

        for (int i = 0; i < bytes.length; i++) {
            byte[] bcdBytes = fromBcd(bytes[i]);
            results[i * 2] = bcdBytes[0];
            results[i * 2 + 1] = bcdBytes[1];
        }
        return results;
    }
    public static int getLength(byte[] bytes){
        return Integer.parseInt(new String(Hex.encodeHex(bytes)));
    }
}
