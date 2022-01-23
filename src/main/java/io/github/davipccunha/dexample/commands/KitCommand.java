package io.github.davipccunha.dexample.commands;

import io.github.davipccunha.dexample.ExamplePlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class KitCommand implements CommandExecutor {

    private final ExamplePlugin instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = sender instanceof Player ? (Player) sender : null;
        if (player == null) return false;

        instance.getGui().open(player);

        return false;
    }
}
