package com.imzcc.plugins.command;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;

public class AFDianCommand {
    public static boolean rechargePoints(String playerName, int amount) {
        int finalAmount = amount * Config.getMultiplier();
        Bukkit.getScheduler().runTask(AFDianPay.getInstance(), () -> {
            if (StringUtils.isNotBlank(playerName)) {
                AFDianPay.LOGGER.info(String.format(" %s will recharge %d points", playerName, amount));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("points give %s %d", playerName, finalAmount));
            }
        });
        return true;
    }
}
