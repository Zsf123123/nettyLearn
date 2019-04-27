package com.muheda.fifthExample;

import com.muheda.thridExample.MyChartServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class MyServer {


    public static void main(String[] args) {


        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup workerGoup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGoup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new WebSockerChanneInitializer());



            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
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

