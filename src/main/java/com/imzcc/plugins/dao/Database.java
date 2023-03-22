package com.imzcc.plugins.dao;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.dao.domain.PlayerEntity;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection connection;

    public static void initTable() {
        PlayerEntity.ensureTableExist(getDSLContext());
    }

    public enum DatabaseType {
        MySQL, SQLite
    }

    public static Connection getConnection() {
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
                        File dbFile = new File(AFDianPay.getInstance().getDataFolder(), dbName);
                        if (!dbFile.exists()) {
                            boolean b = dbFile.createNewFile();
                        }
                        Class.forName("org.sqlite.JDBC");
                        connection = DriverManager.getConnection(String.format("jdbc:sqlite://%s", AFDianPay.getInstance().getDataFolder().getAbsoluteFile() + File.separator + dbName));
                    }
                    default -> {
                        connection = DriverManager.getConnection(String.format("jdbc:sqlite://%s", AFDianPay.getInstance().getDataFolder().getAbsoluteFile() + File.separator + "afdian.db"));
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

    public static DSLContext getDSLContext() {
        return DSL.using(getConnection());
    }
}