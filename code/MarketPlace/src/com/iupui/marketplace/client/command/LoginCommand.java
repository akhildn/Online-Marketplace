package com.iupui.marketplace.client.command;

import com.iupui.marketplace.client.handlers.MarketplaceHandler;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
 
 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are included in each file.

 // Concrete Command
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
