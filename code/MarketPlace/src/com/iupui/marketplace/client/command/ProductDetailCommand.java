package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.MarketplaceHandler;
import com.iupui.marketplace.client.handlers.ProductDetailHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/1/2017.
 */
public class ProductDetailCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public ProductDetailCommand(ProductDetailHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
