package com.muheda.thridExample;

import com.muheda.secondExample.MyServerInitiialize;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @desc 客户端与服务端进行连接
 *        实现广播消息的发送
 */
public class MyChartServer {


    public static void main(String[] args) {


        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup workerGoup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGoup).channel(NioServerSocketChannel.class).
                    childHandler( new MyChartServerInitializer() );



            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            System.out.println("服务端连接异常");
            e.printStackTrace();

        } finally {
            bossGroup.shutdownGracefully();
            workerGoup.shutdownGracefully();

        }

    }



}
