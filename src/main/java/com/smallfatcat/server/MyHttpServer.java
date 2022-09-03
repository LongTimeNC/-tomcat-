package com.smallfatcat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zsz
 * 开启socket服务
 * @date 2022/9/3
 */
public class MyHttpServer {
    //设置默认端口 8080
    private int port = 8080;

    //定义一个接受请求的方法
    public void receicing(){
        try {
            //创建socket服务
            ServerSocket serverSocket = new ServerSocket(port);
            //循环接受请求
            while (true){
                //获取连接对象
                Socket socket = serverSocket.accept();
                //通过输入流获取链接对象
                //创建一个request请求
                InputStream inputStream = socket.getInputStream();
                MyHttpRequest request = new MyHttpRequest(inputStream);
                //解析请求
                request.parse();
                //创建一个response响应
                OutputStream outputStream = socket.getOutputStream();
                MyHttpResponse response = new MyHttpResponse(outputStream);
                //进行响应
                response.sendRedirect(request.getUri());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
