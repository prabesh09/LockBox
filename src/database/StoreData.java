package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreData {
    private Connection connection;

    public StoreData(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS credentials (id INTEGER PRIMARY KEY AUTOINCREMENT, site TEXT, username TEXT, password TEXT)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeCredentials(String site, String username, String password) {
        String sql = "INSERT INTO credentials (site, username, password) VALUES (?, ?, ?)";

        try {
            createTable();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, site);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}