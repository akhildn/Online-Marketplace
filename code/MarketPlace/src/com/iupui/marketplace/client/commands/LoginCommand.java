package com.iupui.marketplace.client.commands;

import com.iupui.marketplace.client.handlers.MarketplaceHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
public class LoginCommand implements MarketplaceCommand {

    MarketplaceHandler handler;
    public LoginCommand(MarketplaceHandler handler){
        this.handler = handler;
    }
    @Override
    public void execute() throws RemoteException {
        handler.handleRequest();
    }
}
