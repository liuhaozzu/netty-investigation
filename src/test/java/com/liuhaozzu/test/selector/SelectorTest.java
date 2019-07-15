package com.liuhaozzu.test.selector;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Author Administrator
 * @create 2019/3/20 0020 23:20
 */
public class SelectorTest {
    @Test
    public void selectorTest() throws IOException {
        Selector selector = Selector.open();
        SocketChannel channel0 = SocketChannel.open();
        SocketChannel channel1 = SocketChannel.open();
        SocketChannel channel2 = SocketChannel.open();
        System.out.println(channel0.equals(channel1));
        System.out.println(channel1);
        System.out.println(channel2);

        channel0.configureBlocking(false);
        SelectionKey selectionKey = channel0.register(selector, SelectionKey.OP_CONNECT);
        channel1.configureBlocking(false);
        SelectionKey selectionKey1 = channel1.register(selector, SelectionKey.OP_CONNECT);
        channel2.configureBlocking(false);
        SelectionKey selectionKey2 = channel2.register(selector, SelectionKey.OP_CONNECT);

        System.out.println(selectionKey);
        System.out.println(selectionKey1);
        System.out.println(selectionKey2);

    }
}
