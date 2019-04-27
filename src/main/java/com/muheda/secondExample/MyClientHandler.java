package com.muheda.secondExample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


        System.out.println(ctx.channel().remoteAddress());
        Channel channel = ctx.channel();

        channel.writeAndFlush(msg);

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().writeAndFlush("from client" );

    }
}
