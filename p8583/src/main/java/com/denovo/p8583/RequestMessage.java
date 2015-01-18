package com.denovo.p8583;

public class RequestMessage{
    private String messageType;
    private byte[] mac;

    public RequestMessage(String messageType, byte[] mac) {
        this.messageType = messageType;
        this.mac = mac;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public byte[] getMac() {
        return mac;
    }
}
