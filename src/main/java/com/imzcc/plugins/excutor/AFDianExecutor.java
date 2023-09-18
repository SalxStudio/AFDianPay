package com.imzcc.plugins.excutor;

import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.pojo.dao.DBPlayer;
import com.imzcc.plugins.utils.DatabaseUtils;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jooq.DSLContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AFDianExecutor implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("afdian")) {
            if (commandSender instanceof Player player) {
                UUID uniqueId = player.getUniqueId();
                switch (args[0].toLowerCase()) {
                    case "bind" -> {
                        assert args.length == 2 : "命令格式不正确：/afdian bind user_id";
                        String userId = args[1];
                        DSLContext dslContext = DatabaseUtils.getDSLContext();
                        DBPlayer byUuid = DBPlayer.findByUuid(dslContext, uniqueId);
                        if (byUuid == null) {
                            DBPlayer DBPlayerEntity = new DBPlayer(uniqueId, player.getName(), userId);
                            boolean save = DBPlayerEntity.save(dslContext);
                            if (save) {
                                player.sendMessage("账户绑定成功");
                                return true;
                            } else {
                                player.sendMessage("账户绑定失败，请联系管理");
                                return false;
                            }
                        } else {
                            byUuid.setUserId(userId);
                            boolean update = byUuid.update(dslContext);
                            if (update) {
                                player.sendMessage("账户更新成功");
                                return true;
                            } else {
                                player.sendMessage("账户更新失败，请联系管理");
                                return false;
                            }
                        }
                    }
                    case "check" -> {
                        //todo 检查订单
                        player.sendMessage("该命令暂未支持");
                    }
                    case "callback_url" -> {
                        assert player.hasPermission("afdianpay.admin") : "你没有权限执行此命令";
                        String callbackUrl = String.format("http://外网IP:%d/callback/%s", Config.AFDIANPAY_CALLBACK_PORT, Config.AFDIANPAY_CALLBACK_PATH);
                        player.sendMessage(callbackUrl);
                    }
                }
            } else if (commandSender instanceof ConsoleCommandSender consoleCommandSender) {
                //todo 控制台角色命令
            } else {
                commandSender.sendMessage("不支持此身份调用此参数");
            }
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("help", "bind", "check");
        }
        return Collections.emptyList();
    }
}
