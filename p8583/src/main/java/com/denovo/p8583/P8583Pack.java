package com.denovo.p8583;
import java.util.ArrayList;

public class P8583Pack {
    private byte[] _tpud;
    private String messageType;
    private ArrayList<P8583Field> p8583Fields;

    public P8583Pack(String messageType, ArrayList<P8583Field> p8583Fields) {
        this.messageType = messageType;
        this.p8583Fields = p8583Fields;
    }

    public int writeData(int index, byte[] bytes, int ptr) {
        P8583Field field = this.p8583Fields.get(index - 1);
        return field.writeData(bytes, ptr);
    }

    public String getMessageType() {
        return messageType;
    }

    public String getString(int index) throws Exception {
        return this.p8583Fields.get(index - 1).getString();
    }

    public byte[] getByteArray(int index) {
        return this.p8583Fields.get(index - 1).getByteArray();
    }

    public   boolean getHasValue(int index) {
        return this.p8583Fields.get(index - 1).getHasValue();
    }
}