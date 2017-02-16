package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
public class LoginHandler implements MarketplaceHandler {

    private String uname;
    private String pass;

    MarketplaceFrontController frontController;
    public LoginHandler(MarketplaceFrontController frontController){
        this.frontController = frontController;
    }

    // username
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    //password
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }


    public void handleRequest() throws RemoteException {
        frontController.authenticate(this.uname, this.pass);
    }

}
