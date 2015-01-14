package com.denovo.p8583;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class P8583Pack{
    private byte[] _tpud;
    private ArrayList<P8583Item> p8583Items;

    public static P8583Pack pack(byte[] bytes, P8583PackBuilder builder){
        String messageType =  StringUtils.leftPad(Integer.toHexString(bytes[11]), 2, '0') + StringUtils.leftPad(Integer.toHexString(bytes[12]), 2, '0');
        byte[] tpud = Arrays.copyOfRange(bytes, 0, 5);

        String bitBitmap = "";
        for(int i = 13; i < 21; i++){
            bitBitmap += StringUtils.leftPad(Integer.toBinaryString(bytes[13]), 8, '0');;
        }

        int n = 0;
        int ptr = 21;
        char[] flags = bitBitmap.toCharArray();
        for(P8583Item  p8583Item : builder.getP8583Items()){
            if(flags[p8583Item.getIndex()] == '1'){
                ptr = p8583Item.writeData(bytes, ptr);
            }
        }

        return builder.build();
    }

    public void setP8583Items(ArrayList<P8583Item> p8583Items) {
        this.p8583Items = p8583Items;
    }

    public ArrayList<P8583Item> getP8583Items() {
        return p8583Items;
    }
}