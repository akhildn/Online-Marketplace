package com.iupui.marketplace.client;

import com.iupui.marketplace.client.view.*;
import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Order;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by anaya on 2/12/2017.
 */

 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are included in each file.
public class MarketplaceDispatcher {

    // FrontController dispatcher and also factory producer for abstract factory
    // Based upon the request - dispatch the view.
    public void dispatch(String page, Object object, MarketplaceFrontController frontController) throws RemoteException {
        try {
            // gets HomeView if page type is home, i.e when authentication is valid
            if (page.equals("HOME")) {
                MarketplaceViewAbstractFactory factoryView = new HomeViewFactory(frontController);
                Account account = (Account) object;
                // calls respective view based on account type i.e. CustomerView is type is customer,
                // AdminView if type is admin
                factoryView.getView(account.getUserType()).show();
            }
            // gets browse view and list of products which are to be displayed are passed in
            else if(page.equals("BROWSE")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                BrowseView browseView = (BrowseView) factoryView.getView("BROWSE");
                // passes the product list which has all the products which are in inventory
                browseView.bindData((List<Product>) object);
                browseView.show();
            }
            // product view an individual product i.e the view when a product is selected, object of that product is
            // passed to display its details
            else if(page.equals("PRODUCT_DETAILS")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                ProductDetailView productDetailView = (ProductDetailView) factoryView.getView("PRODUCT_DETAILS");
                // passes product details of a specific product which was selected
                productDetailView.bindData((Product) object);
                productDetailView.show();
            }
            // Cart view, called when product is added to cart or when user wants to view cart
            else if(page.equals("CART_VIEW")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                ShoppingCartView shoppingCartView = (ShoppingCartView) factoryView.getView("CART_VIEW");
                // passes shopping cart object which contains all items which are in the cart
                shoppingCartView.bindData((ShoppingCart) object);
                shoppingCartView.show();
            }
            // this view is available to admin, it is to add new item into inventory
            else if(page.equals("ADD_ITEM")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                AddItemView addItemView = (AddItemView) factoryView.getView("ADD_ITEM");
                addItemView.show();
            }
            // View after user requests purchase, this view displays items which are successfully placed and those
            // which were not due to some reason
            else if (page.equals("ORDER_CONFIRMATION")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                OrderConfirmationView orderConfirmationView = (OrderConfirmationView)
                        factoryView.getView("ORDER_CONFIRMATION");
                // passes order details
                orderConfirmationView.bindData((Order) object);
                orderConfirmationView.show();
            }
            // Order history view, displays all orders which were that specific user placed so far
            else if(page.equals("ORDER_HISTORY")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                PurchaseHistoryView purchaseHistoryView = (PurchaseHistoryView)
                        factoryView.getView("ORDER_HISTORY");
                // passes list of orders which placed by the user up to date
                purchaseHistoryView.bindData((List<Order>) object);
                purchaseHistoryView.show();
            }
            // view which is displayed login fails i.e. when username or password does not match with details in DB
            else if (page.equals("INVALID_CREDENTIALS")) {
                MarketplaceViewAbstractFactory factoryView = new ErrorViewFactory(frontController);
                factoryView.getView(page).show();
            }
        }
        // Common exception
        catch (Exception ex) {
            MarketplaceViewAbstractFactory factoryView = new ErrorViewFactory(frontController);
            // Authorization handler exception message
            System.out.println(ex.getMessage());
            ex.printStackTrace();
             factoryView.getView("PAGE_NOT_FOUND").show();
        }
    }
}
