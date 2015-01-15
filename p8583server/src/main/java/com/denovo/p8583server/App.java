package com.denovo.p8583server;

import com.denovo.p8583.DefaultP8583PackBuilder;
import com.denovo.p8583.Encoder;
import com.denovo.p8583.P8583PackFactory;
import org.apache.commons.codec.binary.Hex;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App{
    public static void main(String[] args) {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        P8583PackFactory packFactory = new P8583PackFactory();
        packFactory.register("0800",new DefaultP8583PackBuilder());
        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new P8583CodecFactory(packFactory)));
        acceptor.setHandler(  new TimeServerHandler() );

        acceptor.getSessionConfig().setReadBufferSize(4096);
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 1 );
        try {
            acceptor.bind( new InetSocketAddress(3130) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}