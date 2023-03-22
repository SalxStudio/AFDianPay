package com.imzcc.plugins;

import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.controller.AFDianPayController;
import com.imzcc.plugins.dao.Database;
import com.imzcc.plugins.excutor.AFDianExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
        connection = Database.getConnection();
        Database.initTable();

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("bind");
            suggestions.add("check");
        }
        return suggestions;
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
