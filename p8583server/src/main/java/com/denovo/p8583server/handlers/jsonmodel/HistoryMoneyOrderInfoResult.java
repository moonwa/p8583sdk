package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/29.
 */
public class HistoryMoneyOrderInfoResult extends ResultBase {

    private  PayDetail result;

    public void setResult(PayDetail result) {
        this.result = result;
    }

    public PayDetail getResult() {
        return result;
    }
}

