package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.AddCartHandler;
import com.iupui.marketplace.client.handlers.MarketplaceHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/1/2017.
 */
public class AddCartCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public AddCartCommand(AddCartHandler handler) {
        this.handler = handler;

    }
    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
