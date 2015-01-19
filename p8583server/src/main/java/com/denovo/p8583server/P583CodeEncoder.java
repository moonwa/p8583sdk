package com.denovo.p8583server;

import com.denovo.p8583.ResponseMessage;
import com.denovo.p8583.ResponseMessageEncoders;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by 013495 on 2015/1/14.
 */
public class P583CodeEncoder implements ProtocolEncoder {
    private ResponseMessageEncoders responseMessageEncoders;

    public P583CodeEncoder(ResponseMessageEncoders responseMessageEncoders) {
        this.responseMessageEncoders = responseMessageEncoders;
    }

    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        ResponseMessage msg = (ResponseMessage) message;

        byte[] bytes = responseMessageEncoders.pack(msg);
        IoBuffer buffer = IoBuffer.allocate(2 + bytes.length);
        buffer.putUnsigned((byte) bytes.length / 256);
        buffer.putUnsigned((byte) bytes.length % 256);
        buffer.put(bytes);
        buffer.flip();
        out.write(buffer);
    }

    public void dispose(IoSession session) throws Exception {

    }
}
