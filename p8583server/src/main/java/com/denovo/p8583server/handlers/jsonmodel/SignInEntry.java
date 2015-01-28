package com.denovo.p8583server.handlers.jsonmodel;

//签到 跟 签退 共用
public class SignInEntry{
       //登录名
        private String accountName;
       //密码
        private String passwd ;
      //终端号
        private String terminalCode;
      //商户号
        private String businessCode;

    public String getAccountName(){

        return accountName;
    }
    public void setAccountName(String accountName){

        this.accountName = accountName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
