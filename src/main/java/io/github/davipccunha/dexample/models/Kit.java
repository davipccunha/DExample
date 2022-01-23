package io.github.davipccunha.dexample.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
@Getter
public class Kit {

    private final ItemStack[] items;
    private final ItemStack icon;

    private final int slot;
}
