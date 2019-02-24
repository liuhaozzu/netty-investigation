package com.liuhaozzu.netty.investigation.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * @Author Administrator
 * @create 2019/2/24 0024 12:01
 */
public class EchoClientHandler  extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 连接建立时，被调用
        System.out.println("channelActive");
        ctx.writeAndFlush(Unpooled.copiedBuffer("netty rocks!", StandardCharsets.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        //每当接收数据时，都会调用这个方法
        //由服务器发送的消息可能会被分块接收
        //该方法返回时，父类会负责释放指向保存该消息的ByteBuf的内存引用
        System.out.println("Client received:" + msg.toString(StandardCharsets.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
