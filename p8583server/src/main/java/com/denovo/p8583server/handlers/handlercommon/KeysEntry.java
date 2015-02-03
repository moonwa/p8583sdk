package com.denovo.p8583server.handlers.handlercommon;

/**
 * Created by Administrator on 2015/1/30.
 */
public class KeysEntry {
    private String key1;
    private String key2;
    private String shopName;

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getPosName() {
        return posName;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getShopName() {
        return shopName;
    }

    private String posName;
}
