package com.iupui.marketplace.client;

import com.iupui.marketplace.controller.MarketplaceController;
import com.iupui.marketplace.model.beans.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

// Ryan: Please include useful comments in each file.
//Fixed: Comments are included in each file.
public class MarketplaceFrontController {

    // Stores users account details
    private Account session;

    // to check if session is still maintained, set to true when authenticate is successful
    private boolean isAuthenticate;
    private MarketplaceDispatcher dispatcher;

    // to access MarketplaceControllerImpl
    private MarketplaceController controller;

    public MarketplaceFrontController(MarketplaceController controller){
        this.controller = controller;
        this.dispatcher = new MarketplaceDispatcher();
    }

    // method to set to authenticate and set dispatch request
    public void authenticate(String uname,String pass) throws RemoteException {
        Account account = null;
        try {
            account = controller.handleLogin(uname,pass);
            // account object is not null then login credentials are valid and Home is requested.
            if(account!=null){
                isAuthenticate = true;
                this.session=account;
                dispatchRequest("HOME", session);
            }
            // when account type is null i.e. no user details with entered credentials then set page ="INVALID_CREDENTIALS"
            else{
                isAuthenticate = false;
                dispatchRequest("INVALID_CREDENTIALS", session);
            }
        } catch (SQLException e) {
            isAuthenticate = false;
            dispatchRequest("PAGE_NOT_FOUND", session);
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

    // gets list of products from db and dispatches browse view
    public void handleBrowseItems() throws RemoteException {
        if(isAuthenticate) {
            try {
                List<Product> productList;
                productList = controller.handleBrowseItems();
                dispatcher.dispatch("BROWSE", productList, this);
            }catch (SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }

    // gets details of product which user has selected in browse view
    // the object is then passed to product detail view where all the product details are displayed
    public void handleProductDetails(int productId) throws RemoteException {
        if(isAuthenticate){
            try {
                Product product = controller.handlegetProductDetails(productId);
                dispatcher.dispatch("PRODUCT_DETAILS", product, this);
            }catch (SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }


    // get shopping cart object which is attached to current user in db(Hashmap in server)
    // this object is passed to view to display items and cart total of the cart
    public void handleViewCart() throws RemoteException {
        try {
            ShoppingCart shoppingCart = controller.handleGetCartDetails(session);
            dispatcher.dispatch("CART_VIEW", shoppingCart, this);
        }catch(SQLException e){
            dispatchRequest("PAGE_NOT_FOUND", session);
        }
    }

    // adds the product to shopping cart object and shopping cart view is called
    public void handleAddCart(Product product, int quantity) throws RemoteException {
        if(isAuthenticate){
            try {
                boolean isAdded = controller.handleAddToCart(session, product, quantity);
                if (isAdded) {
                    handleViewCart();
                }
            }catch(SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }

    // redirects to home page
    public void homeRedirect() throws RemoteException {
        if(isAuthenticate) {
            dispatcher.dispatch("HOME", session, this);
        }
    }


    // Admin specific view to add item to inventory
    public void handleAddItemView() throws RemoteException {
        if(isAuthenticate){
            dispatcher.dispatch("ADD_ITEM", null, this);
        }
    }

    // passes product object which contains details of new product that admin wants to add to inventory
    public boolean handleAddItem(Product product) throws RemoteException {
        if(isAuthenticate){
            try {
                boolean isProductAdded = controller.handleAddItem(session, product);
                return isProductAdded;
            }catch (SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
        return false;
    }

    // processes order and go to purchase view where it displays which items where places and which were not
    public void handlePurchase(ShoppingCart shoppingCart, String shippingAddress) throws RemoteException {
        if(isAuthenticate) {
            try {
                Callable<Order> callable = new Callable<Order>() {

                    @Override
                    public Order call() throws Exception {
                        // TODO Auto-generated method stub
                        return controller.handlePlaceOrder(session, shoppingCart, shippingAddress);
                    }
                };
                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
                Future<Order> future = executor.submit(callable);
                System.out.println("Placing order, please wait....");
                while(!future.isDone()) {
                }
                System.out.println("Order placed redirecting to order details page");
                Order order = future.get();
                dispatcher.dispatch("ORDER_CONFIRMATION", order, this);
            }catch (Exception e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }

    // to get history of orders which placed by the user
    public void handlePurchaseHistory() throws RemoteException {
        if(isAuthenticate){
            try {
                List<Order> orderList = controller.handleGetOrderHistory(session);
                dispatcher.dispatch("ORDER_HISTORY", orderList, this);
            }catch(SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }

    public void handleClearCart(int cartId) throws RemoteException {
        try {
            ShoppingCart shoppingCart = controller.handleClearCart(session, cartId);
            dispatcher.dispatch("CART_VIEW", shoppingCart, this);
        } catch (SQLException e) {
            dispatchRequest("PAGE_NOT_FOUND", session);
        }
    }


    public void handleRemoveProductView() throws RemoteException {
        if(isAuthenticate){
            try {
                List<Product> productList;
                productList = controller.handleBrowseItems();
                dispatcher.dispatch("REMOVE_PRODUCT", productList, this);
            }catch (SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }

    public void handleUpdateProductView() throws RemoteException {
        if(isAuthenticate){
            try {
                List<Product> productList;
                productList = controller.handleBrowseItems();
                dispatcher.dispatch("UPDATE_PRODUCT", productList, this);
            }catch (SQLException e){
                dispatchRequest("PAGE_NOT_FOUND", session);
            }
        }
    }


    public boolean handleUpdateProduct(Product product) throws RemoteException {
        boolean isUpdated = false;
        try {
             isUpdated = controller.handleUpdateProduct(session, product);
        }catch (SQLException e){
            dispatchRequest("PAGE_NOT_FOUND", session);
        }
        return isUpdated;
    }

    public boolean handleRemoveProduct(int productId) throws RemoteException {
        boolean isRemoved = false;
        try {
            isRemoved = controller.handleRemoveProduct(session, productId);
        }catch (SQLException e){
            dispatchRequest("PAGE_NOT_FOUND", session);
        }
        return isRemoved;

    }
}