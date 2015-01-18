package com.denovo.p8583;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moonwa on 15-1-14.
 */
class Encoder {
    public static byte[] fromBcd(byte value) {
        return StringUtils.leftPad(Integer.toHexString(value), 2, '0').getBytes();
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
}
