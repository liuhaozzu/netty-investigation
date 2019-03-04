package com.liuhaozzu.netty.bytebuf;

import io.netty.buffer.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ByteProcessor;
import io.netty.util.ReferenceCounted;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import sun.reflect.misc.FieldUtil;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @Author Administrator
 * @create 2019/3/3 0003 15:27
 */
public class ByteBufInvestigation {
    public void heapBufDemo() {
        ByteBuf heapBuf = null;
        //检查ByteBuf是否有一个支撑数组
        if (heapBuf.hasArray()) {
            byte[] array=heapBuf.array();
            //第一个字节的偏移量
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            handleArray(array, offset, length);
        }
    }

    public void directBufDemo() {
        ByteBuf directBuf = null;
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length];
            directBuf.getBytes(directBuf.readerIndex(), array);
            handleArray(array, 0, length);
        }
    }

    public void compositeByteBufDemoOfByteBuffer() {
        //use an array to hold the message parts
        ByteBuffer header = null;
        ByteBuffer body = null;
        ByteBuffer[] message = new ByteBuffer[]{header, body};
        //Create a new ByteBuffer and use copy to merge the header and body
        ByteBuffer message2 = ByteBuffer.allocate(header.remaining() + body.remaining());
        message2.put(header)
                .put(body);
        message2.flip();
    }

    public void compositeByteBufDemoOfByteBuf() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = null;
        ByteBuf bodyBuf = null;
        messageBuf.addComponents(headerBuf, bodyBuf);

        //刪除位于索引位置为0的ByteBuf
        messageBuf.removeComponent(0);
        for (ByteBuf bf : messageBuf) {
            System.out.println(bf.toString(StandardCharsets.UTF_8));
        }
        //
        int length = messageBuf.readableBytes();
        byte[] array = new byte[length];
        messageBuf.getBytes(messageBuf.readerIndex(), array);
        handleArray(array, 0, array.length);
    }

    @Test
    public void iteratorByteBuf() {
        ByteBuf buf = Unpooled.buffer(10);
        buf.setByte(6, 1);
        for (int i = 0; i < buf.capacity(); i++) {
            byte b = buf.getByte(i);
            System.out.println(b);
        }
        while (buf.isReadable()) {
            System.out.println(buf.readByte());
        }
        //error
        System.out.println(buf.readByte());
        buf.forEachByte(ByteProcessor.FIND_NUL);
        buf.forEachByte(ByteProcessor.FIND_CR);
    }

    @Test
    public void byteBufferDemo() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.put("hello world".getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        System.out.println(byteBuffer.toString());
        System.out.print(new String(byteBuffer.array()));
    }

    private void handleArray(byte[] array, int offset, int length) {
    }

    @Test
    public void strTest() throws IllegalAccessException {
        String abc = new String("abcdefg");
        String r = abc.substring(0, 3);
        Field field= FieldUtils.getField(String.class, "value", true);

        System.out.println(field.get(abc));
        System.out.println(field.get(r));

    }

    @Test
    public void byteBufSlice() {
        ByteBuf buf = Unpooled.copiedBuffer("netty in action rocks!", StandardCharsets.UTF_8);
        ByteBuf sliced = buf.slice(0, 15);
        System.out.println(sliced.toString(StandardCharsets.UTF_8));
        buf.setByte(0, (byte)'J');

        System.out.println(buf.getByte(0) == sliced.getByte(0)); ;
    }

    @Test
    public void byteBufCopy() {
        ByteBuf buf = Unpooled.copiedBuffer("netty in action rocks!", StandardCharsets.UTF_8);
        ByteBuf copy = buf.copy(0, 15);
        System.out.println(copy.toString(StandardCharsets.UTF_8));

        buf.setByte(0, (byte) 'J');
        System.out.println(buf.getByte(0) == copy.getByte(0));
    }

    @Test
    public void byteBufAllocator() {
        //ByteBuf buf = new PooledByteBufAllocator().heapBuffer();
        ByteBuf buf = Unpooled.copiedBuffer("hello world".getBytes(StandardCharsets.UTF_8));
        System.out.println(ByteBufUtil.hexDump(buf));

    }

    @Test
    public void referenceCount() {
        Channel channel = new NioSocketChannel();
        ByteBufAllocator allocator = channel.alloc();
        ByteBuf buf = allocator.directBuffer();
        assert buf.refCnt()==1;
        boolean released = buf.release();
        System.out.println(released);
        //ChannelHandler
    }

}
