package com.liuhaozzu.netty.investigation.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * @Author Administrator
 * @create 2019/2/23 0023 22:35
 */
public class EchoServer {
    private final int port;

    public EchoServer() {
        //do nothing
        this.port=8888;
    }

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        /*if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " ");
        }*/
        //int port = Integer.parseInt(args[0]);




        int port=8888;
        new EchoServer(port).start();
    }

    private void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        final AttributeKey<Integer> id = AttributeKey.newInstance("ID");
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            //EchoServerHandler被标记为Shareable，所以我们可以总是是用同样的实例
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //异步地绑定服务器，调用sync方法阻塞，等待直到绑定完成
            ChannelFuture f=b.bind().sync();
            //获取Channel的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
