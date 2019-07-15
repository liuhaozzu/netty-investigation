package com.liuhaozzu.nio.mmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: liuhaozzu
 * @date: 2019-07-15 14:16
 */
public class MMap {
    private String path;

    public MMap(String path) {
        this.path = path;
    }

    public void readFile() throws IOException {
        long fileLen;
        int bufSize = 1;

        File file = new File(path);
        fileLen = file.length();
        MappedByteBuffer mbb = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fileLen);

        //mbb.load();
        byte[] buf = new byte[bufSize];
        mbb.load();

        for (int offset = 0; offset < fileLen; offset += bufSize) {
            mbb.load();
            long size;
            int startOffset = offset;
            if (fileLen - offset > bufSize) {
                mbb.get(buf);
                size = bufSize;
            } else {
                int i=0;
                size = fileLen - offset;
                for (; offset < fileLen; offset++) {
                    byte data = mbb.get(offset);
                    buf[i++] = data;
                }
            }

            System.out.println("startOffset:" + startOffset + "->\r\n" + new String(buf,0,(int) size, StandardCharsets.UTF_8));

        }
    }

    public void writeToFile(String data, int start, int offset) throws IOException {
        long fileLen;
        File file = new File(path);
        fileLen = file.length();
        MappedByteBuffer mbb = new RandomAccessFile(file, "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, fileLen);

        mbb.put(data.getBytes(StandardCharsets.UTF_8), 0, data.getBytes(StandardCharsets.UTF_8).length);
        ByteBuffer byteBuffer = mbb.force();

        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));
    }
}
