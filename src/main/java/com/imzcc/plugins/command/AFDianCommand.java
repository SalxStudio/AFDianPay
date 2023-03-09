package com.imzcc.plugins.command;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.dao.connection.JOOQConnection;
import com.imzcc.plugins.dao.domain.PlayerEntity;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.Objects;

public class AFDianCommand {
    public static boolean rechargePoints(String userId, String playerName, int amount) {
        Connection connection = JOOQConnection.getConnection();
        DSLContext dslContext = DSL.using(connection);
        PlayerEntity playerEntity = PlayerEntity.findByUserId(dslContext, userId);
        if (playerEntity == null) {
            AFDianPay.LOGGER.info(String.format("爱发电用户：[%s]，金额：[%d]，未在游戏中绑定，忽略订单", userId, amount));
            return false;
        }
        if (StringUtils.isNotBlank(playerEntity.getPlayerName())) {
            int finalAmount = amount * Objects.requireNonNull(Config.getInstance()).getMultiplier();
            Bukkit.getScheduler().runTask(AFDianPay.getInstance(), () -> {
                AFDianPay.LOGGER.info(String.format("%s will recharge %d points", playerEntity.getPlayerName(), amount));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("points give %s %d", playerEntity.getPlayerName(), finalAmount));
                Player player = AFDianPay.getInstance().getServer().getPlayer(playerEntity.getUuid());
                if (player != null && player.isOnline()) {
                    player.sendMessage(String.format("你成功充值了￥[%d]，感谢您的支持！", amount));
                } else {
                    AFDianPay.LOGGER.info(String.format("玩家：[%s]不在线，不发送消息了", playerEntity.getPlayerName()));
                }
            });
        } else {
            AFDianPay.LOGGER.info(String.format("爱发电用户：[%s]，金额：[%d]，获取不到用户名：[%s]，忽略订单", userId, amount, playerEntity.getPlayerName()));
            return false;
        }
        return true;
    }
}
