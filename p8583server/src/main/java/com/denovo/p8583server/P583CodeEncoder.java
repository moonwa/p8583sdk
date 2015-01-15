package com.denovo.p8583server;

import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.P8583PackFactory;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P583CodeEncoder implements ProtocolEncoder {
    private P8583PackFactory packFactory;

    public P583CodeEncoder(P8583PackFactory packFactory) {
        this.packFactory = packFactory;
    }

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

    }

    public void dispose(IoSession session) throws Exception {

    }
}
