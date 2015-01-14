package com.denovo.p8583;

/**
 * Created by moonwa on 15-1-14.
 */
public abstract class P8583Item {
    private int index;
    private byte[] data;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public abstract int writeData(byte[] bytes, int ptr) ;

    public void setData(byte[] data) {
        this.data = data;
    }
}
