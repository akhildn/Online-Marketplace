package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.PurchaseHandler;
import com.iupui.marketplace.client.handlers.MarketplaceHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/2/2017.
 */
public class PurchaseCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public PurchaseCommand(PurchaseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
