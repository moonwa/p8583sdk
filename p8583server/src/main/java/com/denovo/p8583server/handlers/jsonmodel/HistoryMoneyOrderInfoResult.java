package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/29.
 */
public class HistoryMoneyOrderInfoResult extends ResultBase {

    private  PayResult result;

    public void setResult(PayResult result) {
        this.result = result;
    }

    public PayResult getResult() {
        return result;
    }
}

