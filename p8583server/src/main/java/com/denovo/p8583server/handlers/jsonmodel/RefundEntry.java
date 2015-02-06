package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/22.
 */

//退款
public class RefundEntry {
    private String flowCode;
    private String terminalFlowNum;
    private String amount;
    private String loginNum;
    private String channel;

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public void setTerminalFlowNum(String terminalFlowNum) {
        this.terminalFlowNum = terminalFlowNum;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setPosTerminalCode(String posTerminalCode) {
        this.posTerminalCode = posTerminalCode;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public void setOrderFormNum(String orderFormNum) {
        this.orderFormNum = orderFormNum;
    }

    private String Operator;

    public String getFlowCode() {
        return flowCode;
    }

    public String getTerminalFlowNum() {
        return terminalFlowNum;
    }

    public String getAmount() {
        return amount;
    }

    public String getChannel() {
        return channel;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public String getOperator() {
        return Operator;
    }

    public String getPosTerminalCode() {
        return posTerminalCode;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public String getOrderFormNum() {
        return orderFormNum;
    }

    private String posTerminalCode;
    private String passwd;
    private String businessCode;
    private String orderFormNum;
}
