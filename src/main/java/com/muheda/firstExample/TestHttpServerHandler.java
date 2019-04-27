package com.muheda.firstExample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * @desc 读取客户端发过来的请求并且将响应返回给客户端
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass());

        //获取到远程客户端的地址
        System.out.println(ctx.channel().remoteAddress());

        // 请求路由的处理封装
        if(msg instanceof HttpRequest){


            //将请求转成HttpRequest 对象
            HttpRequest httpRequest = (HttpRequest)msg;

            System.out.println("请求方法名：" + httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());
            System.out.println("请求的uri" + uri);

            //使用谷歌浏览器调用时候回默认传递这样一个请求用户获取网站的图标信息
            if("favicon.cio".equals(uri.getPath())){
                System.out.println("请求uri");
                return;
            }


            //构造出返回的对象 将字符串转成ByteBuffer
            ByteBuf content = Unpooled.copiedBuffer("HelloWorld", CharsetUtil.UTF_8);


            //直接进行响应

            //构造出相应对象
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,content);


            //设置响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");

            //消息体
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            //将响应写回去
            ctx.writeAndFlush(response);


        }

    }





    //关于客户端传递数据过来是这些方法的调用的顺序，需要我们自己来进行测试

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelActive。。。");
        super.channelActive(ctx);

    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelHandlerContext...'");
        super.channelInactive(ctx);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("channelRead...");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelRegister...");
        super.channelRegistered(ctx);
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelUnregisterd");
        super.channelUnregistered(ctx);
    }


    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

        System.out.println("channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }


}

