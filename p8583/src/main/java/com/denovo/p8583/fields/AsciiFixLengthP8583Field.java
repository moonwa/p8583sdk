package com.denovo.p8583.fields;

import com.denovo.p8583.P8583Field;

import java.util.Arrays;

/**
 * Created by moonwa on 15-1-14.
 */
public class AsciiFixLengthP8583Field extends P8583Field {
    private int length;

    public AsciiFixLengthP8583Field(int length) {
        this.length = length;
    }

    @Override
    public int writeData(byte[] bytes, int ptr) {
        this.setData(Arrays.copyOfRange(bytes, ptr, ptr + this.length));
        return this.length;
    }

    @Override
    protected byte[] buildData(byte[] data) {
        return data;
    }
}
