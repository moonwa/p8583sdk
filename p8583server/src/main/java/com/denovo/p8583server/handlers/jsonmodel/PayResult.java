package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/29.
 */
public class PayResult {
     private String  flowCode;
      private    String giveAmount;

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public void setGiveAmount(String giveAmount) {
        this.giveAmount = giveAmount;
    }

    public String getGiveAmount() {

        return giveAmount;
    }
}
