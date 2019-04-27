package com.muheda.firstExample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitiialize  extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel ch) throws Exception {


        //管道，管道中有很多的channelHandler,类似于很多的拦截器
        ChannelPipeline pipeline = ch.pipeline();

        // 这里使用的是回调的函数，将消息传递给我们自己已经定义好的处理方法，进行一步步的处理

        // 编解码用的
        pipeline.addLast("httpServerCodec",new HttpServerCodec());

        //自定义的对消息处理
        pipeline.addLast("testServerHttpHandler", new TestHttpServerHandler());


    }
}


