package com.denovo.p8583;

import java.util.ArrayList;

/**
 * Created by moonwa on 15-1-14.
 */
public abstract class P8583PackBuilder {
    public abstract void Init(P8583Fields fields);

    public P8583Pack build(String messageType) {
        ArrayList<P8583Field> p8583Fields = new ArrayList<P8583Field>();
        for(int i = 1; i <= 64; i++){
            p8583Fields.add(new EmptyP8583Field());
        }
        P8583Fields fields = new P8583Fields (p8583Fields);
        this.Init(fields);
        P8583Pack p8583Pack = new P8583Pack(messageType, fields.getFields());
        return p8583Pack;
    }
}
