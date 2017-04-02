package com.iupui.marketplace.client.command;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
 
// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.

// Command Invoker.
public class CommandInvoker {

    public void invoke(MarketplaceCommand command) throws RemoteException {
        command.execute();
    }
}
