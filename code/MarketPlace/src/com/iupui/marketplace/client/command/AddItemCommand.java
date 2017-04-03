package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.AddItemHandler;
import com.iupui.marketplace.client.handlers.BrowseHandler;
import com.iupui.marketplace.client.handlers.MarketplaceHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 4/2/2017.
 */

// Concrete Command
public class AddItemCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public AddItemCommand(AddItemHandler handler) {
         this.handler = handler;
    }

    @Override
    public void execute() throws RemoteException {
         handler.handleRequest();
    }
}
