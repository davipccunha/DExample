package io.github.davipccunha.dexample.cache;

import com.google.common.collect.Maps;
import io.github.davipccunha.dexample.models.EconomyUser;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public class EconomyUserCache {

    private final Map<String, EconomyUser> cache = Maps.newConcurrentMap();

    public void put(String username, EconomyUser economyUser) {
        cache.put(username, economyUser);
    }

    public void remove(String username) {
        cache.remove(username);
    }

    @Nullable
    public EconomyUser get(String username) {
        return cache.get(username);
    }

    public Collection<EconomyUser> values() {
        return cache.values();
    }

}
