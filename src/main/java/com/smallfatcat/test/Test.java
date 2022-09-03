package com.smallfatcat.test;

import com.smallfatcat.server.MyHttpServer;

/**
 * @author zsz
 * @Description
 * @date 2022/9/3
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Server startup successfully");
        MyHttpServer server = new MyHttpServer();
        server.receicing();
    }
}
