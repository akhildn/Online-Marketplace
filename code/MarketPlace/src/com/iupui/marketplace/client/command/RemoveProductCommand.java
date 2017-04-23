package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.AddItemHandler;
import com.iupui.marketplace.client.handlers.MarketplaceHandler;
import com.iupui.marketplace.client.handlers.RemoveProductHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/22/2017.
 */
public class RemoveProductCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public RemoveProductCommand(RemoveProductHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
