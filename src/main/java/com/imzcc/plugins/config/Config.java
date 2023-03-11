package com.imzcc.plugins.config;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.dao.Database;

public class Config {
    private final AFDianPay plugin;
    public static int AFDIANPAY_CALLBACK_MULTIPLIER;
    public static int AFDIANPAY_CALLBACK_PORT;
    public static String AFDIANPAY_CALLBACK_PATH;
    public static Database.DatabaseType DATABASE_TYPE;
    public static String MYSQL_HOST;
    public static int MYSQL_PORT;
    public static String MYSQL_DATABASE;
    public static String MYSQL_USERNAME;
    public static String MYSQL_PASSWORD;

    public Config(AFDianPay afDianPay) {
        afDianPay.saveDefaultConfig();
        this.plugin = afDianPay;
        reload();
    }

    private void reload() {
        this.plugin.reloadConfig();
        AFDIANPAY_CALLBACK_MULTIPLIER = this.plugin.getConfig().getInt("multiplier");
        AFDIANPAY_CALLBACK_PORT = this.plugin.getConfig().getInt("callback.port");
        AFDIANPAY_CALLBACK_PATH = this.plugin.getConfig().getString("callback.path");
        DATABASE_TYPE = Database.DatabaseType.valueOf(this.plugin.getConfig().getString("db.type"));
        MYSQL_HOST = this.plugin.getConfig().getString("db.mysql.host");
        MYSQL_PORT = this.plugin.getConfig().getInt("db.mysql.port");
        MYSQL_DATABASE = this.plugin.getConfig().getString("db.mysql.database");
        MYSQL_USERNAME = this.plugin.getConfig().getString("db.mysql.username");
        MYSQL_PASSWORD = this.plugin.getConfig().getString("db.mysql.password");
    }

}
