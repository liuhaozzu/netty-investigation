package com.liuhaozzu.netty.investigation.codec;

import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.util.concurrent.EventExecutorGroup;


/**
 * @Author Administrator
 * @create 2019/3/9 0009 23:38
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel> {

    private final MessageLite lite;

    public ProtoBufInitializer(MessageLite lite) {
        this.lite = lite;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());

        pipeline.addLast(new ProtobufEncoder());

        pipeline.addLast(new ProtobufDecoder(lite));

        pipeline.addLast(new ObjectHandler());
    }

    private static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        }
    }
}
