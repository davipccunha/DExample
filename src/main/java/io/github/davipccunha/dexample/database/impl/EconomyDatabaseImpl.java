package io.github.davipccunha.dexample.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.davipccunha.dexample.ExamplePlugin;
import io.github.davipccunha.dexample.database.services.EconomyDatabaseService;
import io.github.davipccunha.dexample.database.services.EconomyUserService;
import lombok.Getter;
import me.pedro.aguiar.library.database.config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EconomyDatabaseImpl implements EconomyDatabaseService {

    private final HikariDataSource hikariDataSource;

    @Getter
    private final EconomyUserService userService;

    public EconomyDatabaseImpl(ExamplePlugin instance) {
        this.hikariDataSource = connect();

        createTable();

        this.userService = new EconomyUserImpl(hikariDataSource, instance.getEconomyUserCache());
    }

    @Override
    public HikariDataSource connect() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3307/dexample");
        hikariConfig.setPoolName("dexample-economy");
        hikariConfig.setMaximumPoolSize(8);
        hikariConfig.addDataSourceProperty("characterEncoding", "utf8");

        return new HikariDataSource(hikariConfig);
    }

    @Override
    public void disconnect() {
        if (hikariDataSource != null)
            hikariDataSource.close();
    }

    @Override
    public void createTable() {
        try (Connection conn = hikariDataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                     "economy (username VARCHAR(20), coins DOUBLE, PRIMARY KEY(username))")) {

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
