package com.imzcc.plugins.afdian.controller;

import com.alibaba.fastjson2.JSONObject;
import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import com.imzcc.plugins.utils.AFDianCommandUtil;
import com.imzcc.plugins.module.webhook.WebHookData;
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
            String json = new String(is.readAllBytes());
            AFDianPay.LOGGER.info("Received data: " + json);
            WebHookData webHookData = JSONObject.parseObject(json, WebHookData.class);
            long ec = webHookData.getEc();
            assert 200 == ec : String.format("回调数据，code [%s] is not 200", ec);
            AfdianOrderRecord order = webHookData.getData().getOrder();

            Boolean b = AFDianCommandUtil.rechargePoints(order);

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