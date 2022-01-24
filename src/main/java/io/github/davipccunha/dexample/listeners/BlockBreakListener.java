package io.github.davipccunha.dexample.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Crops;

import java.util.Arrays;

public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {

        final Block block = event.getBlock();
        final Material material = block.getType();
        boolean isCrop = block.getState().getData() instanceof Crops;

        if (isCrop) {
            event.getPlayer().getInventory().remove(block.getType());
        }

        block.getDrops().forEach(drop -> event.getPlayer().getInventory().addItem(drop));
        event.getPlayer().setExp(event.getPlayer().getExp() + event.getExpToDrop());

        event.getBlock().setType(Material.AIR);

        if (isCrop) {
            event.getBlock().setType(material);
            remove(event.getPlayer(), material);
        }

        event.setCancelled(true);
    }

    private void remove(Player player, Material material) {
        Arrays.stream(player.getInventory().getContents()).forEach(item -> {
            if (item == null) return;
            int amount = item.getAmount() - 1;

            if (item.getType() == material) {
                if (amount >= 1)
                    item.setAmount(amount);
                else
                    player.getInventory().remove(item);
            }
        });
    }

}
