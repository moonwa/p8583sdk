package com.denovo.p8583.fields;

import com.denovo.p8583.P8583Field;

/**
 * Created by moonwa on 15-1-14.
 */
public class EmptyP8583Field extends P8583Field {
    @Override
    public int writeData(byte[] bytes, int ptr) {
        return 0;
    }

    @Override
    public String getString() throws Exception {
        return "";
    }

    @Override
    public byte[] getByteArray() {
        return new byte[0];
    }

    @Override
    public boolean getIsIgnore() {
        return true;
    }
}
