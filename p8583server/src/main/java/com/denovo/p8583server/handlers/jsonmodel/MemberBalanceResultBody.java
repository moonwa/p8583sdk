package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/26.
 */
public class MemberBalanceResultBody  {
    private String basicBalance;//基本余额
    private String donateBalance;//赠送余额
    private String integral;//积分余额

    public void setDonateBalance(String donateBalance) {
        this.donateBalance = donateBalance;
    }

    public void setBasicBalance(String basicBalance) {
        this.basicBalance = basicBalance;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getBasicBalance() {
        return basicBalance;
    }

    public String getDonateBalance() {
        return donateBalance;
    }

    public String getIntegral() {
        return integral;
    }
}
