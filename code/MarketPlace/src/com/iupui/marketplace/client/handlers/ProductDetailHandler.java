package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/1/2017.
 */

// Command Receiver
public class ProductDetailHandler implements MarketplaceHandler {
    private  MarketplaceFrontController frontController;
    private int productId;

    public ProductDetailHandler(MarketplaceFrontController frontController, int productId) {
        this.frontController = frontController;
        this.productId = productId;
    }

    @Override
    public void handleRequest() throws RemoteException {
        frontController.handleProductDetails(productId);
    }
}
