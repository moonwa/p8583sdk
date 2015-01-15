package com.denovo.p8583;

import java.util.HashMap;

/**
 * Created by moonwa on 15-1-15.
 */
public class P8583PackFactory {
    HashMap<String, P8583PackBuilder> messageType2Builders = new HashMap<String, P8583PackBuilder>();
    public void register(String messageType, P8583PackBuilder packBuilder){
        this.messageType2Builders.put(messageType, packBuilder);
    }
    public P8583PackBuilder getBuilder(String messageType){
        return this.messageType2Builders.get(messageType);
    }
}
