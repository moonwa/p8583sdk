package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/29.
 */
public class ChargeEntry {

    private String orderFormNum;
    private String terminalFlowNum;
    private String amount;
    private String payWayCode;
    private String payWayName;
    private String accountType;
    private String loginNum;
    private String channel;
    private String posTerminalCode;

    public void setOrderFormNum(String orderFormNum) {
        this.orderFormNum = orderFormNum;
    }

    public void setTerminalFlowNum(String terminalFlowNum) {
        this.terminalFlowNum = terminalFlowNum;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
        this.operator = operator;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getOrderFormNum() {
        return orderFormNum;
    }

    public String getTerminalFlowNum() {
        return terminalFlowNum;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public String getAmount() {
        return amount;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getChannel() {
        return channel;
    }

    public String getPosTerminalCode() {
        return posTerminalCode;
    }

    public String getOperator() {
        return operator;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    private String operator;
    private String businessCode;
}
