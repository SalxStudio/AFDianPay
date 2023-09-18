package com.imzcc.plugins;

import com.imzcc.plugins.afdian.controller.AFDianPayController;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.excutor.AFDianExecutor;
import com.imzcc.plugins.utils.DatabaseUtils;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AFDianPay extends JavaPlugin {
    public static JavaPlugin instance;
    public static Logger LOGGER;
    public Config config;
    static AFDianPayController afDianPayController;
    public static Connection connection;

    @Override
    public void onLoad() {
        Logger.getLogger("org.jooq.Constants").setLevel(Level.WARNING);
        instance = this;
        LOGGER = getLogger();
        config = new Config(this);
        afDianPayController = new AFDianPayController();
        connection = DatabaseUtils.getConnection();
        DatabaseUtils.initTable();
    }

    @Override
    public void onEnable() {
        PluginCommand command = getCommand("afdian");
        if (command != null) {
            command.setExecutor(new AFDianExecutor());
        }
    }

    @Override
    public void onDisable() {
        afDianPayController.stop();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

}
