package com.iupui.marketplace.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Created by anaya on 4/17/2017.
 */

// Singleton database connection class
public class MarketplaceDBConnection {

    public Connection connection = null;
    public static volatile MarketplaceDBConnection dbConnection;

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

    public static MarketplaceDBConnection getMarketplaceDbConnection() {
        /*
        * synchronized keyword is used to make sure that only one DB connection object is created
        * even when 2 threads are trying to invoke this method at same time.
        * double scoping locking is implemented.
        * */
        if ( dbConnection == null ) {
            synchronized (MarketplaceDBConnection.class){
             /*
             * this null check is useful when 2 threads were invoked db connection at the same time
             * for the first time and db connection object is not initialized from previous clients.
             * only the first thread will create the object and 2nn will reuse the object created by 1st.
             * */
             if(dbConnection == null) {
                 dbConnection = new MarketplaceDBConnection();
                 System.out.println("creating new db object");
             }
            }
        }
        return dbConnection;
    }

}
