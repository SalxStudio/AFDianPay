package com.imzcc.plugins.controller;

import com.alibaba.fastjson2.JSONObject;
import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.command.AFDianCommand;
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
            int read = is.read(bytes);
            String json = new String(bytes);
            AFDianPay.LOGGER.info("Received data: " + json);
            JSONObject jsonObject = JSONObject.parseObject(json);
            Integer code = jsonObject.getInteger("ec");
            assert 200 == code : String.format("回调数据，code [%s] is not 200", code);
            JSONObject order = jsonObject.getJSONObject("data").getJSONObject("order");
            // 解析订单
            String showAmount = order.getString("show_amount");
            Double parseDouble = Double.parseDouble(showAmount);
            // 金额需转整形，points插件不支持小数
            int amount = parseDouble.intValue();
            String playerName = order.getString("remark");
            String userId = order.getString("user_id");

            boolean b = AFDianCommand.rechargePoints(userId, playerName, amount);

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