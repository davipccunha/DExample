package io.github.davipccunha.dexample.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    private void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        event.getPlayer().getInventory().addItem(itemStack);

        event.setCancelled(true);
    }
}
