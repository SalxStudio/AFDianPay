package com.imzcc.plugins.utils;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.pojo.Order;
import com.imzcc.plugins.pojo.dao.DBPlayer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.jooq.DSLContext;

import java.util.logging.Logger;

public class AFDianCommandUtil {
    private static final Logger LOGGER = AFDianPay.LOGGER;

    public static boolean rechargePoints(Order order) {
        boolean exist = order.exist(DatabaseUtils.getDSLContext());
        assert !exist : String.format("订单 [%s] 已存在", order.getOutTradeNo());
        // 解析订单
        String showAmount = order.getShowAmount();
        double parseDouble = Double.parseDouble(showAmount);
        // 金额需转整形，points插件不支持小数
        int amount = (int) parseDouble;
        String playerName = order.getRemark();
        String userId = order.getUserID();
        String chargePlayerName;
        if (StringUtils.isNotBlank(playerName)) {
            LOGGER.info(String.format("从订单中获取到玩家：[%s]，金额：[%d]", playerName, amount));
            chargePlayerName = playerName;
        } else {
            DSLContext dslContext = DatabaseUtils.getDSLContext();
            DBPlayer player = DBPlayer.findByUserId(dslContext, userId);
            if (player == null) {
                LOGGER.info(String.format("爱发电用户：[%s]，金额：[%d]，未在游戏中绑定", userId, amount));
                chargePlayerName = null;
            } else {
                chargePlayerName = player.getPlayerName();
            }
        }
        assert StringUtils.isNotBlank(chargePlayerName) : String.format("爱发电用户：[%s]，金额：[%d]，订单与绑定信息中都找不到玩家信息", userId, amount);
        int finalAmount = amount * Config.AFDIANPAY_CALLBACK_MULTIPLIER;
        LOGGER.info(String.format("玩家：[%s] will recharge %d points", chargePlayerName, amount));
        String command = Config.AFDIANPAY_EXECUTE_COMMAND
                .replace("#player#", chargePlayerName)
                .replace("#amount#", String.valueOf(finalAmount));

        boolean executed = Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        if (executed) {
            org.bukkit.entity.Player player = AFDianPay.getInstance().getServer().getPlayer(chargePlayerName);
            if (player != null && player.isOnline()) {
                player.sendMessage(String.format("你成功赞助了￥[%d]，感谢您的支持！", amount));
            } else {
                LOGGER.info(String.format("玩家：[%s]不在线，不发送消息了", chargePlayerName));
            }
            AFDianPay.getInstance().getServer().broadcastMessage(String.format("玩家：[%s]成功赞助了￥[%d]，老板糊涂呀！", chargePlayerName, amount));
            return true;
        } else {
            LOGGER.info(String.format("玩家：[%s]，充值失败，命令未执行", chargePlayerName));
            return false;
        }
    }
}
