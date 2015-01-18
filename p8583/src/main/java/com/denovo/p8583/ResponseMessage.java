package com.denovo.p8583;

public class ResponseMessage{
    private String messageType;

    public ResponseMessage(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return this.messageType;
    }
}
