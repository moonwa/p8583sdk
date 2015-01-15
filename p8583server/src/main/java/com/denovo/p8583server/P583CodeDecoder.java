package com.denovo.p8583server;

import com.denovo.p8583.DefaultP8583PackBuilder;
import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.P8583PackFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.util.Arrays;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P583CodeDecoder extends CumulativeProtocolDecoder {
    private P8583PackFactory packFactory;

    public P583CodeDecoder(P8583PackFactory packFactory) {
        this.packFactory = packFactory;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if(in.remaining() > 2){
            int len = in.getUnsignedShort();
            if(in.remaining() >= len){
                byte[] array = in.array();
                array = Arrays.copyOfRange(array, 2, len);
                P8583Pack pack = P8583Pack.pack(array, this.packFactory);
                out.write(pack);
            }else{
                in.position(0);
            }
        }
        return false;
    }

}
