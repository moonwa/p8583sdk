package com.denovo.p8583;

public class DefaultRequestMessage extends RequestMessage{
    private String terminalId;
    private String clientId;
    public DefaultRequestMessage(String messageType, byte[] tpud, byte[] mac) {
        super(messageType, tpud, mac);
    }

    /*
        get terminal name
     */
    public String getTerminalId() {
        return terminalId;
    }

    /*
        set terminal name
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /*
        get shop name
     */
    public String getClientId() {
        return clientId;
    }
    /*
        set shop name
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
