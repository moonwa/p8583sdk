package com.denovo.p8583;

public class DefaultRequestMessage extends RequestMessage{
    private String terminalNo;
    private String clientNo;
    public DefaultRequestMessage(String messageType, byte[] mac) {
        super(messageType, mac);
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }
}
