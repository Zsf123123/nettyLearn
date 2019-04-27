package com.muheda.fourhExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {

    public static void main(String[] args) throws  Exception {


        //使用2个线程组进行处理，这2个线程组相当于死循环。服务器端一直接受来自客户端的连接
        // boss相当于是接受来自客户端的请求，接收完成之后分配给下面的连接组
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            // 用于启动服务端的类
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            // 先进行注册线程组，即用于处理接收，和响应处理逻辑的线程。并用过反射进行创建channel对象
            // 每个请求来的时候都会创建一个channel，每次进行数据接收的时候，将数据传递给已经定义好了的
            // 方法，对数据进行相应的处理


            ServerBootstrap group = serverBootstrap.group(boss, worker);
            ServerBootstrap server = group.channel(NioServerSocketChannel.class);


            server.handler(new LoggingHandler(LogLevel.INFO))
                   .childHandler(new ChannelInitializer<SocketChannel>() {

                protected void initChannel(SocketChannel ch) throws Exception {

                    ChannelPipeline pipeline = ch.pipeline();

                    // 读写空闲handler
                    pipeline.addLast(new IdleStateHandler(1,2,3, TimeUnit.SECONDS));

                    pipeline.addLast(new MyServerHandler());
                }

            });


            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();

        }finally {

            boss.shutdownGracefully();
            worker.shutdownGracefully();

        }

    }

}
