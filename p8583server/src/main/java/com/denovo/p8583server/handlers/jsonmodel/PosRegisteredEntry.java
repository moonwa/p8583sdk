package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/22.
 */
public class PosRegisteredEntry {

    private String terminalCode;

    private String  macAddress;

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


