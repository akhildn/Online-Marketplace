package com.iupui.marketplace.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Created by anaya on 4/17/2017.
 */

// Database connection class
public class MarketplaceDBConnection {

    public Connection connection = null;
    public static MarketplaceDBConnection dbConnection;
    private Statement statement;

    private MarketplaceDBConnection(){
        // credentials and connection parameters for mysql connection through jdbc
        String hostname = "localhost:3306";
        String dbName = "anayabu_db";
        String url = "jdbc:mysql://" + hostname + "/" + dbName;
        String username = "anayabu";
        String password = "marketplace";
        System.out.println("Connecting database...");
        try {
            // register driver
            Class.forName("com.mysql.jdbc.Driver");
            // connects to DB
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        } catch (ClassNotFoundException e) {
           throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static synchronized MarketplaceDBConnection getMarketplaceDbConnection() {
        if ( dbConnection == null ) {
            System.out.println("creating new db object");
            dbConnection = new MarketplaceDBConnection();
        }
        System.out.println("reusing same db object");
        return dbConnection;
    }

}
