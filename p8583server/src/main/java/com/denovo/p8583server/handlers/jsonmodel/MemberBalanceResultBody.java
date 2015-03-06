package com.denovo.p8583server.handlers.jsonmodel;

/**
 * Created by Administrator on 2015/1/26.
 */
public class MemberBalanceResultBody  {
    private String basicBalance;
    private String donateBalance;
    private String integral;

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
