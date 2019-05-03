package com.muheda.sxithexample;

import com.muheda.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {


        System.out.println(msg.getId());
        System.out.println(msg.getName());
        System.out.println(msg.getAddress());

    }


}
