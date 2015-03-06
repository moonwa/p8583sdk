package com.denovo.p8583server.handlers.jsonmodel;


public class SignInEntry{

        private String accountName;

        private String passwd ;

        private String terminalCode;

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
