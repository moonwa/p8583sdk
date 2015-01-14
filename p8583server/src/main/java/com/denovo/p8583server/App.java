package com.denovo.p8583server;

import org.apache.commons.codec.binary.Hex;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.IoBufferWrapper;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class App{
    public static void main(String[] args) {

        IoBuffer buffer = IoBuffer.allocate(1024);


        System.out.println("===================================");
        buffer.putInt(12);
        buffer.position(0);
        System.out.println(buffer.position());
        buffer.capacity(4);
        byte[] bytes = buffer.array();
        System.out.println(Hex.encodeHexString(bytes));
//        IoAcceptor acceptor = new NioSocketAcceptor();
//        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
//        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new P583CodecFactory()));
//        acceptor.setHandler(  new TimeServerHandler() );
//
//        acceptor.getSessionConfig().setReadBufferSize(2048);
//        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 1 );
//        try {
//            acceptor.bind( new InetSocketAddress(3130) );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}