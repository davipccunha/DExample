package io.github.davipccunha.dexample.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class EconomyUser {

    private final String name;

    @Setter
    private double coins;

    public void addCoins(double value) {
        setCoins(coins + value);
    }

    public void removeCoins(double value) {
        setCoins((coins - value) < 0 ? 0 : coins - value);
    }
}
