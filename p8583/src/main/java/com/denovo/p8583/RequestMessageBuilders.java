package com.denovo.p8583;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class RequestMessageBuilders {
    HashMap<String, RequestMessageBuilder> messageType2Builders = new HashMap<String, RequestMessageBuilder>();

    public void register(String messageType, RequestMessageBuilder packBuilder) {
        this.messageType2Builders.put(messageType, packBuilder);
    }
    public P8583Pack pack(byte[] bytes) throws Exception {
        String messageType = StringUtils.leftPad(Integer.toHexString(bytes[11]), 2, '0') + StringUtils.leftPad(Integer.toHexString(bytes[12]), 2, '0');

        RequestMessageBuilder builder = getBuilder(messageType);
        return builder.pack(bytes, messageType);
    }
    public RequestMessage build(byte[] bytes) throws Exception {
      //消息类型 0800 是签到
        String messageType = StringUtils.leftPad(Integer.toHexString(bytes[11]), 2, '0') + StringUtils.leftPad(Integer.toHexString(bytes[12]), 2, '0');
         RequestMessageBuilder builder = getBuilder(messageType);
        return builder.build(builder.pack(bytes, messageType));
    }
    public RequestMessageBuilder getBuilder(String messageType) {
        return this.messageType2Builders.get(messageType);
    }
}
