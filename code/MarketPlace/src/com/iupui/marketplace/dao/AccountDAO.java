package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;

import java.util.HashMap;

// Ryan: Please include usefull comments in each file.
public class AccountDAO {

    HashMap<String, Account> userDataMap;

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

    public boolean validateCredentials(String username, String password) {
        Account account = userDataMap.get(username);
        if (account!=null && account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public Account getAccountDetails(String username) {
        return userDataMap.get(username);
    }
}
