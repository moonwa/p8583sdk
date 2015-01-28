package com.denovo.p8583server.responseMessages;

import com.denovo.p8583server.requestMessages.DefaultRequestMessage;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.ResponseMessage;

/**
 * Created by moonwa on 15-1-18.
 */
public class DefaultResponseMessage extends ResponseMessage {
    public DefaultResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
    private int result;

    private  String resultMsg;

    private String clientId;
    private String terminalId;
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public void setRequestMessage(RequestMessage requestMessage) {
        DefaultRequestMessage defaultRequestMessage = (DefaultRequestMessage) requestMessage;
        this.setClientId( defaultRequestMessage.getClientId());
        this.setTerminalId(defaultRequestMessage.getTerminalId());
    }
}
