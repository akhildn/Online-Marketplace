package com.iupui.marketplace.client.commands;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/16/2017.
 */
 
// Ryan: Please include usefull comments in each file.
public class CommandInvoker {
    public void invoke(MarketplaceCommand command) throws RemoteException {
        command.execute();
    }
}
