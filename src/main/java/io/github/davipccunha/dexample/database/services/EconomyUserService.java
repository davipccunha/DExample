package io.github.davipccunha.dexample.database.services;

import io.github.davipccunha.dexample.models.EconomyUser;

public interface EconomyUserService {

    void loadUser(String username);

    void saveUser(EconomyUser user);
}
