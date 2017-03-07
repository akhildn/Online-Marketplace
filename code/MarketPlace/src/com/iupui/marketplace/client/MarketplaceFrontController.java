package com.iupui.marketplace.client;

import com.iupui.marketplace.controller.MarketplaceController;
import com.iupui.marketplace.model.beans.Account;

import java.rmi.RemoteException;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.

public class MarketplaceFrontController {

    private boolean isAuthenticate; // set to true when authenticate is successfull
    private MarketplaceDispatcher dispatcher;
    private MarketplaceController controller;

    public MarketplaceFrontController(MarketplaceController controller){
        this.controller = controller;
        this.dispatcher = new MarketplaceDispatcher();
    }

	// method to set to authenticate and set dispatch request
    public void authenticate(String uname,String pass) throws RemoteException {
       Account account = controller.handleLogin(uname,pass);
	   
	   // account object is not null then login credentials are valid and Home is requested.
       if(account!=null){
            isAuthenticate = true;
            dispatchRequest("HOME", account);
       }
	   
	   // when account type is null i.e. no user details with entered credentials then set page ="INVALID_CREDENTIALS"
       else{
            isAuthenticate = false;
           dispatchRequest("INVALID_CREDENTIALS", account);
       }

    }

	// returns status of authentication for future references
    private boolean isAuthenticUser() {
        return isAuthenticate;
    }

	// Responsible for dispatching the request to the Dispatcher
     public void dispatchRequest(String page, Account account) throws RemoteException {
        if(isAuthenticUser()) {
            dispatcher.dispatch(page,account);
        }
        else if(page.equals("INVALID_CREDENTIALS")){
            dispatcher.dispatch(page,account);
        }
    }


    public void browseItems() throws RemoteException {
        //TODO: Browse functionality to be implemented
        dispatcher.dispatch("BROWSE",null);
    }
}