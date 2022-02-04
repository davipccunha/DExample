package io.github.davipccunha.dexample.database.services;

import com.zaxxer.hikari.HikariDataSource;

public interface EconomyDatabaseService {
    EconomyUserService getUserService();

    HikariDataSource connect();

    void disconnect();

    void createTable();
}
