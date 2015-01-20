package com.denovo.p8583;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by moon.wa on 2015/1/19.
 */
public class Ledes {
    public static String encrypt(byte[] text, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] byteMi = cipher.doFinal(text);
        return new String(Hex.encodeHexString(byteMi)).toUpperCase();
    }
}
