package org.software.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;
    private static final String URL = "jdbc:sqlite:ResturantManagment.db";

    private DatabaseManager() {
        try {
            // Initialize the database connection
            this.connection = DriverManager.getConnection(URL);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.err.println("Failed to create the database connection: " + e.getMessage());
        }
    }

    public static  DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error while closing the connection: " + e.getMessage());
            }
        }
    }
}
