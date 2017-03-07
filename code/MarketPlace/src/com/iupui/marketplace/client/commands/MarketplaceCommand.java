package com.iupui.marketplace.client.commands;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/15/2017.
 */
 
 // Ryan: Please include usefull comments in each file.
 // Fixed: Comments are provided in each file.
 
 // Command pattern interface
public interface MarketplaceCommand {
    public void execute() throws RemoteException;
}


