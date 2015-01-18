package com.denovo.p8583.requestMessages;

import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;

/**
 * Created by moonwa on 15-1-18.
 */
public class SignInRequestMessage extends RequestMessage {
    private String userName;

    public SignInRequestMessage(String messageType, byte[] mac) {
        super(messageType, mac);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
