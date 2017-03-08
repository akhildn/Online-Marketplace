package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
 
 // Ryan: Please include usefull comments in each file.
 // Fixed: Comments are included in each file.
 
 // Command Receiver
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

	//  Passes username and password to authenticate method of front controller where authentication is initiated.
    public void handleRequest() throws RemoteException {
        frontController.authenticate(this.uname, this.pass);
    }

}
