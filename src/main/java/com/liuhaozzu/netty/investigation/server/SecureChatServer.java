package com.liuhaozzu.netty.investigation.server;

import com.liuhaozzu.netty.investigation.channelinitializer.ChatServerInitializer;
import com.liuhaozzu.netty.investigation.channelinitializer.SecureChatServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.concurrent.ImmediateEventExecutor;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

/**
 * @Author Administrator
 * @create 2019/3/10 0010 9:10
 */
public class SecureChatServer extends ChatServer {
    private final SSLContext sslContext;

    public SecureChatServer(SSLContext context) {
        this.sslContext = context;
    }

    @Override
    protected ChannelHandler createInitializer(ChannelGroup channelGroup) {
        return new SecureChatServerInitializer(channelGroup, sslContext);
    }

    public static void main(String[] args) throws Exception {
        int port =8888;
        SelfSignedCertificate cert = new SelfSignedCertificate();

        SSLContext sslContext = SSLContext.getInstance(cert.certificate().toString(), cert.privateKey().toString());

        final SecureChatServer endpoint = new SecureChatServer(sslContext);
        ChannelFuture future = endpoint.start(new InetSocketAddress(port));

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            endpoint.destroy();
        }));
    }
}
