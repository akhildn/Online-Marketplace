package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;

import java.util.HashMap;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.

// performs retrieval and creation of accounts
public class AccountDAO {

    // To store mock user details
    HashMap<String, Account> userDataMap;

    // Mock Up User Credentials for assignment#2 and #3
    public AccountDAO() {
        userDataMap = new HashMap<String, Account>();

        //Customers
        Account customer = new Account();
        Account customer1= new Account();
        Account customer2= new Account();
        Account customer3= new Account();
        Account customer4= new Account();

        //Customers details

        customer.setEmail("abc@abc.com");
        customer.setPassword("user");
        customer.setSecurityAnswer("answerC");
        customer.setUsername("user1");
        customer.setUserType("CUSTOMER");

        customer1.setEmail("abc1@abc.com");
        customer1.setPassword("user");
        customer1.setSecurityAnswer("answerC");
        customer1.setUsername("user2");
        customer1.setUserType("CUSTOMER");

        customer2.setEmail("abc3@abc.com");
        customer2.setPassword("user");
        customer2.setSecurityAnswer("answerC");
        customer2.setUsername("user3");
        customer2.setUserType("CUSTOMER");

        customer3.setEmail("abc@4abc.com");
        customer3.setPassword("user");
        customer3.setSecurityAnswer("answerC");
        customer3.setUsername("user4");
        customer3.setUserType("CUSTOMER");

        customer4.setEmail("abc5@abc.com");
        customer4.setPassword("user");
        customer4.setSecurityAnswer("answerC");
        customer4.setUsername("user5");
        customer4.setUserType("CUSTOMER");

        //Admin
        Account admin = new Account();

        //Admin details
        admin.setEmail("abc2@abc.com");
        admin.setPassword("admin");
        admin.setSecurityAnswer("answerA");
        admin.setUsername("admin");
        admin.setUserType("ADMIN");

        // adds all users to the data structure
        userDataMap.put("user1", customer);
        userDataMap.put("user2",customer1);
        userDataMap.put("user3",customer2);
        userDataMap.put("user4",customer3);
        userDataMap.put("user5",customer4);
        userDataMap.put("admin", admin);
    }

    // credential validation from mock up details
    public boolean validateCredentials(String username, String password) {
        Account account = userDataMap.get(username);
        if (account!=null && account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // returns all user details of logged in user
    public Account getAccountDetails(String username) {
        return userDataMap.get(username);
    }
}
