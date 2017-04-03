package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/2/2017.
 */

// Command Receiver
public class AddItemHandler implements MarketplaceHandler {

    private MarketplaceFrontController frontController;
    public AddItemHandler(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void handleRequest() throws RemoteException {
        frontController.handleAddItemView();
    }
}
