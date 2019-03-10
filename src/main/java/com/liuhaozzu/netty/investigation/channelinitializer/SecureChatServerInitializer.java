package com.liuhaozzu.netty.investigation.channelinitializer;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * @Author Administrator
 * @create 2019/3/10 0010 9:01
 */
public class SecureChatServerInitializer extends ChatServerInitializer {

    private final SSLContext sslContext;

    public SecureChatServerInitializer(ChannelGroup group, SSLContext sslContext) {
        super(group);
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        SSLEngine engine = sslContext.createSSLEngine();
        engine.setUseClientMode(false);
        ch.pipeline().addFirst(new SslHandler(engine));

    }
}
