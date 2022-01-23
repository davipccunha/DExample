package io.github.davipccunha.dexample.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if  (killer == null) return;

        event.getDrops().forEach(drop -> killer.getInventory().addItem(drop));
        killer.giveExp(event.getDroppedExp());

        event.setDroppedExp(0);
        event.getDrops().clear();
    }
}
