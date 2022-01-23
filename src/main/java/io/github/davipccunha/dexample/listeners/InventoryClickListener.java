package io.github.davipccunha.dexample.listeners;

import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.Kit;
import lombok.RequiredArgsConstructor;
import me.pedro.aguiar.library.builder.inventory.InventoryBuilder;
import me.pedro.aguiar.library.builder.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@RequiredArgsConstructor
public class InventoryClickListener implements Listener {

    private final ExamplePlugin instance;

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getInventory().getName().equalsIgnoreCase("§8Kits")) return;
        event.setCancelled(true);

        final ItemStack item = event.getCurrentItem();

        if (item == null || !item.hasItemMeta()) return;

        final ItemBuilder itemBuilder = new ItemBuilder(item);

        if(!itemBuilder.hasNbt("kit-id")) return;

        final Player player = (Player) event.getWhoClicked();

        final String name = itemBuilder.getNbt("kit-id");

        final Kit kit = instance.getCache().get(name);
        if (kit == null) return;

        if (event.getClick().isLeftClick()) {
            Arrays.stream(kit.getItems()).forEach(kitItem -> player.getInventory().addItem(kitItem));

            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 10.0f, 99.0f);
            player.sendMessage("§aKit " + name + " §acoletado.");
            player.closeInventory();

        } else if (event.getClick().isRightClick()) {
            final InventoryBuilder inventoryBuilder = new InventoryBuilder("§8Kit " + name, 3);

            inventoryBuilder.registerClick(instance, kitPreviewEvent-> {
                kitPreviewEvent.setCancelled(true);
            });

            Arrays.stream(kit.getItems()).forEach(kitItem -> {
                if (inventoryBuilder.build().firstEmpty() == -1) return;
                inventoryBuilder.addItem(kitItem, inventoryBuilder.build().firstEmpty());
            });

            player.openInventory(inventoryBuilder.build());
        }

    }
}
