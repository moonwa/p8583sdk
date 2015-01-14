package com.denovo.p8583server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P583CodeDecoder extends CumulativeProtocolDecoder {
    public P583CodeDecoder(IoSession session) {
    }

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
//
//        if(in.remaining() > 4){
//            int len = in.getInt();
//            if(in.remaining() > len){
//                in.array()
//            }else{
//                in.position(0);
//            }
//        }
        return false;
    }

}
