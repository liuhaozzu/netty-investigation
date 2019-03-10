package com.liuhaozzu.netty.investigation.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author Administrator
 * @create 2019/3/9 0009 22:38
 */
public class Integer2StringDecoder extends MessageToMessageDecoder<Integer> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Integer msg, List out) throws Exception {

    }
}
