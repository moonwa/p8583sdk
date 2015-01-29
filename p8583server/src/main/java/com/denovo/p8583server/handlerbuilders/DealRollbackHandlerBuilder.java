package com.denovo.p8583server.handlerbuilders;

import com.denovo.p8583.HandlerBuilder;
import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583server.handlers.DealRollbackHandler;
import com.denovo.p8583server.requestMessages.DealRollbackRequestMessage;

/**
 * Created by Administrator on 2015/1/28.
 */
public class DealRollbackHandlerBuilder implements HandlerBuilder {

    public MessageHandler build(RequestMessage requestMessage) {
        return new DealRollbackHandler((DealRollbackRequestMessage)requestMessage);
    }
}
