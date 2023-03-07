package com.imzcc.plugins.config;

import com.imzcc.plugins.AfadianPay;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {


    private static int port;
    private static String path;
    private static int multiplier;

    public static void load() {
        // 保存写出配置文件，此处不会替换已经存在的配置
        AfadianPay.getInstance().saveDefaultConfig();
        File config = new File(AfadianPay.getInstance().getDataFolder(), "config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(config);
        port = yamlConfiguration.getInt("afadian.callback.port");
        path = yamlConfiguration.getString("afadian.callback.path");
        multiplier = yamlConfiguration.getInt("afadian.multiplier");
        AfadianPay.LOGGER.info(String.format("read config port:%d path:%s multiplier:%d", port, path, multiplier));
    }

    public static int getPort() {
        return port;
    }

    public static String getPath() {
        return path;
    }

    public static int getMultiplier() {
        return multiplier;
    }
}
