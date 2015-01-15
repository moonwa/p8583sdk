package com.denovo.p8583;

import java.util.ArrayList;

/**
 * Created by moonwa on 15-1-14.
 */
public abstract class P8583Field {
    private byte[] data;

    public abstract int writeData(byte[] bytes, int ptr) ;

    public void setData(byte[] data) {
        this.data = data;
    }

    public boolean getIsIgnore() {
        return false;
    }
}
