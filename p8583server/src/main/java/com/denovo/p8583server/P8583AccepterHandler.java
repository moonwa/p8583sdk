package com.denovo.p8583server;

import com.denovo.p8583.*;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class P8583AccepterHandler extends IoHandlerAdapter
{
    private HandlerBuilders handlerBuilders;

    public P8583AccepterHandler(HandlerBuilders handlerBuilders) {
        this.handlerBuilders = handlerBuilders;
    }

    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }
    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        RequestMessage requestMessage= (RequestMessage) message;
        HandlerBuilder handlerBuilder = handlerBuilders.getHandler(requestMessage.getMessageType());
        MessageHandler handler = handlerBuilder.build(requestMessage);
        ResponseMessage responseMessage = handler.handle();
        session.write(responseMessage);
    }
    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        if(session.getIdleCount( status ) > 10){
            session.close(true);
        }
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}