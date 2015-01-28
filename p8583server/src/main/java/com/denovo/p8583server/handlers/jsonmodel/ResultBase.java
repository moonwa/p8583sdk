package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/26.
 */
public class ResultBase {

    private String code;
    private String msg;
    private String errorCode;


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


}
