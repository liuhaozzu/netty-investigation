package com.liuhaozzu.test.nio.mmap;

import com.liuhaozzu.nio.mmap.MMap;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: liuhaozzu
 * @date: 2019-07-15 16:46
 */
public class MMapTest {
    @Test
    public void readFileByMMapTest() throws IOException {

        MMap mMap = new MMap("src/test/resources/mmap.txt");
        long start = System.currentTimeMillis();
        mMap.readFile();
        System.out.println("costTime:" + (System.currentTimeMillis() - start));
    }


    @Test
    public void writeFileByMMapTest() throws Exception {
        MMap mMap = new MMap("src/test/resources/mmap.txt");
        long start = System.currentTimeMillis();
        mMap.writeToFile("hello world", 1, 6);
        System.out.println("costTime:" + (System.currentTimeMillis() - start));
    }
}
