package com.denovo.p8583server.requestMessages;

import com.denovo.p8583.RequestMessage;

public class DefaultRequestMessage extends RequestMessage {
    private String terminalId;
    private String clientId;
    public DefaultRequestMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
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
