package com.denovo.p8583server.handlers;

/**
 * Created by 013495 on 2015/1/19.
 */
public class Result {
    // {"code":"0","msg":null,"result":null,"errorCode":null}

    private String code;
    private String msg;
    private String errorCode;
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
