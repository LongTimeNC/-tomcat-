package com.smallfatcat.server;

import java.io.*;

/**
 * @author zsz
 * 封装响应，处理响应相关的业务
 * @date 2022/9/3
 */
public class MyHttpResponse {
    private OutputStream outputStream;

    public MyHttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendRedirect(String uri){
        //判断目标资源是否存在
        //不存在返回404
        //存在直接返回目标资源数据
        //System.getProperty("user.dir") 获取路径
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/static" + uri);
        if(file.exists()){
            //返回目标资源数据--先通过io流读取本地数据--再通过输出流输出
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[(int)file.length()];
                fileInputStream.read(bytes);
                String result = new String(bytes);
                String response = getResponseMessage("200", result);
                this.outputStream.write(response.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            try {
                //返回404
                String error = getResponseMessage("404","404 File Not Found");
                this.outputStream.write(error.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //获取响应格式--必须按照特定的格式进行处理响应
    public String getResponseMessage(String code,String message){
        return "HTTP/1.1 " + code + "\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + message.length()
                + "\r\n"
                + "\r\n"
                + message;
    }


}
