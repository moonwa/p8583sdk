package com.denovo.p8583server;

import com.denovo.p8583.RequestMessageBuilders;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P8583CodecFactory implements ProtocolCodecFactory {

    private RequestMessageBuilders packFactory;
    private P583CodeEncoder encoder;
    private P583CodeDecoder decoder;
    public P8583CodecFactory(RequestMessageBuilders packFactory){

        this.packFactory = packFactory;
        encoder =new P583CodeEncoder(packFactory);
        decoder =new P583CodeDecoder(packFactory);
    }
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
