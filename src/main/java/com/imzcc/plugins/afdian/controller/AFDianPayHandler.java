package com.imzcc.plugins.afdian.controller;

import com.alibaba.fastjson2.JSONObject;
import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.utils.AFDianCommandUtil;
import com.imzcc.plugins.pojo.Order;
import com.imzcc.plugins.pojo.WebHookData;
import com.imzcc.plugins.utils.DatabaseUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AFDianPayHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            // 读取POST请求的数据
            InputStream is = exchange.getRequestBody();
            byte[] bytes = new byte[is.available()];
            String json = new String(bytes);
            AFDianPay.LOGGER.info("Received data: " + json);
            WebHookData webHookData = JSONObject.parseObject(json, WebHookData.class);
            long ec = webHookData.getEc();
            assert 200 == ec : String.format("回调数据，code [%s] is not 200", ec);
            Order order = webHookData.getData().getOrder();

            boolean b = AFDianCommandUtil.rechargePoints(order);

            // 返回响应，爱发电才能保存callback地址
            JSONObject object = new JSONObject();
            object.put("ec", 200);
            object.put("em", b);
            String response = object.toString();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}