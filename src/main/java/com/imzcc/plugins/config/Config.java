package com.imzcc.plugins.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.imzcc.plugins.AFDianPay;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static Config config = null;


    public int multiplier;
    public Callback callback;
    public DB db;

    public static Config getInstance() {
        if (config != null) {
            return config;
        }
        AFDianPay.getInstance().saveDefaultConfig();
        synchronized (Config.class) {
            if (config == null) {
                Path path = Paths.get(AFDianPay.getInstance().getDataFolder().toString(), "config.yml");
                File file = path.toFile();
                try {
                    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                    config = mapper.readValue(file, Config.class);
                    return config;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public static class Callback {
        private int port;
        private String path;

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        // getters and setters
    }

    public static class MySQL {
        private String host;
        private int port;
        private String database;
        private String username;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SQLite {
        private String filename;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }

    public static class DB {
        private String type;
        private MySQL mysql;
        private SQLite sqlite;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public MySQL getMysql() {
            return mysql;
        }

        public void setMysql(MySQL mysql) {
            this.mysql = mysql;
        }

        public SQLite getSqlite() {
            return sqlite;
        }

        public void setSqlite(SQLite sqlite) {
            this.sqlite = sqlite;
        }
    }
}
