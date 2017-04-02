package com.iupui.marketplace.client;

import com.iupui.marketplace.controller.MarketplaceController;
import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.rmi.RemoteException;
import java.util.List;

// Ryan: Please include useful comments in each file.
//Fixed: Comments are included in each file.
public class MarketplaceFrontController {

    private Account session;
    private boolean isAuthenticate; // set to true when authenticate is successfull
    private MarketplaceDispatcher dispatcher;
    private MarketplaceController controller;
    boolean onSuccess;

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
        if(isAuthenticate) {
            List<Product> productList;
            productList = controller.handleBrowseItems();
            dispatcher.dispatch("BROWSE", productList, this);
        }
        //TODO : is not authenticated display page not found
    }


    public void productDetails(int productId) throws RemoteException {
        if(isAuthenticate){
            Product product= controller.handlegetProductDetails(productId);
            dispatcher.dispatch("PRODUCT_DETAILS", product, this);
        }
    }


    public void addCart(Product product, int quantity) throws RemoteException {
        if(isAuthenticate){
            boolean isAdded = controller.handleAddToCart(session,product,quantity);
            if(isAdded){
                ShoppingCart shoppingCart = controller.handleGetCartDetails(session);
                dispatcher.dispatch("CART_VIEW", shoppingCart, this);
            }
        }
    }

    public void homeRedirect() throws RemoteException {
        dispatcher.dispatch("HOME", session, this);
    }

    public void addItemView() throws RemoteException {
        if(isAuthenticate){
            dispatcher.dispatch("ADD_ITEM", null, this);
        }
    }


    public boolean addItem(Product product) throws RemoteException {
        if(isAuthenticate){
            boolean isProductAdded = controller.handleAddItem(session, product);
            return isProductAdded;
        }
        return false;
    }
}