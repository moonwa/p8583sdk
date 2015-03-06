package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/28.
 */

public class ConsumeEntry {

        private String orderFormNum;

        public void setOrderFormNum(String orderFormNum) {
            this.orderFormNum = orderFormNum;
        }

        public void setTerminalFlowNum(String terminalFlowNum) {
            this.terminalFlowNum = terminalFlowNum;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setIntegralAmount(String integralAmount) {
            this.integralAmount = integralAmount;
        }

        public void setIntegralMultiple(String integralMultiple) {
            this.integralMultiple = integralMultiple;
        }

        public void setLoginNum(String loginNum) {
            this.loginNum = loginNum;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
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

            return null;
        }

        public String getTerminalFlowNum() {
            return terminalFlowNum;
        }

        public String getAmount() {
            return amount;
        }

        public String getIntegralAmount() {
            return integralAmount;
        }

        public String getIntegralMultiple() {
            return null;
        }

        public String getLoginNum() {
            return loginNum;
        }

        public String getPasswd() {
            return passwd;
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

        private String terminalFlowNum;
        private String amount;
        private String integralAmount;
        private String integralMultiple;
        private String loginNum;
        private String passwd;
        private String channel;
        private String posTerminalCode;

        public String operator;
        private String businessCode;
    }


