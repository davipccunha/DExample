package io.github.davipccunha.dexample.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PlayerItemDamageListener implements Listener {

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(true);
    }
}
