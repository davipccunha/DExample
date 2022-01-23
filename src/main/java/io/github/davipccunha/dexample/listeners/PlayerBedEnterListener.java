package io.github.davipccunha.dexample.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerBedEnterListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        event.getBed().getWorld().createExplosion(event.getBed().getLocation(), 100.0f);
        event.setCancelled(true);
    }
}
