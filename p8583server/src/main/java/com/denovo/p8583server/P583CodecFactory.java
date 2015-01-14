package com.denovo.p8583server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P583CodecFactory implements ProtocolCodecFactory {
    public P583CodecFactory() {
    }

    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new P583CodeEncoder(session);
    }

    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return new P583CodeDecoder(session);
    }
}
