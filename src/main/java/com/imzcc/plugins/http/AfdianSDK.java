package com.imzcc.plugins.http;

import com.alibaba.fastjson2.JSONObject;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import com.imzcc.plugins.module.query.QueryData;
import com.imzcc.plugins.module.query.QueryList;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AfdianSDK {
    private final String PING_URL = "https://afdian.net/api/open/ping";
    private final String QUERY_ORDER_URL = "https://afdian.net/api/open/query-order";
    private final String QUERY_SPONSOR_URL = "https://afdian.net/api/open/query-sponsor";

    private final String user_id;
    private final String token;

    public AfdianSDK(String userId, String token) {
        this.user_id = userId;
        this.token = token;
    }

    public boolean ping() {
        JSONObject params = JSONObject.of();
        params.put("", "");
        JSONObject responseBody = afdianQuery(PING_URL, params.toJSONString());
        return responseBody.getInteger("ec") == 200;
    }

    public List<AfdianOrderRecord> queryAllOrders() {
        JSONObject params = JSONObject.of();
        long page = 1;
        long size = 100;
        long totalPage;
        params.put("page", String.valueOf(page));//按页数倒序获取订单，按订单创建时间倒序
        params.put("per_page", String.valueOf(size));//默认50，支持1-100
        params.put("out_trade_no", "");//指定订单号查询信息，如需要查多个，则英文逗号分隔
        List<AfdianOrderRecord> result = new ArrayList<>();
        do {
            JSONObject responseBody = afdianQuery(QUERY_ORDER_URL, params.toJSONString());
            QueryData queryData = responseBody.to(QueryData.class);
            QueryList data = queryData.getData();
            totalPage = data.getTotalPage();
            List<AfdianOrderRecord> pageRecord = data.getList();
            result.addAll(pageRecord);
        } while (page < totalPage);
        return result;
    }

    private JSONObject afdianQuery(String url, String jsonParams) {
        try {
            String ts = getTs();
            String user_id = this.user_id;
            String token = this.token;

            String sb = token + "params" + jsonParams + "ts" + ts + "user_id" + user_id;
            String sign = md5(sb);

            JSONObject postData = JSONObject.of();
            postData.put("user_id", user_id);
            postData.put("params", jsonParams);
            postData.put("ts", ts);
            postData.put("sign", sign);

            Unirest.setTimeouts(300, 0);
            HttpResponse<String> response = Unirest.post(url).header("Content-Type", "application/json").body(postData.toJSONString()).asString();

            assert response.getBody() != null : "响应为空";
            return JSONObject.parseObject(response.getBody());
        } catch (UnirestException e) {
            return null;
        }
    }


    public static String md5(String str) {
        String hexString = null;
        try {
            // 创建MD5消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算消息的摘要
            byte[] digest = md.digest(str.getBytes());
            // 将摘要转换为十六进制字符串
            hexString = bytesToHex(digest);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString;
    }

    private static String getTs() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
