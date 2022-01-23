package io.github.davipccunha.dexample.cache;

import com.google.common.collect.Maps;
import io.github.davipccunha.dexample.models.Kit;

import java.util.Collection;
import java.util.Map;

public class KitCache {
    private final Map<String, Kit> cache = Maps.newHashMap();

    public void put(String name, Kit kit) {
        cache.put(name, kit);
    }

    public Kit get(String name) {
        return cache.get(name);
    }

    public void remove(String name) {
        cache.remove(name);
    }

    public Collection<Kit> values() {
        return cache.values();
    }
}
