package com.condusef.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

public class DatabaseConnection {

    //quitar esta linea
    //private static final String URL = "jdbc:sqlserver://ADRIAN\\SQLEXPRESS;databaseName=Catalog;user=sa;password=12345678;trustServerCertificate=true";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            
            Config config = ConfigProvider.getConfig();
            String URL = config.getValue("quarkus.datasource.jdbc.url", String.class);
            String USER = config.getValue("quarkus.datasource.username", String.class);
            String PASSWORD = config.getValue("quarkus.datasource.password", String.class);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }else{
            System.out.println("Connection failed");
        
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
