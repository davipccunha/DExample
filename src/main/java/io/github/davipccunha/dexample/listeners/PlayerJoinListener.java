package io.github.davipccunha.dexample.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ChatColor.GOLD + event.getPlayer().getName() + " entrou!");
    }


}
