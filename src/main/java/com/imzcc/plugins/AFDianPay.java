package com.imzcc.plugins;

import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.controller.AFDianPayController;
import com.imzcc.plugins.dao.connection.JOOQConnection;
import com.imzcc.plugins.excutor.AFDianExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Logger;

public class AFDianPay extends JavaPlugin {
    public static JavaPlugin instance;
    public static Logger LOGGER;
    public static Config config;
    static AFDianPayController afDianPayController;
    static Connection connection;


    @Override
    public void onEnable() {
//        this.getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("afdian")).setExecutor(new AFDianExecutor());
        LOGGER = getLogger();
        instance = this;
        LOGGER.info("AfadianPay has been enabled.");

        config = Config.getInstance();

        afDianPayController = new AFDianPayController();
        afDianPayController.init();

        connection = JOOQConnection.getConnection(config.getDb());
    }

    @Override
    public void onDisable() {
        afDianPayController.stop();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }


}
