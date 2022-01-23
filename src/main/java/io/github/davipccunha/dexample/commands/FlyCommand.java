package io.github.davipccunha.dexample.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        if (player == null) return false;

        boolean isFlying = player.isFlying();
        player.setAllowFlight(!isFlying);

        String message = isFlying ? "desvoa" : "voa mlk";
        player.sendMessage(ChatColor.LIGHT_PURPLE + message);

        return false;
    }
}
