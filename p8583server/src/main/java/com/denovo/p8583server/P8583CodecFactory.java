package com.denovo.p8583server;

import com.denovo.p8583.P8583PackFactory;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P8583CodecFactory implements ProtocolCodecFactory {

    private P8583PackFactory packFactory;
    private P583CodeEncoder encoder;
    private P583CodeDecoder decoder;
    public P8583CodecFactory(P8583PackFactory packFactory){

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
