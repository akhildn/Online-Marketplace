package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.MarketplaceHandler;
import com.iupui.marketplace.client.handlers.UpdateProductHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/22/2017.
 */

// Concrete Command
public class UpdateProductCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public UpdateProductCommand(UpdateProductHandler updateProductHandler) {
        this.handler = updateProductHandler;
    }

    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
