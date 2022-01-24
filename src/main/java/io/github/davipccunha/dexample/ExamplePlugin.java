package io.github.davipccunha.dexample;

import io.github.davipccunha.dexample.cache.KitCache;
import io.github.davipccunha.dexample.commands.*;
import io.github.davipccunha.dexample.inventories.KitGUI;
import io.github.davipccunha.dexample.listeners.*;
import io.github.davipccunha.dexample.loaders.KitLoader;
import io.github.davipccunha.dexample.loaders.NoteLoader;
import io.github.davipccunha.dexample.utils.NoteManagementUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

@Getter
public final class ExamplePlugin extends JavaPlugin {

    private final KitCache cache = new KitCache();
    private final KitGUI gui = new KitGUI(this);
    private final NoteManagementUtil noteManagement = new NoteManagementUtil(this);

    @SneakyThrows
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

    private void init() throws FileNotFoundException {
        saveDefaultConfig();

        initListeners();
        initCommands();
        loadKits();
        loadNotes();
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
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new PlayerDropItemListener(), this);
        pluginManager.registerEvents(new PlayerItemDamageListener(), this);
    }

    private void initCommands() {
        getCommand("hungry").setExecutor(new HungryCommand(this));
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("enderchest").setExecutor(new EnderChestCommand());
        getCommand("kit").setExecutor(new KitCommand(this));
        getCommand("note").setExecutor(new NoteCommand(this));
    }

    private void loadKits() {
        KitLoader kitLoader = new KitLoader();
        kitLoader.load(this);
    }

    private void loadNotes() throws FileNotFoundException {
        NoteLoader noteLoader = new NoteLoader();
        noteLoader.load(this);
    }
}
