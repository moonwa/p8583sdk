package com.denovo.p8583;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by moonwa on 15-1-14.
 */
public abstract class P8583Field {
    private byte[] data;
    private boolean hasValue = false;
    public abstract int writeData(byte[] bytes, int ptr);
    protected abstract byte[] buildData(byte[] data);


    public void setData(byte[] data) {
        hasValue=true;
        this.data = data;
    }


    public String getString() throws Exception {
        return new String(this.data, "utf8");
    }

    public byte[] getByteArray() {
        return data;
    }

    public  boolean getHasValue(){
        return hasValue;
    };

    public void setString(String text) throws Exception {
        this.setData(text.getBytes("utf8"));
    }

    public byte[] buildData(){
        return buildData(this.data);
    }
}
