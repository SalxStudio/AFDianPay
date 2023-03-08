package com.imzcc.plugins.excutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AFDianExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            if (args.length == 2 && command.getName().equalsIgnoreCase("afdian")) {
                String key = args[0];
                String userId = args[1];

            }

            //todo 玩家绑定爱发电user_id

            Player player = (Player) commandSender;
            UUID uniqueId = player.getUniqueId();

        } else {
            commandSender.sendMessage("不支持此身份调用此参数");
        }
        return false;
    }
}
