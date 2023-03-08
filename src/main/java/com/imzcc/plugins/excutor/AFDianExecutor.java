package com.imzcc.plugins.excutor;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.dao.domain.PlayerEntity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.UUID;

public class AFDianExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            if (args.length == 2 && command.getName().equalsIgnoreCase("afdian")) {
                Player player = (Player) commandSender;
                UUID uniqueId = player.getUniqueId();

                String key = args[0];
                String userId = args[1];
                DSLContext dslContext = DSL.using(AFDianPay.connection);
                PlayerEntity playerEntity = new PlayerEntity(uniqueId, player.getName(), userId);
                playerEntity.save(dslContext);
            }

            //todo 玩家绑定爱发电user_id


        } else {
            commandSender.sendMessage("不支持此身份调用此参数");
        }
        return false;
    }
}
