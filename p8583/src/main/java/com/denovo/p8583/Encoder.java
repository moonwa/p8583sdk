package com.denovo.p8583;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by moonwa on 15-1-14.
 */
public class Encoder {
    public static byte[] fromBcd(byte value) {
        return StringUtils.leftPad(Integer.toHexString(value), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE)  / 4, Integer.SIZE / 4).getBytes();
    }
    public static byte[] toBcd(byte[] data) {
        if(data.length % 2 == 1){
            data = Arrays.copyOf(data, data.length + 1);
            data[data.length - 1] = '0';
        }

        ArrayList<Byte> results = new ArrayList<Byte>();

        for(int i = 0; i < data.length; i+=2){
            byte[] bytes = Arrays.copyOfRange(data, i, i + 2);
            String text = new String(bytes);

            byte d = toBcd(Integer.valueOf(text).byteValue());
            results.add(d);
        }
        return toArray(results);
    }

    private static byte toBcd(byte b) {
        return Integer.valueOf(Integer.toHexString(b)).byteValue();
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

    public static byte toLength(byte length) {
        return toBcd(length);
    }

    public static Collection<Byte> toArray(byte[] data) {
        ArrayList<Byte> results = new  ArrayList<Byte>();
        for(byte b : data){
            results.add(  b);
        }
        return results;
    }
    public static byte[] toArray(List<Byte> data) {
        byte[] results = new byte[data.size()];
        for(int i = 0; i < results.length; i++){
            results[i] = data.get(i);
        }
        return results;
    }

}
