package com.iupui.marketplace.client.view;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/14/2017.
 */
 
// Interface for each view 
public interface MarketplaceView {
    public void show() throws RemoteException;
}
