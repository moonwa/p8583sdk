package com.denovo.p8583.fields;

import com.denovo.p8583.P8583Field;

import java.util.Arrays;

public class AsciiVarLengthP8583Field extends P8583Field {
    private int length;
    private int varLength;
    private boolean isCompress;

    public AsciiVarLengthP8583Field(int length, int varLength, boolean isCompress) {
        this.length = length;
        this.varLength = varLength;
        this.isCompress = isCompress;
    }

    @Override
    public int writeData(byte[] bytes, int ptr) {
        int len = 0;
        int skip = 0;
        if (this.varLength == 2) {
            Byte b = bytes[ptr];
            len = Integer.valueOf(b.toString(), 16);
            skip = 1;
        }
        if (this.varLength == 3) {
            len = Integer.valueOf(((Byte) bytes[ptr]).toString(), 16);
            len = len * 100 + Integer.valueOf(((Byte) bytes[ptr + 1]).toString(), 16);
            skip = 2;
        }
        if (this.isCompress) {
            len = len * 2;
        }

        byte[] data = Arrays.copyOfRange(bytes, ptr + skip, ptr + skip + len);
        this.setData(data);
        return skip + len;
    }
}
