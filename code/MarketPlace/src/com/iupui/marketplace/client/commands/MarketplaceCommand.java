package com.iupui.marketplace.client.commands;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/15/2017.
 */
public interface MarketplaceCommand {
    public void execute() throws RemoteException;
}


