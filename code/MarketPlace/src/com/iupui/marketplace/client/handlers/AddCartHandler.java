package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/1/2017.
 */
public class AddCartHandler implements MarketplaceHandler{

    private  MarketplaceFrontController frontController;
    private Product product;
    private int quantity;
    public AddCartHandler(MarketplaceFrontController frontController, Product product, int quantity) {
        this.frontController = frontController;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public void handleRequest() throws RemoteException {
        frontController.addCart(product,quantity);
    }
}
