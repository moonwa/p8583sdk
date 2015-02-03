package com.denovo.p8583;

public class ResponseMessage{
    private String messageType;
    private byte[] tpud;

    public ResponseMessage(String messageType,  byte[] tpud ) {
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
