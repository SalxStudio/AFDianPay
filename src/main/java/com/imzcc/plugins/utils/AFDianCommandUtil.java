package com.imzcc.plugins.utils;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.module.dbutils.PatchedOrderUtil;
import com.imzcc.plugins.module.dbutils.PlayerUtil;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPatchedRecord;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPlayerRecord;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AFDianCommandUtil {
    private static final Logger LOGGER = AFDianPay.LOGGER;

    public static Boolean rechargePoints(AfdianOrderRecord order) {
        try {
            AFDianPay.connection.setAutoCommit(false);
            assert PatchedOrderUtil.exist(order) : String.format("订单 [%s] 已处理", order.getOutTradeNo());
            // 解析订单
            String showAmount = order.getShowAmount();
            double parseDouble = Double.parseDouble(showAmount);
            // 金额需转整形，points插件不支持小数
            int amount = (int) parseDouble;
            String orderPlayerName = order.getRemark();
            String orderUserId = order.getUserId();
            AfdianPlayerRecord chargePlayer;
            if (StringUtils.isNotBlank(orderPlayerName)) {
                LOGGER.info(String.format("从订单中获取到玩家：[%s]，金额：[%d]", orderPlayerName, amount));
                Player player = AFDianPay.instance.getServer().getPlayer(orderPlayerName);
                chargePlayer = new AfdianPlayerRecord(player != null ? player.getUniqueId().toString() : "", player != null ? player.getName() : orderPlayerName, "");
            } else {
                chargePlayer = PlayerUtil.findByUserId(orderUserId);
                if (chargePlayer == null) {
                    LOGGER.info(String.format("爱发电用户：[%s]，金额：[%d]，未在游戏中绑定", orderUserId, amount));
                }
            }
            assert chargePlayer != null && StringUtils.isNotBlank(chargePlayer.getPlayerName()) : String.format("爱发电用户：[%s]，金额：[%d]，订单与绑定信息中都找不到玩家信息", orderUserId, amount);
            int finalAmount = amount * Config.AFDIANPAY_CALLBACK_MULTIPLIER;
            LOGGER.info(String.format("玩家：[%s] will recharge %d points", chargePlayer.getPlayerName(), amount));
            String command = Config.AFDIANPAY_EXECUTE_COMMAND
                    .replace("#player#", chargePlayer.getPlayerName())
                    .replace("#amount#", String.valueOf(finalAmount));

            boolean executed = Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            AfdianPatchedRecord patchedRecord = new AfdianPatchedRecord(chargePlayer.getUuid(), order.getOutTradeNo());
            boolean saved = PatchedOrderUtil.save(patchedRecord);
            if (executed) {
                Player player = AFDianPay.instance.getServer().getPlayer(chargePlayer.getPlayerName());
                if (player != null && player.isOnline()) {
                    player.sendMessage(String.format("你成功赞助了￥[%d]，感谢您的支持！", amount));
                } else {
                    LOGGER.info(String.format("玩家：[%s]不在线，不发送消息了", chargePlayer.getPlayerName()));
                }
                AFDianPay.instance.getServer().broadcastMessage(String.format("玩家：[%s]成功赞助了￥[%d]，老板糊涂呀！", chargePlayer.getPlayerName(), amount));
                AFDianPay.connection.commit();
                AFDianPay.connection.setAutoCommit(true);
                return true;
            } else {
                LOGGER.info(String.format("玩家：[%s]，充值失败，命令未执行", chargePlayer.getPlayerName()));
                AFDianPay.connection.rollback();
                AFDianPay.connection.setAutoCommit(true);
                return false;
            }
        } catch (SQLException e) {
            try {
                AFDianPay.connection.rollback();
                AFDianPay.connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.SEVERE, "处理订单异常", e);
            return false;
        }
    }
}
