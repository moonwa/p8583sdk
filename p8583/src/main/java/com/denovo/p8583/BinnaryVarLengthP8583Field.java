package com.denovo.p8583;

import java.util.Arrays;

public class BinnaryVarLengthP8583Field extends P8583Field {
    private int length;
    private int varLength;
    private boolean isCompress;

    public BinnaryVarLengthP8583Field(int length, int varLength, boolean isCompress) {
        this.length = length;
        this.varLength = varLength;
        this.isCompress = isCompress;
    }

    @Override
    public int writeData(byte[] bytes, int ptr) {
        int len = 0;

        int skip = 0;
        if(this.varLength == 2){
            Byte b = bytes[ptr];
            len = Integer.valueOf(b.toString(), 16);
            skip = 1;
        }
        if(this.varLength == 3){
            len = Integer.valueOf(((Byte)bytes[ptr]).toString(), 16);
            len = len*100 + Integer.valueOf(((Byte)bytes[ptr+1]).toString(), 16);
            skip = 2;
        }
        if(this.isCompress){
            len = len * 2;
        }

        int actual_len = len;
        // 跳过自动补0
        if(len % 2 == 1){
            actual_len = actual_len + 1;
        }
        byte[] data = Arrays.copyOfRange(bytes, ptr + skip, ptr + skip + actual_len / 2);
        data = Encoder.fromBcds(data);
        if(len % 2 == 1){
            data = Arrays.copyOfRange(data, 1, data.length);
        }
        this.setData(data);
        return skip + actual_len;
    }
}
