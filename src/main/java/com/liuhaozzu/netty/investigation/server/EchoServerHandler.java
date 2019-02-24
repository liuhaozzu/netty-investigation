package com.liuhaozzu.netty.investigation.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author Administrator
 * @create 2019/2/23 0023 22:35
 *
 * Sharable 标识一个ChannelHandler可以被多个Channel共享
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //仍然需要将传入消息回送给发送者，而write()操作是异步的，直到channelRead返回后，可能仍然没有完成，不能立即释放msg对象
        ByteBuf in = (ByteBuf) msg;
        System.out.println("channelRead, Server received: " + in.toString(CharsetUtil.UTF_8));
        //将接收到的消息写给发送者，而不flush出站消息
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);//将未决消息冲刷到远程结点，并且关闭该Channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
