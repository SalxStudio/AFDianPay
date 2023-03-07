package com.imzcc.plugins;

import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.controller.AFDianPayController;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AFDianPay extends JavaPlugin implements Listener {
    public static JavaPlugin instance;
    public static Logger LOGGER;
    static AFDianPayController afDianPayController;


    @Override
    public void onEnable() {
//        this.getServer().getPluginManager().registerEvents(this, this);
        LOGGER = getLogger();
        instance = this;
        LOGGER.info("AfadianPay has been enabled.");

        Config.load();

        afDianPayController = new AFDianPayController();
        afDianPayController.init();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        afDianPayController.stop();
    }

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static boolean rechargePoints(String playerName, int amount) {
        int finalAmount = amount * Config.getMultiplier();
        Bukkit.getScheduler().runTask(getInstance(), () -> {
            if (StringUtils.isNotBlank(playerName)) {
                LOGGER.info(String.format(" %s will recharge %d points", playerName, amount));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("points give %s %d", playerName, finalAmount));
            }
        });
        return true;
    }
}
