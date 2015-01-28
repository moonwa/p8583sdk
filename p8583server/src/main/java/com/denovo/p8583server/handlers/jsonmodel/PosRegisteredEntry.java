package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/22.
 */
public class PosRegisteredEntry {
    //必填，pos终端编码
    private String terminalCode;
    //必填,机身码
    private String  macAddress;
    //必填，商户编码
    private String businessCode;

    public  String getTerminalCode(){

        return  terminalCode;
    }
    public  void  setTerminalCode(String terminalCode){
        this.terminalCode=terminalCode;
    }

    public  String getMacAddress(){

        return  macAddress;
    }
    public  void  setMacAddress(String macAddress){
        this.macAddress=macAddress;
    }
    public  String getBusinessCode(){

        return  businessCode;
    }

    public  void  setBusinessCode(String businessCode){
        this.businessCode=businessCode;
    }
}


