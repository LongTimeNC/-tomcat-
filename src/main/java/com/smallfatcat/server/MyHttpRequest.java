package com.smallfatcat.server;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zsz
 * 封装请求，处理请求相关的业务
 * @date 2022/9/3
 */
public class MyHttpRequest {

    private InputStream inputStream;
    private String uri;

    public MyHttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    //解析输入流里面的信息
    public void parse(){
        try {
            //将inputStream里面的数据读取到字节数组里面
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            String request = new String(bytes);
            //调用方法获取uri
            parseUri(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //通过输入流获取的信息解析字符串里面的uri
    public void parseUri(String request){
        //通过查找空格
        int index1,index2;
        index1 = request.indexOf(" ");
        index2 = request.indexOf(" ",index1 + 1);
        //通过字符串的substring截取方法截取uri
        uri = request.substring(index1 + 1,index2);
        System.out.println(uri);
    }
    //获取uri
    public String getUri(){
        return this.uri;
    }




}
