package io.github.davipccunha.dexample.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {

        final Block block = event.getBlock();
        boolean isCrop = block.getState().getData() instanceof Crops;

        if (isCrop) {
            event.getPlayer().getInventory().remove(block.getType());
        }

        System.out.println(block.getDrops());
        System.out.println(block.getDrops().size());

        block.getDrops().forEach(drop -> {
            event.getPlayer().getInventory().addItem(drop);
        });
        event.getPlayer().setExp(event.getPlayer().getExp() + event.getExpToDrop());

        event.getBlock().setType(Material.AIR);

        if (isCrop) {
            event.getBlock().setType(block.getType());
        }
    }
}
