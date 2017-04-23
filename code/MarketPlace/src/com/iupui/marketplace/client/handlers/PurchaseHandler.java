package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Address;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/2/2017.
 */

// Command Receiver
public class PurchaseHandler implements  MarketplaceHandler{

    private MarketplaceFrontController frontController;
    private ShoppingCart shoppingCart;
    private String shippingAddress;
    public PurchaseHandler(MarketplaceFrontController frontController, ShoppingCart shoppingCart, String shippingAddress) {
        this.frontController = frontController;
        this.shoppingCart = shoppingCart;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public void handleRequest() throws RemoteException {
            frontController.handlePurchase(shoppingCart,shippingAddress);
    }
}
