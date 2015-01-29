package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/28.
 */
//退款时 返回交易历史
public class HistoryMoneyOrderInfoEntry {

    private String loginNum;
    private String isRequirePwd;
    private String passwd;
    private String flowType;
    private String startDate;
    private String endDate;
    private String pageSize;

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setIsRequirePwd(String isRequirePwd) {
        this.isRequirePwd = isRequirePwd;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getLoginNum() {

        return loginNum;
    }

    public String getIsRequirePwd() {
        return isRequirePwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getFlowType() {
        return flowType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPageSize() {
        return pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    private String pageNum;
    private String businessCode;
}
