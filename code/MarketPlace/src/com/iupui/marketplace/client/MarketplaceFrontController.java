package com.iupui.marketplace.client;

import com.iupui.marketplace.controller.MarketplaceController;
import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.server.AuthorizationException;

import java.rmi.RemoteException;

// Ryan: Please include useful comments in each file.
//Fixed: Comments are included in each file.
public class MarketplaceFrontController {

    private Account session;
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
           this.session=account;
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
            dispatcher.dispatch(page,account,this);
        }
        else if(page.equals("INVALID_CREDENTIALS")){
            dispatcher.dispatch(page,account,this);
        }
    }


    public void browseItems() throws RemoteException {
        dispatcher.dispatch("BROWSE",null, this);
    }

    // communicates with handleEditItemName in the server.
    public void editItems() throws RemoteException {
        try{
         System.out.print("enter edit");
        controller.handleEditItemName(session,123,"New_Name");
        }catch (Exception e){
            System.out.println("Authorization Exception : "+e.getMessage());
        }
    }

    // communicates with handleAddToCart in the server.
    public void addCart() throws RemoteException {
        try {
            controller.handleAddToCart(session, 123, 1);
        }catch (Exception e){
            System.out.println("Authorization Exception : "+e.getMessage());
        }
    }
}