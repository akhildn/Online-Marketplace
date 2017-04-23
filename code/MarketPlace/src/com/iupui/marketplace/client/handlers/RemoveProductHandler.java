package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/22/2017.
 */
public class RemoveProductHandler implements MarketplaceHandler {
    private  MarketplaceFrontController frontController;
    public RemoveProductHandler(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void handleRequest() throws RemoteException {
        frontController.handleRemoveProductView();
    }
}
