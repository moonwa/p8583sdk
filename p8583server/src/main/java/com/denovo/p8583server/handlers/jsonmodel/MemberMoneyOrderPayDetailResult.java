package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/2/7.
 */
public class MemberMoneyOrderPayDetailResult extends ResultBase{
    private  PayDetail result;

    public void setResult(PayDetail result) {
        this.result = result;
    }

    public PayDetail getResult() {
        return result;
    }

}
