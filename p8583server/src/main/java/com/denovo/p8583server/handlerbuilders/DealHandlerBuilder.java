package com.denovo.p8583server.handlerbuilders;

import com.denovo.p8583.HandlerBuilder;
import com.denovo.p8583.MessageHandler;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583server.handlers.DealHandler;
import com.denovo.p8583server.requestMessages.DealRequestMessage;

/**
 * Created by Administrator on 2015/1/26.
 */
public class DealHandlerBuilder implements HandlerBuilder {

    public MessageHandler build(RequestMessage requestMessage) {
        return new DealHandler((DealRequestMessage)requestMessage);
    }
}
