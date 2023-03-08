package com.imzcc.plugins.dao.connection;

import com.imzcc.plugins.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JOOQConnection {
    public static Connection getConnection(Config.DB db) {
        try {
            switch (db.getType().toLowerCase()) {
                case "mysql" -> {
                    Config.MySQL dbMySQL = db.getMySQL();
                    DriverManager.getConnection(String.format("jdbc:%s://%s:%d/%s", db.getType(), dbMySQL.getHost(), dbMySQL.getPort(), dbMySQL.getDatabase()), dbMySQL.getUsername(), dbMySQL.getPassword());
                }
                case "sqlite" -> {
                    Config.SQLite dbSqLite = db.getSqLite();
                    return DriverManager.getConnection(String.format("jdbc:%s://%s", db.getType(), dbSqLite.getFilepath()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}