package com.denovo.p8583;

import java.util.Arrays;

/**
 * Created by moonwa on 15-1-14.
 */
public class AsciiFixLengthP8583Item extends P8583Item {
    private int length;

    public AsciiFixLengthP8583Item(int length) {
        this.length = length;
    }

    @Override
    public int writeData(byte[] bytes, int ptr) {
        this.setData(Arrays.copyOfRange(bytes, ptr, ptr + this.length));
        return this.length;
    }
}
