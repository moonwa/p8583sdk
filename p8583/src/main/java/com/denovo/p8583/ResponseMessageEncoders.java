package com.denovo.p8583;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseMessageEncoders {
    HashMap<String, ResponseMessageParser> messageParsers = new HashMap<String, ResponseMessageParser>();

    public void register(String messageType, ResponseMessageParser messageParser) {
        this.messageParsers.put(messageType, messageParser);
    }
    public byte[] pack(ResponseMessage msg) throws Exception {
        String messageType = msg.getMessageType();

        ResponseMessageParser parser = getMessageParser(messageType);
        P8583Pack pack1 = parser.parse(msg);
        return pack(pack1);
    }

    public byte[] pack(P8583Pack pack ) {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        bytes.addAll(Encoder.toArray(pack.getTpud()));

        bytes.add((byte) 96);
        bytes.add((byte) 34);
        bytes.add((byte) 0);
        bytes.add((byte) 0);
        bytes.add((byte) 0);
        bytes.add((byte) 0);
        bytes.add((byte) Integer.parseInt(pack.getMessageType().substring(0, 2), 16));
        bytes.add((byte) Integer.parseInt(pack.getMessageType().substring(2, 4), 16));
        String[] bitMaps = new String[8];

        for(int i = 0; i < 64; i++){
            if(bitMaps[i/8] == null){
                bitMaps[i/8] = "";
            }
            bitMaps[i/8] += (pack.getHasValue(i+1) ? "1" : "0");
        }
        for(String bitBitmap : bitMaps){
            bytes.add((byte)(int)Integer.valueOf(bitBitmap, 2));
        }
        for(int i : pack.getFieldIndexs()){
            bytes.addAll(Encoder.toArray(pack.buildData(i)));
        }
        byte[] result = new byte[bytes.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = bytes.get(i);
        }


        return result;


    }

    public ResponseMessageParser getMessageParser(String messageType) {
        return this.messageParsers.get(messageType);
    }
}
