package com.denovo.p8583server.handlerbuilders;

import com.denovo.p8583.*;
import com.denovo.p8583server.handlers.PosRegisteredHandler;
import com.denovo.p8583server.requestMessages.PosRegisteredRequestMessage;

/**
 * Created by Administrator on 2015/1/21.
 */
public class PosRegisteredHandlerBuilder implements HandlerBuilder {

    public MessageHandler build(RequestMessage requestMessage) {
        return new PosRegisteredHandler(( PosRegisteredRequestMessage)requestMessage);
    }
}
