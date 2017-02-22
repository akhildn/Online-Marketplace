package com.iupui.marketplace.client;

import com.iupui.marketplace.controller.MarketplaceController;
import com.iupui.marketplace.model.beans.Account;

import java.rmi.RemoteException;

// Ryan: Please include usefull comments in each file.
public class MarketplaceFrontController {

    private boolean isAuthenticate;
    private MarketplaceDispatcher dispatcher;
    private MarketplaceController controller;

    public MarketplaceFrontController(MarketplaceController controller){
        this.controller = controller;
        this.dispatcher = new MarketplaceDispatcher();
    }


    public void authenticate(String uname,String pass) throws RemoteException {
       Account account = controller.handleLogin(uname,pass);
       if(account!=null){
            isAuthenticate = true;
            dispatchRequest("HOME", account);
       }
       else{
            isAuthenticate = false;
           dispatchRequest("INVALID_CREDENTIALS", account);
       }

    }

    private boolean isAuthenticUser() {
        return isAuthenticate;
    }

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