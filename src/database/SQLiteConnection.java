package database;

import java.io.*;
import java.sql.*;

public class SQLiteConnection {
    private Connection connection;

    public SQLiteConnection(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");

            File databaseFile = new File(dbName);
            boolean databaseExists = databaseFile.exists();

            if (!databaseExists) {
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
                System.out.println("Database created successfully.");
            } else {
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
                System.out.println("Connected to existing database.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}