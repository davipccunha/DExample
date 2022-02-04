package io.github.davipccunha.dexample.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.RED + player.getName() + " saiu. Que lixo!");
    }
}
