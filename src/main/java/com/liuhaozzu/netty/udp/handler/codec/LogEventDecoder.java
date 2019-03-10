package com.liuhaozzu.netty.udp.handler.codec;

import com.liuhaozzu.netty.udp.dto.LogEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @create 2019/3/10 0010 20:03
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        ByteBuf data = msg.content();
        int idx = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);

        String fileName = data.slice(0, idx).toString(StandardCharsets.UTF_8);
        String logMsg = data.slice(idx + 1, data.readableBytes()).toString(StandardCharsets.UTF_8);
        LogEvent event = new LogEvent(msg.sender(), System.currentTimeMillis(), fileName, logMsg);
        out.add(event);
        Map map = new HashMap(6);
        map.put("1", "1");
    }
}
