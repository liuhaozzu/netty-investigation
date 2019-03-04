package com.liuhaozzu.oio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @Author Administrator
 * @create 2019/3/3 0003 13:00
 */
public class PlainOioServer {
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);//server 监听端口
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();//接受连接，阻塞
                System.out.println("Accepted connection from " + clientSocket);

                new Thread(()->{
                    OutputStream out;
                    try {
                        out = clientSocket.getOutputStream();
                        out.write("Hi!\r\n".getBytes(StandardCharsets.UTF_8));
                        out.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally{
                        if (clientSocket != null) {
                            try {
                                clientSocket.close();//关闭连接
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
