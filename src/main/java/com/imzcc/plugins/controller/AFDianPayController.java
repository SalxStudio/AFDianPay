package com.imzcc.plugins.controller;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;

public class AFDianPayController {
    HttpServer httpServer;

    public AFDianPayController() {
        try {
            int port = Config.AFDIANPAY_CALLBACK_PORT;
            String path = Config.AFDIANPAY_CALLBACK_PATH;
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/callback/" + path, new AFDianPayHandler());
            httpServer.setExecutor(null);
            httpServer.start();
            AFDianPay.LOGGER.info(String.format("HttpServer started on port %s", port));
        } catch (IOException e) {
            AFDianPay.LOGGER.log(Level.SEVERE, "启动HttpServer失败", e);
        }
    }

    public void stop() {
        httpServer.stop(1);
    }
}
