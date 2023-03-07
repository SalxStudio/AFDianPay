package com.imzcc.plugins.controller;

import com.imzcc.plugins.AfadianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.handler.AfadianPayHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;

public class AfadianPayController {
    HttpServer httpServer;

    public void init() {
        try {
            int port = Config.getPort();
            String path = Config.getPath();
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/callback/" + path, new AfadianPayHandler());
            httpServer.setExecutor(null);
            httpServer.start();
            AfadianPay.LOGGER.info(String.format("HttpServer started on port %s", port));
        } catch (IOException e) {
            AfadianPay.LOGGER.log(Level.SEVERE, "启动HttpServer失败", e);
        }
    }

    public void stop() {
        httpServer.stop(1);
    }
}
