package com.iupui.marketplace.client;

import com.iupui.marketplace.client.view.*;
import com.iupui.marketplace.model.beans.Account;
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
            else if(page.equals("BROWSE")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                BrowseView browseView = (BrowseView) factoryView.getView("BROWSE");
                browseView.bindData((List<Product>) object);
                browseView.show();
            }
            else if(page.equals("PRODUCT_DETAILS")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                ProductDetailView productDetailView = (ProductDetailView) factoryView.getView("PRODUCT_DETAILS");
                productDetailView.bindData((Product) object);
                productDetailView.show();
            }
            else if(page.equals("CART_VIEW")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                ShoppingCartView shoppingCartView = (ShoppingCartView) factoryView.getView("CART_VIEW");
                shoppingCartView.bindData((ShoppingCart) object);
                shoppingCartView.show();
            }
            else if(page.equals("ADD_ITEM")){
                MarketplaceViewAbstractFactory factoryView = new MarketplaceViewFactory(frontController);
                AddItemView addItemView = (AddItemView) factoryView.getView("ADD_ITEM");
                addItemView.show();
            }
            else if (page.equals("INVALID_CREDENTIALS")) {
                MarketplaceViewAbstractFactory factoryView = new ErrorViewFactory(frontController);
                factoryView.getView(page).show();
            }
        } catch (Exception ex) {
            MarketplaceViewAbstractFactory factoryView = new ErrorViewFactory(frontController);
            System.out.println(ex.getMessage());
           // ex.printStackTrace();
             factoryView.getView("PAGE_NOT_FOUND").show();
        }
    }
}
