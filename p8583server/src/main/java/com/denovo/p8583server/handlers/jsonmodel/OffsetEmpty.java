package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/29.
 */
public class OffsetEmpty {
    private String oldTerminalFlowNum;
    private String terminalFlowNum;
    private String channel;
    private String operator;

    public void setOldTerminalFlowNum(String oldTerminalFlowNum) {
        this.oldTerminalFlowNum = oldTerminalFlowNum;
    }

    public void setTerminalFlowNum(String terminalFlowNum) {
        this.terminalFlowNum = terminalFlowNum;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setOperator(String operator) {
        operator = operator;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    private String businessCode;

    public String getOldTerminalFlowNum() {
        return oldTerminalFlowNum;
    }

    public String getTerminalFlowNum() {
        return terminalFlowNum;
    }

    public String getChannel() {
        return channel;
    }

    public String getOperator() {
        return operator;
    }

    public String getBusinessCode() {
        return businessCode;
    }
}
