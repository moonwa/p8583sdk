package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/28.
 */
public class HistoryMoneyOrderInfoResultBody {
    private String orderNum;//订单编号
    private String payFlowNum;//支付流水号
    private String transactionNum;//交易编号

    private String mobileNum;//手机号码
    private String cardNum;//会员卡号
    private String transactionTime;//交易时间
    private String transactionAmount;//交易金额
    private String flowType;//交易类型
    private String channelType;//渠道类型
    private String merchantName;//交易商户
    private String description;//备注
    private String operatorName;//操作人

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

    public void setTransactionAmount(String transactionAmount) {
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

    public String getTransactionAmount() {
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

    private String isCancel;//是否已冲销
}
