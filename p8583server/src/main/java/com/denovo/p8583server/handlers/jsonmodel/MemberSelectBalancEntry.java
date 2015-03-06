package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/22.
 */

public class MemberSelectBalancEntry {

    private String loginNum;
    private String isRequirePwd;
    private String passwd;
    private String businessCode;

    public  String getLoginNum(){

        return  loginNum;
    }

    public  void setLoginNum(String loginNum){
        this.loginNum=loginNum;
    }

    public  String getIsRequirePwd(){

        return  isRequirePwd;
    }

    public  void setIsRequirePwd(String isRequirePwd){
        this.isRequirePwd=isRequirePwd;
    }

    public  String getPasswd(){

        return  passwd;
    }

    public  void setPasswd(String passwd){
        this.passwd=passwd;
    }
    public  String getBusinessCode(){

        return  businessCode;
    }

    public  void setBusinessCode(String businessCode){
        this.businessCode=businessCode;
    }

}
