package io.github.davipccunha.dexample.listeners;

import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.models.EconomyUser;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class PlayerDataListener implements Listener {

    private final ExamplePlugin instance;

    @EventHandler
    private void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        instance.getEconomyDatabaseService().getUserService().loadUser(event.getName());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerQuit(PlayerQuitEvent event) {
        EconomyUser economyUser = instance.getEconomyUserCache().get(event.getPlayer().getName());

        if (economyUser == null) return;

        instance.getEconomyUserCache().remove(economyUser.getName());

        CompletableFuture.runAsync(() -> instance.getEconomyDatabaseService().getUserService().saveUser(economyUser));
    }
}
