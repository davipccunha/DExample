package io.github.davipccunha.dexample.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        if (player == null) return false;

        player.openInventory(player.getEnderChest());

        return false;
    }
}
