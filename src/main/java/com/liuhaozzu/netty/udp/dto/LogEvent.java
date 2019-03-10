package com.liuhaozzu.netty.udp.dto;

import java.net.InetSocketAddress;

/**
 * @Author Administrator
 * @create 2019/3/10 0010 17:20
 */
public class LogEvent {
    public static final byte SEPARATOR = ':';
    private final InetSocketAddress source;
    private final String logFile;
    private final String msg;
    private final long received;

    public LogEvent(String logFile, String msg) {
        this(null, -1, logFile, msg);
    }


    public LogEvent(InetSocketAddress source, long received, String logFile, String msg) {
        this.source = source;
        this.received = received;
        this.logFile=logFile;
        this.msg = msg;
    }

    public InetSocketAddress getSource() {
        return source;
    }

    public String getLogFile() {
        return logFile;
    }

    public String getMsg() {
        return msg;
    }

    public long getReceived() {
        return received;
    }
}
