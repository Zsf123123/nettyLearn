package com.muheda.thridExample;

import com.muheda.secondExample.MyClientInitiialize;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChartClient {

    public static void main(String[] args) {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {


            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(null);

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();

            channelFuture.channel().writeAndFlush("hello");

            channelFuture.channel().closeFuture().sync();


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            eventLoopGroup.shutdownGracefully();
        }

    }


}
