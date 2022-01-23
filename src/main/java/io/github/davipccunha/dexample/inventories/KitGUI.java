package io.github.davipccunha.dexample.inventories;

import io.github.davipccunha.dexample.ExamplePlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@RequiredArgsConstructor
public class KitGUI {

    private final ExamplePlugin instance;

    public void open(Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 9 * 3, "§8Kits");

        instance.getCache().values().forEach(kit -> inventory.setItem(kit.getSlot(), kit.getIcon()));

        player.openInventory(inventory);
    }

}
