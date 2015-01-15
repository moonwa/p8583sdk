package com.denovo.p8583;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class P8583Pack{
    private byte[] _tpud;
    private String messageType;
    private ArrayList<P8583Field> p8583Fields;

    public P8583Pack (String messageType, ArrayList<P8583Field> p8583Fields)
    {
        this.messageType = messageType;
        this.p8583Fields = p8583Fields;
    }
    public static P8583Pack pack(byte[] bytes, P8583PackFactory packFactory){
        String messageType =  StringUtils.leftPad(Integer.toHexString(bytes[11]), 2, '0') + StringUtils.leftPad(Integer.toHexString(bytes[12]), 2, '0');

        P8583PackBuilder builder = packFactory.getBuilder(messageType);
        byte[] tpud = Arrays.copyOfRange(bytes, 0, 5);

        String bitBitmap = "";
        for(int i = 13; i < 21; i++){
            bitBitmap += StringUtils.leftPad(Integer.toBinaryString(bytes[i]), 8, '0');;
        }
        P8583Pack pack = builder.build(messageType);
        int n = 0;
        int ptr = 21;
        char[] flags = bitBitmap.toCharArray();
        for(int i = 0; i < flags.length; i++){
            char flag = flags[i];
            if(flag == '1'){
                ptr += pack.writeData(i+1, bytes, ptr);
            }
        }


        return pack;
    }

    private int writeData(int index, byte[] bytes, int ptr) {
        P8583Field field = this.p8583Fields.get(index - 1);
        return   field.writeData(bytes, ptr);
    }
}