package com.denovo.p8583.fields;

import com.denovo.p8583.Encoder;
import com.denovo.p8583.P8583Field;
import org.apache.commons.codec.binary.Hex;

import java.util.ArrayList;
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
    public int writeData(byte[] writebyte, int ptr) {
        int len = 0;
        int skip = 0;
        if (this.varLength == 2) {
            skip = 1;
            len = Encoder.getLength(Arrays.copyOfRange(writebyte, ptr  , ptr + skip));
        }
        if (this.varLength == 3) {
            skip = 2;
            len = Encoder.getLength(Arrays.copyOfRange(writebyte, ptr  , ptr + skip));
        }
//        if (this.isCompress) {
//            len = len * 2;
//        }

        byte[] data = Arrays.copyOfRange(writebyte, ptr + skip, ptr + skip + len);
        this.setData(data);
        return skip + len;
    }

    @Override
    protected byte[] buildData(byte[] data) {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        int len = data.length;
        if (this.isCompress) {
            len = len / 2;
        }
        if (this.varLength == 2) {
            bytes.add(Encoder.toLength((byte) len));
        }
        if (this.varLength == 3) {
            bytes.add(Encoder.toLength((byte) (len / 100)));
            bytes.add(Encoder.toLength((byte) (len % 100)));
        }
        bytes.addAll(Encoder.toArray(data));
        return Encoder.toArray(bytes);
    }
}
