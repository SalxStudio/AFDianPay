package com.imzcc.plugins.command;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.dao.Database;
import com.imzcc.plugins.dao.domain.PlayerEntity;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;

public class AFDianCommand {
    public static boolean rechargePoints(String userId, String playerName, int amount) {
        String chargePlayerName;
        if (StringUtils.isNotBlank(playerName)) {
            AFDianPay.LOGGER.info(String.format("从订单中获取到玩家：[%s]，金额：[%d]", playerName, amount));
            chargePlayerName = playerName;
        } else {
            Connection connection = Database.getConnection();
            DSLContext dslContext = DSL.using(connection);
            PlayerEntity playerEntity = PlayerEntity.findByUserId(dslContext, userId);
            if (playerEntity == null) {
                AFDianPay.LOGGER.info(String.format("爱发电用户：[%s]，金额：[%d]，未在游戏中绑定", userId, amount));
                chargePlayerName = null;
            } else {
                chargePlayerName = playerEntity.getPlayerName();
            }
        }
        if (StringUtils.isNotBlank(chargePlayerName)) {
            int finalAmount = amount * Config.AFDIANPAY_CALLBACK_MULTIPLIER;
            Bukkit.getScheduler().runTask(AFDianPay.getInstance(), () -> {
                AFDianPay.LOGGER.info(String.format("玩家：[%s] will recharge %d points", chargePlayerName, amount));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("points give %s %d", chargePlayerName, finalAmount));
                Player player = AFDianPay.getInstance().getServer().getPlayer(chargePlayerName);
                if (player != null && player.isOnline()) {
                    player.sendMessage(String.format("你成功赞助了￥[%d]，感谢您的支持！", amount));
                } else {
                    AFDianPay.LOGGER.info(String.format("玩家：[%s]不在线，不发送消息了", chargePlayerName));
                }
            });
            return true;
        } else {
            AFDianPay.LOGGER.info(String.format("爱发电用户：[%s]，金额：[%d]，订单与绑定信息中都找不到玩家信息，忽略订单", userId, amount));
        }
        return false;
    }
}
