package com.denovo.p8583server;

import com.denovo.p8583.ResponseMessageEncoders;
import com.denovo.p8583.HandlerBuilders;
import com.denovo.p8583.RequestMessageBuilders;
import com.denovo.p8583server.handlerbuilders.DealHandlerBuilder;
import com.denovo.p8583server.handlerbuilders.DealRollbackHandlerBuilder;
import com.denovo.p8583server.handlerbuilders.PosRegisteredHandlerBuilder;
import com.denovo.p8583server.handlerbuilders.SigninHandlerBuilder;
import com.denovo.p8583server.requestMessageBuilders.DealRequestMessageBuilder;
import com.denovo.p8583server.requestMessageBuilders.DealRollbackRequestMessageBuilder;
import com.denovo.p8583server.requestMessageBuilders.PosRegisteredRequestMessageBuilder;
import com.denovo.p8583server.requestMessageBuilders.SignInRequestMessageBuilder;
import com.denovo.p8583server.responseMessagesEncoders.DealResponseMessageEncoder;
import com.denovo.p8583server.responseMessagesEncoders.DealRollbackResponseMessageEncoder;
import com.denovo.p8583server.responseMessagesEncoders.PosRegisteredResponseMessageEncoder;
import com.denovo.p8583server.responseMessagesEncoders.SignInResponseMessageEncoder;
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

        // request message builders
        RequestMessageBuilders requestMessageBuilders = new RequestMessageBuilders();
        requestMessageBuilders.register("0800", new SignInRequestMessageBuilder());
        requestMessageBuilders.register("0900", new PosRegisteredRequestMessageBuilder());
        requestMessageBuilders.register("0200", new DealRequestMessageBuilder());
        requestMessageBuilders.register("0400", new DealRollbackRequestMessageBuilder());

        ResponseMessageEncoders responseMessageEncoders = new ResponseMessageEncoders();
        responseMessageEncoders.register("0810", new SignInResponseMessageEncoder());
        responseMessageEncoders.register("0910", new PosRegisteredResponseMessageEncoder());
        responseMessageEncoders.register("0210", new DealResponseMessageEncoder());
        responseMessageEncoders.register("0410", new DealRollbackResponseMessageEncoder());


        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new P8583CodecFactory(requestMessageBuilders, responseMessageEncoders)));

        // handler builders
        HandlerBuilders handlerBuilders = new HandlerBuilders();
        handlerBuilders.register("0800", new SigninHandlerBuilder());//签到
        handlerBuilders.register("0900",new PosRegisteredHandlerBuilder());//pos机注册
        handlerBuilders.register("0200",new DealHandlerBuilder());//交易
        handlerBuilders.register("0400",new DealRollbackHandlerBuilder());//冲正
       // 0820  签退

        acceptor.setHandler(new P8583AccepterHandler(handlerBuilders) );

        acceptor.getSessionConfig().setReadBufferSize(4096);
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 1 );
        try {
            acceptor.bind( new InetSocketAddress(3130) );
            System.out.println("started.");
            System.in.read();
            System.out.println("stoped.");
            acceptor.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}