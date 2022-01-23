package io.github.davipccunha.dexample;

import io.github.davipccunha.dexample.cache.KitCache;
import io.github.davipccunha.dexample.commands.EnderChestCommand;
import io.github.davipccunha.dexample.commands.FlyCommand;
import io.github.davipccunha.dexample.commands.HungryCommand;
import io.github.davipccunha.dexample.commands.KitCommand;
import io.github.davipccunha.dexample.inventories.KitGUI;
import io.github.davipccunha.dexample.listeners.*;
import io.github.davipccunha.dexample.loaders.KitLoader;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class ExamplePlugin extends JavaPlugin {

    private final KitCache cache = new KitCache();
    private final KitGUI gui = new KitGUI(this);


    @Override
    public void onEnable() {
        Bukkit.getLogger().info("QUÉ TIRA LADRÃO");
        init();
    }

    @Override
    public void onDisable() {
        System.out.println("puts desligou");
        HandlerList.unregisterAll(this);
    }

    private void init() {
        saveDefaultConfig();

        initListeners();
        initCommands();
        loadKits();
    }

    private void initListeners() {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new EntityDeathListener(), this);
        pluginManager.registerEvents(new PlayerBedEnterListener(), this);
        pluginManager.registerEvents(new PlayerEggThrowListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(this), this);
        pluginManager.registerEvents(new BlockPlaceListener(this), this);
    }

    private void initCommands() {
        getCommand("hungry").setExecutor(new HungryCommand(this));
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("kit").setExecutor(new KitCommand(this));
    }

    private void loadKits() {
        KitLoader kitLoader = new KitLoader();
        kitLoader.load(this);
    }
}
