package com.iupui.marketplace.client.handlers;

import java.rmi.RemoteException;


/**
 * Created by anaya on 2/16/2017.
 */


 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are provided in each file.

 // Command Pattern Receiver Interface.
public interface MarketplaceHandler {
    public void handleRequest() throws RemoteException;
}
