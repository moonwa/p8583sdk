package com.denovo.p8583server.responseMessages;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackResponseMessage extends DefaultResponseMessage {

    public DealRollbackResponseMessage(String messageType, byte[] tpud) {
        super(messageType, tpud);
    }
}
