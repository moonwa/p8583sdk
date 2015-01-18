package com.denovo.p8583;

import com.denovo.p8583.fields.*;

import java.util.ArrayList;

public abstract class ResponseMessageParser {
    public abstract void Init(P8583Fields fields);
    private void addToArray(ArrayList<Byte> list, byte[] bytes){
        for(byte b : bytes){
            list.add(b);
        }
    }
    public P8583Pack parse(ResponseMessage msg) throws Exception {
        ArrayList<P8583Field> p8583Fields = new ArrayList<P8583Field>();
        for (int i = 1; i <= 64; i++) {
            p8583Fields.add(new EmptyP8583Field());
        }
        P8583Fields fields = new P8583Fields(p8583Fields);
        this.Init(fields);

        P8583Pack pack = new P8583Pack(msg.getTpud(), msg.getMessageType(), fields.getFields());
        this.update(msg, pack);

        return pack;
    }

    protected abstract void update(ResponseMessage msg, P8583Pack pack) throws Exception;
}

