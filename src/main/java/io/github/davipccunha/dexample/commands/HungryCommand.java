package io.github.davipccunha.dexample.commands;

import io.github.davipccunha.dexample.ExamplePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HungryCommand implements CommandExecutor {

    private final ExamplePlugin instance;

    public HungryCommand(ExamplePlugin instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender instanceof Player ? (Player)sender : null;
        if (player == null) return false;

        player.setFoodLevel(instance.getConfig().getInt("foodLevel"));

        final String message = instance.getConfig().getString("message").replace("&", "§");

        player.sendMessage(message);

        return false;
    }
}
