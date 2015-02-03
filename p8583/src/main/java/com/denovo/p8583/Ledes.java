package com.denovo.p8583;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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







    public static byte[] DesDecryptB(byte[] text, byte[] key)throws Exception
    {
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] byteMi = cipher.doFinal(text);
        return byteMi;
    }

    public static String GetPasswordFromDecrypt(byte[] decrypt)throws Exception{


   byte[] key= getHAccno("000000000000");
        byte[] bytes = new byte[7];
        int len=0;
        for (int i = 0; i < 8; i++)
        {
            if(i==0)
                len = (byte)(key[i]^decrypt[i]);
            else
                bytes[i-1]=(byte)(key[i]^decrypt[i]);
        }


        return Arrays.copyOf(bytes,7).toString().replace("-", "").substring(0, len);

    }

    private static byte[] getHAccno(String accno)  {
        //取出主帐号；
        int len = accno.length();
        byte arrTemp[] = accno.getBytes();
        byte arrAccno[] = new byte[12];
        for (int i = 0; i < 12; i++) {
            arrAccno[i] = (i <= arrTemp.length ? arrTemp[i] : (byte) 0x00);
        }
        byte encode[] = new byte[8];
        encode[0] = (byte) 0x00;
        encode[1] = (byte) 0x00;
        encode[2] = (byte) uniteBytes(arrAccno[0], arrAccno[1]);
        encode[3] = (byte) uniteBytes(arrAccno[2], arrAccno[3]);
        encode[4] = (byte) uniteBytes(arrAccno[4], arrAccno[5]);
        encode[5] = (byte)uniteBytes(arrAccno[6], arrAccno[7]);
        encode[6] = (byte) uniteBytes(arrAccno[8], arrAccno[9]);
        encode[7] = (byte)uniteBytes(arrAccno[10], arrAccno[11]);
        printHexString("encoded accno：", encode);
        return encode;
    }







    public static byte[] ToHexArray(String pToDecrypt) throws Exception{

        byte[] results = new byte[pToDecrypt.length() / 2];
        for (int i = 0; i < pToDecrypt.length() / 2; i++)
        {
            results[i] =  Byte.parseByte(pToDecrypt.substring(i*2, i*2+2),16);
        }
        return results;
    }



    public static byte[] DesDecryptB(String  passWork,String key1){

        if (passWork.length() < 1)
            return new byte[0];
        passWork="29e9be777780d321";
        key1="bc8e2fb4";
        try
        {
            byte[] key = key1.getBytes();
            String sData = passWork;
            byte[] inputByteArray = new byte[sData.length() / 2];
            for (int x = 0; x < passWork.length() / 2; x++)
            {
                int i = (Integer.parseInt(sData.substring(x * 2, x * 2 + 2), 16));
                inputByteArray[x] = (byte)i;
            }
            return DesDecryptB(inputByteArray, key);
        }
        catch (Exception ex)
        {

        }
        return new byte[0];

    }
    public static void printHexString(String hint, byte[] b) {
        System.out.print(hint);
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + ' ');
        }
        System.out.println(' ');
    }

    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }












}
