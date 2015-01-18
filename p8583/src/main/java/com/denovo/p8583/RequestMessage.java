package com.denovo.p8583;

public class RequestMessage{
    private String messageType;
    private byte[] tpud;
    private byte[] mac;

    public RequestMessage(String messageType, byte[] tpud, byte[] mac) {
        this.messageType = messageType;
        this.tpud = tpud;
        this.mac = mac;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public byte[] getMac() {
        return mac;
    }

    public byte[] getTpud() {
        return tpud;
    }
}
