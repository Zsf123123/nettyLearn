package com.muheda.sxithexample;

import com.muheda.protobuf.DataInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {



    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setAddress("北京")
                .setId(10)
                .setName("张三")
                .build();

        Channel channel = ctx.channel();
        channel.writeAndFlush(student);


    }





}
