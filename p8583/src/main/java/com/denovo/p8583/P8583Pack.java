package com.denovo.p8583;
import java.util.ArrayList;

public class P8583Pack {
    private byte[] tpud;
    private String messageType;
    private ArrayList<P8583Field> p8583Fields;

    public P8583Pack(byte[] tpud, String messageType, ArrayList<P8583Field> p8583Fields) {
        this.tpud = tpud;
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

    public Integer[] getFieldIndexs( ) {
        ArrayList<Integer> items = new ArrayList<Integer>();
        for(int i = 0; i < this.p8583Fields.size(); i++){
            if(  this.p8583Fields.get(i).getHasValue()){
                items.add(i+1);
            }
        }
        Integer[] result = new Integer[items.size()];
        items.toArray(result);
        return result;
    }

    public void setString(int index, String text) throws Exception {
        this.p8583Fields.get(index - 1).setString(  text);
    }

    public byte[] buildData(int index) {
        return this.p8583Fields.get(index - 1).buildData();
    }

    public byte[] getTpud() {
        return tpud;
    }
}