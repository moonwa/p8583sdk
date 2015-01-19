package com.denovo.p8583server;

import com.denovo.p8583.ResponseMessageEncoders;
import com.denovo.p8583.HandlerBuilders;
import com.denovo.p8583.RequestMessageBuilders;
import com.denovo.p8583server.handlerbuilders.SigninHandlerBuilder;
import com.denovo.p8583server.memweb.IMemberWebService;
import com.denovo.p8583server.memweb.IMemberWebServiceService;
import com.denovo.p8583server.requestMessageBuilders.SignInRequestMessageBuilder;
import com.denovo.p8583server.responseMessagesEncoders.SignInResponseMessageParser;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App{
    public static void main(String[] args) {
//        IoAcceptor acceptor = new NioSocketAcceptor();
//        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
//
//        // request message builders
//        RequestMessageBuilders requestMessageBuilders = new RequestMessageBuilders();
//        requestMessageBuilders.register("0800", new SignInRequestMessageBuilder());
//
//        ResponseMessageEncoders responseMessageEncoders = new ResponseMessageEncoders();
//        responseMessageEncoders.register("0810", new SignInResponseMessageParser());
//        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new P8583CodecFactory(requestMessageBuilders, responseMessageEncoders)));
//
//        // handler builders
//        HandlerBuilders handlerBuilders = new HandlerBuilders();
//        handlerBuilders.register("0800", new SigninHandlerBuilder());
//
//        acceptor.setHandler(new P8583AccepterHandler(handlerBuilders) );
//
//        acceptor.getSessionConfig().setReadBufferSize(4096);
//        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 1 );
//        try {
//            acceptor.bind( new InetSocketAddress(3130) );
//            System.out.println("started.");
//            System.in.read();
//            System.out.println("stoped.");
//            acceptor.dispose();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}