package com.denovo.p8583;

import com.sun.crypto.provider.DESCipher;
import org.apache.commons.codec.binary.Hex;
import sun.security.krb5.internal.crypto.Des;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
