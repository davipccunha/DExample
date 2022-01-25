package io.github.davipccunha.dexample.commands;

import io.github.davipccunha.dexample.ExamplePlugin;
import lombok.RequiredArgsConstructor;
import me.pedro.aguiar.library.builder.chat.ChatActionBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SignCommand implements CommandExecutor {

    private final ExamplePlugin instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = sender instanceof Player ? (Player) sender : null;
        if (player == null) return false;

        new ChatActionBuilder(instance, player, message -> {

            Bukkit.getScheduler().runTaskLater(instance, () -> {
                Location playerLocation = player.getLocation();
                double signZ = playerLocation.getZ() + 2;
                Location signLocation = new Location(player.getWorld(), playerLocation.getX(), playerLocation.getY(), signZ);

                signLocation.getBlock().setType(Material.SIGN_POST);

                Sign sign = (Sign) signLocation.getBlock().getState();

                int textLength = message.length();

                sign.setLine(0, message.substring(0, Math.min(textLength, 15)));

                for (int line = 1; line <= Math.floorDiv(textLength, 15); line++) {
                    sign.setLine(line, message.substring(line * 15, (15 * line) + (textLength % 15)));
                }

                sign.update(true);

            }, 20L);

            player.sendMessage(ChatColor.GOLD + message);
        });
        return false;
    }
}
