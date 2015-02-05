package com.denovo.p8583;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.security.SecureRandom;
import java.security.Security;


import sun.misc.BASE64Encoder;

import java.util.ArrayList;
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
           // macBuffer=new byte[]{2,0,48,32,4,(byte)192,32,(byte)194,(byte)152,55,0,0,0,0,0,0,0,37,0,0,23,84,5,16,0,18,8,16,(byte)128,0,6,80,48,48,48,48,48,54,49,56,48,48,48,48,54,32,32,32,32,32,32,32,32,32,0,50,97,56,97,102,101,100,101,97,98,52,101,52,56,55,52,102,98,102,52,54,100,48,99,100,56,57,55,102,51,57,56,57,49,53,54,(byte)176,(byte)150,113,3,95,30,(byte)199,(byte)150,32,0,0,0,0,0,0,0,0,54,56,48,48,48,48,54,80,48,48,48,48,48,54,49,48,48,50,50,50,54,48,48,49,55,53,53,48,50,48,52,49,54,53,51,52,56,0,8,34,0,34,38,0,56,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,48,79,78,76,89,83,65,76,69,0,35,32,32,32,104,105,50,54,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32};
        int index = 0;
        //取前两个需要异或的Block
        byte[] temp_block = new byte[8];
        byte[] sub_Mac = GetSubMac(macBuffer, index++ * 8);



        while (sub_Mac != null)
        {
            for (int i = 0; i < 8; i++)
            {
                //每8个字节异或
                temp_block[i] = (byte)(sub_Mac[i] ^ temp_block[i]);
            }

            //获取下一个需要异或的8字节
            //GetSubMac方法有具体的描述
            sub_Mac = GetSubMac(macBuffer, index++ * 8);
        }
            String result_block = "";
            //temp_block=new byte[]{(byte)149,(byte)235,(byte)205,(byte)179,55,(byte)180,94,(byte)210};
        //因为后面暂时只用到了转换后的前8个字节，所以只处理一般长度 temp_block.Length / 2
        for (int i = 0; i < temp_block.length / 2; i++)
        {
            //将temp_block[i]中的值由10进制转化为16进制，如果不足2位左补0
            result_block+=StringUtils.leftPad(Integer.toHexString(temp_block[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE) / 4, Integer.SIZE / 4);
        }




        //转换为大写 关键
            result_block=result_block.toUpperCase();

        //-------------------------------------------------------------------------------------------------------------------------
                /*文档中的描述
                 *  d)  取前8 个字节用MAK加密：
                    ENC BLOCK1 = eMAK（TM311 TM312 TM321 TM322 TM331 TM332 TM341 TM342）
			                    = EN11 EN12 EN13 EN14 EN15 EN16 EN17 EN18
                 */
        //存放加密后的内容
        byte[] enc_block = new byte[8];
        //加密 result_block的内容是之前计算好的HEXDECIMAL
        enc_block =MacEncrypt(result_block.getBytes("utf-8"), Key.getBytes("utf-8"));

        //-------------------------------------------------------------------------------------------------------------------------
                /* 文档中的描述
                    e)  将加密后的结果与后8 个字节异或：
                        EN11  EN12  EN13  EN14  EN15  EN16  EN17  EN18
                        XOR）     	TM351 TM352 TM361 TM362 TM371 TM372 TM381 TM382
                        ------------------------------------------------------------
                        TEMP BLOCK=	TE11  TE12  TE13  TE14  TE15  TE16  TE17  TE18
                 */
        //首先取后8个字节的内容 temp_block.Length / 2
        result_block = "";

        for (int i = temp_block.length / 2; i < temp_block.length; i++)
        {

            result_block+=StringUtils.leftPad(Integer.toHexString(temp_block[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE) / 4, Integer.SIZE / 4);
        }
        //转换为大写 关键
        result_block = result_block.toUpperCase();
            temp_block = result_block.getBytes("utf-8");
        //将后8个字节的HEXDECIMAL转化为byte[]
        //将加密后的结果与后8 个字节异或
        for (int i = 0; i < 8; i++)
        {
            temp_block[i] = (byte)(enc_block[i] ^ temp_block[i]);
        }

        //-------------------------------------------------------------------------------------------------------------------------
                /* 文档中的描述
                 f)  用异或的结果TEMP BLOCK 再进行一次单倍长密钥算法运算。
                        ENC BLOCK2 = eMAK（TE11 TE12 TE13 TE14 TE15 TE16 TE17 TE18）
		               = EN21 EN22 EN23 EN24 EN25 EN26 EN27 EN28
                 */
        //存放加密后的结果
        byte[] enc_block2 = new byte[8];
        enc_block2 = MacEncrypt(temp_block, Key.getBytes());
        //-------------------------------------------------------------------------------------------------------------------------

                /* 文档中的描述
                g)  将运算后的结果（ENC BLOCK2）转换成16 个HEXDECIMAL：
                        ENC BLOCK2 = EN21 EN22 EN23 EN24 EN25 EN26 EN27 EN28
                        = EM211 EM212 EM221 EM222 EM231 EM232 EM241 EM242 ||
 			             EM251 EM252 EM261 EM262 EM271 EM272 EM281 EM282
                h)  取前8个字节作为MAC值。
                 */
            result_block = "";
        //因为只取前8个字节作为MAC值，所以计算的长度是加密数组的一半 enc_block2.Length / 2
        for (int i = 0; i < enc_block2.length / 2; i++)
        {
            //将enc_block2[i]中的值由10进制转化为16进制，如果不足2位左补0

            result_block+=StringUtils.leftPad(Integer.toHexString(enc_block2[i]), Integer.SIZE / 4, '0').substring((Integer.SIZE - Byte.SIZE)  / 4, Integer.SIZE / 4);
        }
        result_block = result_block.toUpperCase();

        //-------------------------------------------------------------------------------------------------------------------------
        //到这里result_block的值就应该是MAC的值
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
