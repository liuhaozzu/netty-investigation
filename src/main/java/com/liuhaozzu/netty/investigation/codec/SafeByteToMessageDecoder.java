package com.liuhaozzu.netty.investigation.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @Author Administrator
 * @create 2019/3/9 0009 22:42
 */
public class SafeByteToMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() > 1024) {
            in.skipBytes(1024);
            throw new TooLongFrameException();
        }
    }
}
