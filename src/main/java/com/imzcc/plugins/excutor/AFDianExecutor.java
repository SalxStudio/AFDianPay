package com.imzcc.plugins.excutor;

import com.imzcc.plugins.AFDianPay;
import com.imzcc.plugins.config.Config;
import com.imzcc.plugins.module.dbutils.OrderUtil;
import com.imzcc.plugins.module.dbutils.PatchedOrderUtil;
import com.imzcc.plugins.module.dbutils.PlayerUtil;
import com.imzcc.plugins.module.jooq.tables.AfdianPatched;
import com.imzcc.plugins.module.jooq.tables.records.AfdianOrderRecord;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPatchedRecord;
import com.imzcc.plugins.module.jooq.tables.records.AfdianPlayerRecord;
import com.imzcc.plugins.utils.AFDianCommandUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class AFDianExecutor implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("afdian")) {
            if (commandSender instanceof Player player) {
                UUID uniqueId = player.getUniqueId();
                String commandName = args[0].toLowerCase();
                switch (commandName) {
                    case "bind" -> {
                        assert args.length == 2 : "命令格式不正确：/afdian bind user_id";
                        String userId = args[1];
                        AfdianPlayerRecord dbPlayer = PlayerUtil.findByUuid(uniqueId);
                        if (dbPlayer == null) {
                            AfdianPlayerRecord playerRecord = new AfdianPlayerRecord(uniqueId.toString(), player.getName(), userId);
                            boolean save = PlayerUtil.save(playerRecord);
                            if (save) {
                                player.sendMessage("账户绑定成功");
                                return true;
                            } else {
                                player.sendMessage("账户绑定失败，请联系管理");
                                return false;
                            }
                        } else {
                            dbPlayer.setUserId(userId);
                            boolean update = PlayerUtil.update(dbPlayer);
                            if (update) {
                                player.sendMessage("账户更新成功");
                                return true;
                            } else {
                                player.sendMessage("账户更新失败，请联系管理");
                                return false;
                            }
                        }
                    }
                    case "sync" -> {
                        assert player.hasPermission("afdianpay.admin") : "你没有权限执行此命令";
                        String commandPara = args.length == 2 ? args[1] : "all";
                        if (commandPara.equals("all")) {
                            List<AfdianOrderRecord> orderRecords = AFDianPay.afdianSDK.queryAllOrders();
                            Set<AfdianOrderRecord> newRecords = OrderUtil.selectNewRecord(orderRecords);
                            OrderUtil.saveOrders(newRecords);
                            player.sendMessage(String.format("查询到[%d]条委托, 其中[%d]条是新记录！", orderRecords.size(), newRecords.size()));
                        }
                    }
                    case "patch" -> {
                        if (!player.hasPermission("afdianpay.admin")) {
                            player.sendMessage("你没有权限执行此命令");
                            return false;
                        }
                        if (args.length != 3) {
                            player.sendMessage("命令格式错误：/afdianpay patch order_id player_name");
                            return false;
                        }
                        String orderId = args[1];
                        String playerName = args[2];
                        AfdianOrderRecord order = OrderUtil.findByOutTradeNo(orderId);
                        if (order == null) {
                            player.sendMessage("此成交不在数据库中，请手动同步订单后再试");
                            return false;
                        }
                        // 获取用户，给了name，表中查找
                        AfdianPlayerRecord playerRecord = PlayerUtil.findByName(playerName);
                        if (playerRecord == null) {
                            Player targetPlayer = AFDianPay.instance.getServer().getPlayer(playerName);
                            if (targetPlayer == null || !targetPlayer.isOnline()) {
                                player.sendMessage("获取不到玩家，该玩家可能不在线，请稍后再试");
                                return false;
                            } else {
                                //将订单与玩家进行关联
                                playerRecord = new AfdianPlayerRecord(targetPlayer.getUniqueId().toString(), targetPlayer.getName(), order.getUserId());
                                PlayerUtil.save(playerRecord);
                            }
                        }
                        boolean success = AFDianCommandUtil.rechargePoints(order);
                        player.sendMessage(String.format("订单编号: %s, %s", order.getOutTradeNo(), success ? "处理成功" : "处理失败"));
                    }
                    case "show" -> {
                        assert player.hasPermission("afdianpay.admin") : "你没有权限执行此命令";
                        String commandPara = args.length == 2 ? args[1] : "callback_url";
                        switch (commandPara) {
                            case "callback_url" -> {
                                TextComponent message = getCallbackURLMessage();
                                player.spigot().sendMessage(message);
                            }
                            case "unPatchedOrder" -> {
                                List<AfdianOrderRecord> unPatchedOrders = PatchedOrderUtil.selectUnPatchedOrder();
                                BaseComponent[] unPatchOrderMessage = getUnPatchOrderMessage(unPatchedOrders);
                                player.spigot().sendMessage(unPatchOrderMessage);
                            }
                        }
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

    private BaseComponent[] getUnPatchOrderMessage(List<AfdianOrderRecord> unPatchedOrders) {
        List<TextComponent> textComponents = unPatchedOrders.stream().map(afdianOrderRecord -> {
            TextComponent textComponent = new TextComponent(String.format("订单编号: %s, 金额: %s, 备注: %s, 用户ID: %s", afdianOrderRecord.getOutTradeNo(), afdianOrderRecord.getShowAmount(), afdianOrderRecord.getRemark(), afdianOrderRecord.getUserId()));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/afdian patch %s ", afdianOrderRecord.getOutTradeNo())));
            return textComponent;
        }).toList();
        return textComponents.toArray(BaseComponent[]::new);
    }

    private static TextComponent getCallbackURLMessage() {
        String callbackUrl = String.format("http://外网IP:%d/callback/%s", Config.AFDIANPAY_CALLBACK_PORT, Config.AFDIANPAY_CALLBACK_PATH);
        TextComponent message = new TextComponent();
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, callbackUrl));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(callbackUrl)));
        message.setText(callbackUrl);
        message.setColor(ChatColor.BLUE);
        return message;
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        Map<String, List<String>> secondCommand = new HashMap<>();
        secondCommand.put("sync", List.of("all", "new"));
        secondCommand.put("patch", List.of("all"));
        secondCommand.put("show", List.of("callback_url", "unPatchedOrder"));
        if (args.length == 1) {
            return Arrays.asList("help", "bind", "sync", "patch", "show");
        }
        if (args.length == 2) {
            return secondCommand.getOrDefault(args[0], Collections.emptyList());
        }
        if (args.length == 3 && "patch".equalsIgnoreCase(args[0])) {
            // afdian patch aaaa
            return getAllPlayerNames();
        }
        return Collections.emptyList();
    }

    private List<String> getAllPlayerNames() {
        List<String> offlinePlayerNames = Arrays.stream(AFDianPay.instance.getServer().getOfflinePlayers()).map(OfflinePlayer::getName).toList();
        List<String> onlinePlayerNames = AFDianPay.instance.getServer().getOnlinePlayers().stream().map(Player::getName).toList();
        ArrayList<String> result = new ArrayList<>(onlinePlayerNames);
        result.addAll(offlinePlayerNames);
        return result;
    }
}
