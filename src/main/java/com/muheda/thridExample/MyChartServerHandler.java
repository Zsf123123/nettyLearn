package com.muheda.thridExample;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChartServerHandler  extends SimpleChannelInboundHandler<String> {


    // 维护所有的channel
    private static  ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        //每次接收到客户端的消息的时候，将消息发送给每个客户端，告诉其他的客户端这个消息是谁发送的。告诉发送这个消息的客户端这个消息是自己发送的

        //需要将自己的channel给剔除出来

        channelGroup.forEach(ch -> {

            if(ch != channel){

                channelGroup.writeAndFlush( msg + "----" + "this messge from " + channel.remoteAddress() + "\n");

            }else {

                channelGroup.writeAndFlush( msg + "----" + "this messge from  yourself \n" );

            }

        });




    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + "加入\n");

        channelGroup.add(channel);


    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channel.writeAndFlush("【服务器】" + channel.remoteAddress() + "离开\n");

        //断开连接起始已经回从channelGroup 中移除
        //netty 回自动调用该连接
        channelGroup.remove(channel);


    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress() + "上线'");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }

}


