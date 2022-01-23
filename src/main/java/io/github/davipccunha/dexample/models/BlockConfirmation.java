package io.github.davipccunha.dexample.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;

@Getter
@RequiredArgsConstructor
public class BlockConfirmation {
    private final Material material;
    private final Location location;
}
