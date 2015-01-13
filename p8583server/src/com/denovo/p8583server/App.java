package com.denovo.p8583server;
public class App{
//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(ScheduledTasks.class);
//    }
    public static void main(String[] args) {
        IoAcceptor acceptor = new NioSocketAcceptor();
    }
}