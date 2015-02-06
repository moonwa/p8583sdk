package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/26.
 */
public class MemberBalanceResult extends ResultBase {

    private  MemberBalanceResultBody result;

    public void setResult(MemberBalanceResultBody result) {
        this.result = result;
    }

    public MemberBalanceResultBody getResult() {
        return result;
    }
}
