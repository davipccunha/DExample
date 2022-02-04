package io.github.davipccunha.dexample.loaders;

import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.Kit;
import me.pedro.aguiar.library.builder.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class KitLoader {

    public void load(ExamplePlugin instance) {
        instance.getConfig().getConfigurationSection("kit").getKeys(false).forEach(kit -> {
            Set<ItemStack> items = new HashSet<>();

            instance.getConfig().getStringList("kit." + kit + ".items").forEach(i -> {
                String[] separator = i.split(" ");
                String[] itemID = separator[0].split(":");

                ItemStack itemStack = new ItemStack(Material.getMaterial(Integer.parseInt(itemID[0])),
                        Integer.parseInt(separator[1]),
                        (short) 1,
                        Byte.parseByte(itemID[1]));

                items.add(itemStack);
            });

            String[] id = instance.getConfig().getString("kit." + kit + ".icon").split(":");
            ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(id[0])), 1, Short.parseShort(id[1]));

            final ItemBuilder itemBuilder = new ItemBuilder(item);

            itemBuilder.setDisplayName("§eKit " + kit);
            itemBuilder.setLore("§7- Clique com o esquerdo para coletar", "§7- Clique com o direito para ver o conteúdo");
            itemBuilder.addNbt("kit-id", kit);

            instance.getKitCache().put(kit, new Kit(items.toArray(ItemStack[]::new),
                    itemBuilder.build(), instance.getConfig().getInt("kit." + kit + ".slot")));
        });
    }
}
