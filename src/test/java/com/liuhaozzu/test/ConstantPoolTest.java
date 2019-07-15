package com.liuhaozzu.test;

import io.netty.channel.ChannelOption;
import org.junit.Test;

/**
 * @author: liuhaozzu
 * @date: 2019-03-20 20:02
 */
public class ConstantPoolTest {
    @Test
    public void constantPoolTest() {
        ChannelOption timeout2 = ChannelOption.newInstance("abc");
        ChannelOption timeout3 = ChannelOption.newInstance("abc");

        ChannelOption timeout = ChannelOption.valueOf("abc");
        //System.out.println(timeout == timeout2);
    }
}
