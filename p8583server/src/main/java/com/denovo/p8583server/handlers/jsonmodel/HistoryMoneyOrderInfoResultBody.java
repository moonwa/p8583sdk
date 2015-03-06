package com.denovo.p8583server.handlers.jsonmodel;

import java.util.Date;

/**
 * Created by Administrator on 2015/1/28.
 */
public class HistoryMoneyOrderInfoResultBody {
    private String orderNum;
    private String payFlowNum;
    private String transactionNum;

    private String mobileNum;
    private String cardNum;
    private String transactionTime;
    private double transactionAmount;
    private String flowType;
    private String channelType;
    private String merchantName;
    private String description;
    private String operatorName;

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public void setPayFlowNum(String payFlowNum) {
        this.payFlowNum = payFlowNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getIsCancel() {

        return isCancel;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public String getPayFlowNum() {
        return payFlowNum;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public String getFlowType() {
        return flowType;
    }

    public String getChannelType() {
        return channelType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getDescription() {
        return description;
    }

    public String getOperatorName() {
        return operatorName;
    }

    private String isCancel;
}
