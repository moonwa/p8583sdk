package com.denovo.p8583server;

import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.RequestMessage;
import com.denovo.p8583.RequestMessageBuilders;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.util.Arrays;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P583CodeDecoder extends CumulativeProtocolDecoder {
    private RequestMessageBuilders messageBuilders;

    public P583CodeDecoder(RequestMessageBuilders messageBuilders) {
        this.messageBuilders = messageBuilders;
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if(in.remaining() > 2){
            int len = in.getUnsignedShort();
            if(in.remaining() >= len){
                byte[] array = in.array();
                array = Arrays.copyOfRange(array, 2, len);
                RequestMessage requestMessage = messageBuilders.build(array);
                out.write(requestMessage);
            }else{
                in.position(0);
            }
        }
        return false;
    }

}
