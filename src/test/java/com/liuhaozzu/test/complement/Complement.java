package com.liuhaozzu.test.complement;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Administrator
 * @create 2019/4/6 0006 10:15
 */
public class Complement {
    @Test
    public void negativeComplement() {
        System.out.println(Integer.toBinaryString(5>>2));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(-5>>2));
        System.out.println(-5>>2);
        System.out.println(Integer.parseInt("101",2));
        System.out.println(Integer.toBinaryString(-2));
        //System.out.println(Integer.parseInt("11111111111111111111111111111110",2));

        int a=3;
        int b=6;
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void socketTest() throws IOException {
        Socket socket = new Socket("localhost", 8080);

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println(reader.readLine());


        System.out.println("after readline");

    }

    @Test
    public void serverTest() throws IOException {
        ServerSocket server = new ServerSocket(8080);
        Socket socket = server.accept();
        System.out.println(socket);
    }

}
