package com.imzcc.plugins.config;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.utils.DatabaseUtils;

public class Config {
    public static String AFDIANPAY_USER_ID;
    public static String AFDIANPAY_TOKEN;
    public static String AFDIANPAY_EXECUTE_COMMAND;
    public static int AFDIANPAY_CALLBACK_MULTIPLIER;
    public static int AFDIANPAY_CALLBACK_PORT;
    public static String AFDIANPAY_CALLBACK_PATH;
    public static DatabaseUtils.DatabaseType DATABASE_TYPE;
    public static String MYSQL_HOST;
    public static int MYSQL_PORT;
    public static String MYSQL_DATABASE;
    public static String MYSQL_USERNAME;
    public static String MYSQL_PASSWORD;

    public static void init(AFDianPay afDianPay) {
        afDianPay.saveDefaultConfig();
        AFDIANPAY_TOKEN = afDianPay.getConfig().getString("token");
        AFDIANPAY_USER_ID = afDianPay.getConfig().getString("user_id");
        AFDIANPAY_CALLBACK_MULTIPLIER = afDianPay.getConfig().getInt("multiplier");
        AFDIANPAY_EXECUTE_COMMAND = afDianPay.getConfig().getString("execute_command");
        AFDIANPAY_CALLBACK_PORT = afDianPay.getConfig().getInt("callback.port");
        AFDIANPAY_CALLBACK_PATH = afDianPay.getConfig().getString("callback.path");
        DATABASE_TYPE = DatabaseUtils.DatabaseType.valueOf(afDianPay.getConfig().getString("db.type"));
        MYSQL_HOST = afDianPay.getConfig().getString("db.mysql.host");
        MYSQL_PORT = afDianPay.getConfig().getInt("db.mysql.port");
        MYSQL_DATABASE = afDianPay.getConfig().getString("db.mysql.database");
        MYSQL_USERNAME = afDianPay.getConfig().getString("db.mysql.username");
        MYSQL_PASSWORD = afDianPay.getConfig().getString("db.mysql.password");
    }
}
