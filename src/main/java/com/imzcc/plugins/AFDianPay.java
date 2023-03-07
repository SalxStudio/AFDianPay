package com.imzcc.plugins;

import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.controller.AFDianPayController;
import com.imzcc.plugins.excutor.AFDianExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class AFDianPay extends JavaPlugin {
    public static JavaPlugin instance;
    public static Logger LOGGER;
    static AFDianPayController afDianPayController;


    @Override
    public void onEnable() {
//        this.getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("afdian")).setExecutor(new AFDianExecutor());
        LOGGER = getLogger();
        instance = this;
        LOGGER.info("AfadianPay has been enabled.");

        Config.load();

        afDianPayController = new AFDianPayController();
        afDianPayController.init();
    }

    @Override
    public void onDisable() {
        afDianPayController.stop();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }


}
