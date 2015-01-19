package com.denovo.p8583;

public class RequestMessage{
    private String messageType;
    private byte[] tpud;

    public RequestMessage(String messageType, byte[] tpud) {
        this.messageType = messageType;
        this.tpud = tpud;
    }

    public String getMessageType() {
        return this.messageType;
    }



    public byte[] getTpud() {
        return tpud;
    }
}
