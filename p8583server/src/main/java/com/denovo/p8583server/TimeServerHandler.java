package com.denovo.p8583server;

import com.denovo.p8583.P8583Pack;
import com.denovo.p8583.P8583PackFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

public class TimeServerHandler extends IoHandlerAdapter
{
    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }
    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        String str = message.toString();
        System.out.println("my message...");
        System.out.println(str);
        if( str.trim().equalsIgnoreCase("quit") ) {
            session.close();
            return;
        }
        Date date = new Date();
        session.write( date.toString() );
        System.out.println("Message written...");
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