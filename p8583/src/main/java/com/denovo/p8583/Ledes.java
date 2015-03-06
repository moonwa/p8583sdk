package com.denovo.p8583;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;



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
        byte[] resultByte = new byte[7];
        int len=0;
        for (int i = 0; i < 8; i++)
        {
            if(i==0)
                len = (byte)(key[i]^decrypt[i]);
            else
                resultByte[i-1]=(byte)(key[i]^decrypt[i]);
        }
        return bytesToHexString(resultByte).replace("-", "").substring(0, len);
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    private static byte[] getHAccno(String accno)  {

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
        return encode;
    }
    public static byte[] DesDecryptB(String  passWork,String key1){

        if (passWork.length() < 1)
            return new byte[0];

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
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
                .byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
                .byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }
    public  static boolean MACDecrypt(byte[] buffer, String key, String MAC){
        byte[] macBuffer = Arrays.copyOf(buffer,buffer.length-8);
        String mac = MACEncrypt(macBuffer, key, 0);
        return MAC.equals(mac) ? true : false;
    }

    public static String MACEncrypt(byte[] buffer, String Key, int idx){
        try {
        byte[] macBuffer = Arrays.copyOfRange(buffer,11 + idx, buffer.length);
        int index = 0;

        byte[] temp_block = new byte[8];
        byte[] sub_Mac = GetSubMac(macBuffer, index++ * 8);
        while (sub_Mac != null)
        {
            for (int i = 0; i < 8; i++)
            {

                temp_block[i] = (byte)(sub_Mac[i] ^ temp_block[i]);
            }

            sub_Mac = GetSubMac(macBuffer, index++ * 8);
        }
            String result_block = "";

        for (int i = 0; i < temp_block.length / 2; i++)
        {

            result_block+=StringUtils.leftPad(Integer.toHexString(temp_block[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE) / 4, Integer.SIZE / 4);
        }

            result_block=result_block.toUpperCase();


        byte[] enc_block = new byte[8];

        enc_block =MacEncrypt(result_block.getBytes("utf-8"), Key.getBytes("utf-8"));


        result_block = "";
        for (int i = temp_block.length / 2; i < temp_block.length; i++)
        {

            result_block+=StringUtils.leftPad(Integer.toHexString(temp_block[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE) / 4, Integer.SIZE / 4);
        }

        result_block = result_block.toUpperCase();
            temp_block = result_block.getBytes("utf-8");

        for (int i = 0; i < 8; i++)
        {
            temp_block[i] = (byte)(enc_block[i] ^ temp_block[i]);
        }

        byte[] enc_block2 = new byte[8];
        enc_block2 = MacEncrypt(temp_block, Key.getBytes());

            result_block = "";

        for (int i = 0; i < enc_block2.length / 2; i++)
        {


            result_block+=StringUtils.leftPad(Integer.toHexString(enc_block2[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE)  / 4, Integer.SIZE / 4);
        }
        result_block = result_block.toUpperCase();


        temp_block =result_block.getBytes();
        String ret = "";

        for (int i = 0; i < result_block.length(); i++) {
          ret+= StringUtils.leftPad(Integer.toHexString(temp_block[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE)  / 4, Integer.SIZE / 4);
        }
        return ret;
         }
         catch (Exception ex)
        {

        }
    return "";
    }








    public static byte[] MacEncrypt(byte[] text, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(text);

    }



    private static byte[] GetSubMac(byte[] macBuffer, int index)
    {
        byte[] buffer = new byte[8];

        if (macBuffer.length <= index) {
            return null;
        }
        else if (macBuffer.length - index < 8)
        {
            int len = macBuffer.length - index;
            System.arraycopy(macBuffer, index, buffer, 0, len);
            for (int i = 0; i < 8 - len; i++)
            {
                buffer[len + i] = 0x00;
            }
        }
        else
        {
            System.arraycopy(macBuffer, index, buffer, 0, 8);
        }
        return buffer;
    }












}
