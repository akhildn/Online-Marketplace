package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/22/2017.
 */
public class UpdateProductHandler implements MarketplaceHandler {

    private  MarketplaceFrontController frontController;
    public UpdateProductHandler(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void handleRequest() throws RemoteException {
        frontController.handleUpdateProductView();
    }
}
