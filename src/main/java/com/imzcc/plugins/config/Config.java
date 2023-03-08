package com.imzcc.plugins.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

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
        synchronized (Config.class) {
            if (config == null) {
//                AFDianPay.getInstance().saveDefaultConfig();
                Path path = Paths.get("D:\\WorkSpace\\IDEA\\AfadianPay\\src\\main\\resources", "config.yml");
                File file = path.toFile();
                Config load = null;
                try {
                    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                    load = mapper.readValue(file, Config.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                config = load;
            }
            return config;
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

    public class Callback {
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

    public class MySQL {
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

    public class SQLite {
        private String filepath;

        public String getFilepath() {
            return filepath;
        }

        public void setFilepath(String filepath) {
            this.filepath = filepath;
        }
    }

    public class DB {
        private String type;
        private MySQL mySQL;
        private SQLite sqLite;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public MySQL getMySQL() {
            return mySQL;
        }

        public void setMySQL(MySQL mySQL) {
            this.mySQL = mySQL;
        }

        public SQLite getSqLite() {
            return sqLite;
        }

        public void setSqLite(SQLite sqLite) {
            this.sqLite = sqLite;
        }
    }
}
