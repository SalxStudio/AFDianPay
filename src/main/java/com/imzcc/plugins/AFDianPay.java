package com.imzcc.plugins;

import com.imzcc.plugins.afdian.controller.AFDianPayController;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.excutor.AFDianExecutor;
import com.imzcc.plugins.http.AfdianSDK;
import com.imzcc.plugins.module.dbutils.DBDslContext;
import com.imzcc.plugins.module.dbutils.OrderUtil;
import com.imzcc.plugins.module.dbutils.PatchedOrderUtil;
import com.imzcc.plugins.module.dbutils.PlayerUtil;
import com.imzcc.plugins.utils.DatabaseUtils;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AFDianPay extends JavaPlugin {
    public static JavaPlugin instance;
    public static Logger LOGGER;
    public Config config;
    static AFDianPayController afDianPayController;
    public static Connection connection;
    public static DSLContext dslContext;
    public static AfdianSDK afdianSDK;

    @Override
    public void onEnable() {
        init();
        register();
        tasks();

    }

    private void tasks() {
        //todo 定时任务，检测有几条订单未自动处理的，向在线的有权限的玩家，发送提示
    }

    private void register() {
        PluginCommand command = getCommand("afdian");
        if (command != null) {
            command.setExecutor(new AFDianExecutor());
        }
    }

    private void init() {
        Logger.getLogger("org.jooq.Constants").setLevel(Level.WARNING);
        instance = this;
        LOGGER = getLogger();
        Config.init(this);
        afDianPayController = new AFDianPayController();
        connection = DatabaseUtils.getConnection(config);
        dslContext = DSL.using(connection);
        DBDslContext.init(dslContext);
        PlayerUtil.ensureTableExist();
        OrderUtil.ensureTableExist();
        PatchedOrderUtil.ensureTableExist();
        afdianSDK = new AfdianSDK(Config.AFDIANPAY_USER_ID, Config.AFDIANPAY_TOKEN);
    }

    @Override
    public void onDisable() {
        afDianPayController.stop();
    }
}
