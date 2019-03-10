package com.liuhaozzu.netty.investigation.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.concurrent.Future;

import java.net.InetSocketAddress;

/**
 * @Author Administrator
 * @create 2019/3/9 0009 21:23
 */
public class DatagramChannelClient {
    public void serve(int port) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new OioEventLoopGroup())
                .channel(OioDatagramChannel.class)
                .handler(new SimpleChannelInboundHandler<DatagramPacket>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
                        System.out.println("datagram:" + msg.toString());
                    }
                });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(0));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("channel bound");
                } else {
                    System.err.println("Bind attemp failed");
                    future.cause().printStackTrace();
                    Future shutdownFuture = future.channel().eventLoop().shutdownGracefully();
                    shutdownFuture.syncUninterruptibly();
                }
            }
        });

    }
}
