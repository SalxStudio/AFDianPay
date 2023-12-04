package com.imzcc.plugins.utils;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
    public static Connection connection;

    public enum DatabaseType {
        MySQL, SQLite
    }

    public static Connection getConnection(Config config) {
        if (connection == null) {
            try {
                switch (Config.DATABASE_TYPE) {
                    case MySQL -> {
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",
                                Config.MYSQL_HOST,
                                Config.MYSQL_PORT,
                                Config.MYSQL_DATABASE
                        ), Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
                    }
                    case SQLite -> {
                        String dbName = "afdian.db";
                        File dbFile = new File(AFDianPay.instance.getDataFolder(), dbName);
                        if (!dbFile.exists()) {
                            boolean b = dbFile.createNewFile();
                        }
                        Class.forName("org.sqlite.JDBC");
                        connection = DriverManager.getConnection(String.format("jdbc:sqlite://%s", AFDianPay.instance.getDataFolder().getAbsoluteFile() + File.separator + dbName));
                    }
                    default -> {
                        connection = DriverManager.getConnection(String.format("jdbc:sqlite://%s", AFDianPay.instance.getDataFolder().getAbsoluteFile() + File.separator + "afdian.db"));
                    }
                }
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return connection;
    }
}