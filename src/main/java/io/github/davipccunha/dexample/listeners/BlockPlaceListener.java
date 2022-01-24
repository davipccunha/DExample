package io.github.davipccunha.dexample.listeners;

import io.github.davipccunha.dexample.models.BlockConfirmation;
import lombok.RequiredArgsConstructor;
import me.pedro.aguiar.library.builder.inventory.InventoryBuilder;
import me.pedro.aguiar.library.builder.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class BlockPlaceListener implements Listener {

    private final Map<Player, BlockConfirmation> confirmations = new HashMap<>();

    @EventHandler(ignoreCancelled = true)
    private void onBlockPlace(BlockPlaceEvent event) {
        final Block block = event.getBlock();

        if (block.getType() != Material.QUARTZ_BLOCK) return;

        InventoryBuilder inventoryBuilder = new InventoryBuilder("§8Quartz Block", 3);

        ItemBuilder confirm = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short) 5));
        confirm.addNbt("confirm", "true");
        confirm.setDisplayName("§eConfirmar");
        confirm.setLore("§7Clique para confirmar a colocação");

        inventoryBuilder.addItem(confirm.build(), 10);

        ItemBuilder cancel = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short) 14));
        cancel.addNbt("confirm", "false");
        cancel.setDisplayName("§eCancelar");
        cancel.setLore("§7Clique para cancelar a colocação");

        inventoryBuilder.addItem(cancel.build(), 16);

        confirmations.put(event.getPlayer(), new BlockConfirmation(block.getType(), block.getLocation().clone()));

        event.getPlayer().openInventory(inventoryBuilder.build());

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    private void onInventoryClick(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals("§8Quartz Block")) return;

        event.setCancelled(true);

        if (!event.getCurrentItem().hasItemMeta()) return;

        final Player player = (Player) event.getWhoClicked();

        final ItemBuilder itemBuilder = new ItemBuilder(event.getCurrentItem());

        if (!itemBuilder.hasNbt("confirm") || !confirmations.containsKey(player)) return;

        final BlockConfirmation block = confirmations.get(player);

        if (!Boolean.parseBoolean(itemBuilder.getNbt("confirm"))) {
            event.getWhoClicked().getInventory().addItem(new ItemStack(block.getMaterial(), 1));
        } else {
            block.getLocation().getBlock().setType(block.getMaterial());
        }

        confirmations.remove((Player) event.getWhoClicked());

        event.getWhoClicked().closeInventory();
    }

}
