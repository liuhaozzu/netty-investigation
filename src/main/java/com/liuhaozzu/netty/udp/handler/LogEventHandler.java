package com.liuhaozzu.netty.udp.handler;

import com.liuhaozzu.netty.udp.dto.LogEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author Administrator
 * @create 2019/3/10 0010 20:09
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) {
        StringBuilder builder = new StringBuilder();
        builder.append(msg.getReceived())
                .append("[")
                .append(msg.getSource().toString())
                .append("]    [")
                .append(msg.getLogFile())
                .append("]  :")
                .append(msg.getMsg());
        System.out.println(builder.toString());
    }
}
