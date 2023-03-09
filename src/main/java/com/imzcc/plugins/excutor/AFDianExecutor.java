package com.imzcc.plugins.excutor;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.dao.domain.PlayerEntity;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.ArrayList;
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
                        if (args.length == 2) {
                            String userId = args[1];
                            DSLContext dslContext = DSL.using(AFDianPay.connection);
                            PlayerEntity byUuid = PlayerEntity.findByUuid(dslContext, uniqueId);
                            if (byUuid == null) {
                                PlayerEntity playerEntity = new PlayerEntity(uniqueId, player.getName(), userId);
                                boolean save = playerEntity.save(dslContext);
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

                        } else {
                            player.sendMessage("命令格式不正确：/afdian bind user_id");
                        }
                    }
                    case "check" -> {
                        //todo 检查订单
                        player.sendMessage("该命令暂未支持");
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
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // 返回自动补全的建议列表
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            suggestions.add("bind");
            suggestions.add("check");
        }
        return suggestions;
    }

}
