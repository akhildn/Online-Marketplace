package com.iupui.marketplace.client.handlers;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are included in each file

 // Command Receiver
public class BrowseHandler implements MarketplaceHandler {

    private  MarketplaceFrontController controller;
    public  BrowseHandler(MarketplaceFrontController controller){
        this.controller = controller;
    }

    @Override
    public void handleRequest() throws RemoteException {
        controller.handleBrowseItems();
    }
}
