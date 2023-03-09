package com.imzcc.plugins.dao.connection;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;

public class JOOQConnection {
    public static Connection getConnection() {
        try {
            Config config = AFDianPay.config;
            Config.DB db = config.getDb();
            switch (db.getType().toLowerCase()) {
                case "mysql" -> {
                    Class.forName("com.mysql.jdbc.Driver");
                    Config.MySQL dbMySQL = db.getMysql();
                    DriverManager.getConnection(String.format("jdbc:%s://%s:%d/%s", db.getType(), dbMySQL.getHost(), dbMySQL.getPort(), dbMySQL.getDatabase()), dbMySQL.getUsername(), dbMySQL.getPassword());
                }
                case "sqlite" -> {
                    Config.SQLite dbSqLite = db.getSqlite();
                    File dbFile = new File(AFDianPay.getInstance().getDataFolder(), dbSqLite.getFilename());
                    if (!dbFile.exists()) {
                        InputStream inputStream = AFDianPay.getInstance().getResource(dbSqLite.getFilename());
                        assert inputStream != null;
                        Files.copy(inputStream, dbFile.toPath());
                    }
                    Class.forName("org.sqlite.JDBC");
                    return DriverManager.getConnection(String.format("jdbc:%s://%s", db.getType(), AFDianPay.getInstance().getDataFolder().getAbsoluteFile() + File.separator + dbSqLite.getFilename()));
                }
                default -> {
                    return DriverManager.getConnection(String.format("jdbc:%s://%s", db.getType(), AFDianPay.getInstance().getDataFolder().getAbsoluteFile() + File.separator + "afdian.db"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}