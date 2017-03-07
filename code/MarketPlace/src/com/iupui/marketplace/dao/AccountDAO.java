package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;

import java.util.HashMap;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.

public class AccountDAO {

	// To store mock user details
    HashMap<String, Account> userDataMap;
	
	// Mock Up User Credentials for assignment#2 and #3
    public AccountDAO() {
        userDataMap = new HashMap<String, Account>();
        Account customer = new Account();
        Account admin = new Account();
        customer.setEmail("abc@abc.com");
        customer.setPassword("customer");
        customer.setSecurityAnswer("answerC");
        customer.setUsername("customer");
        customer.setUserType("CUSTOMER");


        admin.setEmail("abc2@abc.com");
        admin.setPassword("admin");
        admin.setSecurityAnswer("answerA");
        admin.setUsername("admin");
        admin.setUserType("ADMIN");


        userDataMap.put("customer", customer);
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
