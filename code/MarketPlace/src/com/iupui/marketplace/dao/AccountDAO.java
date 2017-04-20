package com.iupui.marketplace.dao;

import com.iupui.marketplace.database.MarketplaceDBConnection;
import com.iupui.marketplace.model.beans.Account;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.

// performs retrieval and creation of accounts
public class AccountDAO {

    private Connection dbConnection;
    // gets database connection to maintain singleton pattern for DB
    public AccountDAO() {
        dbConnection = MarketplaceDBConnection.getMarketplaceDbConnection().getConnection();
    }

    // credential validation
    public boolean validateCredentials(String username, String password) throws SQLException {

        //checks if there is any user with given username and password
        String query = "select * from anayabu_db.account where username='"+username+"' and password='"+password+"'";
        Statement statement = (Statement) dbConnection.createStatement();
        // runs the query
        ResultSet resultSet = statement.executeQuery(query);
        // returns true if it gets details of account with given username and password
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    // returns all user details of logged in user
    public Account getAccountDetails(String username) throws SQLException {
        Account account = new Account();
        // gets account details
        String query = "select * from anayabu_db.account where username='"+username+"'";
        System.out.println(query);
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        // if true populates account object
        if(resultSet.next()){
            account.setUsername(resultSet.getString("username"));
            System.out.println("user:"+ account.getUsername());
            account.setPassword(resultSet.getString("password"));
            account.setEmail(resultSet.getString("email"));
            account.setUserType(resultSet.getString("user_type"));
            System.out.println("user:"+ account.getUserType());
            account.setSecurityQuestion(resultSet.getString("security_question"));
            account.setSecurityAnswer(resultSet.getString("security_answer"));
            return  account;
        }
        return null;
    }
}
