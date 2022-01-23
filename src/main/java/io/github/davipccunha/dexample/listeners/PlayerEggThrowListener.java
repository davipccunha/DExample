package io.github.davipccunha.dexample.listeners;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerEggThrowListener implements Listener {

    private final EntityType[] entityTypes = { EntityType.COW, EntityType.PIG, EntityType.OCELOT,
            EntityType.MUSHROOM_COW, EntityType.SHEEP, EntityType.WOLF } ;

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @EventHandler()
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {

        if (event.isHatching()) {
            Arrays.stream(entityTypes)
                    .skip((long) (entityTypes.length * random.nextDouble(1.0)))
                    .findFirst()
                    .ifPresent(entityType -> {
                        Animals entity = (Animals) event.getEgg()
                                .getWorld()
                                .spawnEntity(event.getEgg().getLocation(), entityType);

                        entity.setBaby();
                    });

        }
        event.setHatching(false);

        final Location eggLocation = event.getEgg().getLocation();

        eggLocation.setYaw(event.getPlayer().getLocation().getYaw());
        eggLocation.setPitch(event.getPlayer().getLocation().getPitch());

        event.getPlayer().teleport(eggLocation);

    }
}
