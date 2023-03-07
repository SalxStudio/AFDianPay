package com.imzcc.plugins.excutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AFDianExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            //todo 玩家绑定爱发电user_id
            Player player = (Player) commandSender;
            UUID uniqueId = player.getUniqueId();

        }
        return false;
    }
}
