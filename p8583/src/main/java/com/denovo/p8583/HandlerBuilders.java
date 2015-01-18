package com.denovo.p8583;

import java.util.HashMap;

public class HandlerBuilders {
    public HashMap<String, HandlerBuilder> _builders = new HashMap<String, HandlerBuilder>();

    public HandlerBuilder getHandler(String messageType) {
        return _builders.get(messageType);
    }

    public void register(String messageType, HandlerBuilder handlerBuilder) {
        _builders.put(messageType, handlerBuilder);
    }
}
