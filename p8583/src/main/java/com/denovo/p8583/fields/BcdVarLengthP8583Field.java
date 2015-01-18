package com.denovo.p8583.fields;

import com.denovo.p8583.Encoder;
import com.denovo.p8583.P8583Field;

import java.util.Arrays;

/**
 * Created by moonwa on 15-1-14.
 */
public class BcdVarLengthP8583Field extends P8583Field {
    private int length;
    private int varLength;
    private boolean isCompress;

    public BcdVarLengthP8583Field(int length, int varLength, boolean isCompress) {
        this.length = length;
        this.varLength = varLength;
        this.isCompress = isCompress;
    }

    @Override
    public int writeData(byte[] bytes, int ptr) {
        int len = 0;

        int skip = 0;
        if (this.varLength == 2) {
            skip = 1;
            len = Encoder.getLength(Arrays.copyOfRange(bytes, ptr  , ptr + skip));
        }
        if (this.varLength == 3) {
            skip = 2;
            len = Encoder.getLength(Arrays.copyOfRange(bytes, ptr  , ptr + skip));
        }
        if (this.isCompress) {
            len = len * 2;
        }

        int actual_len = len;
        // 跳过自动补0
        if (len % 2 == 1) {
            actual_len = actual_len + 1;
        }
        byte[] data = Arrays.copyOfRange(bytes, ptr + skip, ptr + skip + actual_len / 2);
        data = Encoder.fromBcds(data);
        if (len % 2 == 1) {
            data = Arrays.copyOfRange(data, 0, data.length - 1);
        }
        this.setData(data);
        return skip + actual_len / 2;
    }
}
