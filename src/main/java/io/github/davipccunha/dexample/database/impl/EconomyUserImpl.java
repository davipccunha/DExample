package io.github.davipccunha.dexample.database.impl;

import com.zaxxer.hikari.HikariDataSource;
import io.github.davipccunha.dexample.cache.EconomyUserCache;
import io.github.davipccunha.dexample.database.services.EconomyUserService;
import io.github.davipccunha.dexample.models.EconomyUser;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class EconomyUserImpl implements EconomyUserService {

    private final HikariDataSource hikariDataSource;

    private final EconomyUserCache cache;

    @Override
    public void loadUser(String username) {
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement preparedStatement = statement(username, connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            EconomyUser economyUser = new EconomyUser(username);

            if (resultSet.next()) {
                economyUser.setCoins(resultSet.getDouble("coins"));
            } else {
                saveUser(economyUser);
            }

            cache.put(username, economyUser);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void saveUser(EconomyUser user) {
        final String query = "INSERT INTO economy (username, coins) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE coins = VALUES(coins)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setDouble(2, user.getCoins());

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private PreparedStatement statement(String username, Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT coins FROM economy WHERE username='" + username + "'");
    }
}
