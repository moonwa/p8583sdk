package com.denovo.p8583;

import com.denovo.p8583.fields.EmptyP8583Field;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by moonwa on 15-1-14.
 */
public abstract class RequestMessageBuilder {
    public abstract void Init(P8583Fields fields);


    protected RequestMessage pack(byte[] bytes, String messageType) throws Exception {
        byte[] tpud = Arrays.copyOfRange(bytes, 0, 5);

        String bitBitmap = "";
        for (int i = 13; i < 21; i++) {
            bitBitmap += StringUtils.leftPad(Integer.toBinaryString(bytes[i]), Integer.SIZE, '0').substring(Integer.SIZE - Byte.SIZE);
        }
        ArrayList<P8583Field> p8583Fields = new ArrayList<P8583Field>();
        for (int i = 1; i <= 64; i++) {
            p8583Fields.add(new EmptyP8583Field());
        }
        P8583Fields fields = new P8583Fields(p8583Fields);
        this.Init(fields);
        P8583Pack pack = new P8583Pack(messageType, fields.getFields());
        int ptr = 21;
        char[] flags = bitBitmap.toCharArray();
        for (int i = 0; i < flags.length; i++) {
            char flag = flags[i];
            if (flag == '1') {
                ptr += pack.writeData(i + 1, bytes, ptr);
                int g = i;
            }
        }

        P8583Pack p8583Pack = new P8583Pack(messageType, fields.getFields());
        return createRequestMessage(p8583Pack);
    }

    protected byte[] getMac(P8583Pack p8583Pack) {
        return p8583Pack.getByteArray(64);
    }

    protected abstract RequestMessage createRequestMessage(P8583Pack p8583Pack) throws Exception;
}
