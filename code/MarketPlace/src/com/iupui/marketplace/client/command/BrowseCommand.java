package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.BrowseHandler;
import com.iupui.marketplace.client.handlers.MarketplaceHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
 
// Ryan: Please include useful comments in each file.
// Fixed: Comments are provided in each file.

// Concrete Command
public class BrowseCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public BrowseCommand(BrowseHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
