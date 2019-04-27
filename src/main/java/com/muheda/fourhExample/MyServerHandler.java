package com.muheda.fourhExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {




    @Override
    /**
     * @desc  用户事件触发  由于在之前添加了IdleStateHandler，所以当上一个handler有事件发生的时候会将事件信息发送到下一个handler中进行处理操作
     *        在我们自定义handler 中添加事件处理方法对相关事件进行恰当的处理
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if(evt instanceof IdleStateEvent){

            IdleStateEvent event = (IdleStateEvent) evt;

            String eventType =  null;

            switch (event.state()){

                case READER_IDLE:
                    eventType = "读空闲";
                    break;

                case ALL_IDLE:
                    eventType = "写空闲";
                    break;

                case WRITER_IDLE:
                    eventType = "读写空闲";
                    break;

            }


            System.out.println(eventType);
        }


    }
}
