package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/12/2017.
 */
public class PageNotFoundView implements MarketplaceView {

    private MarketplaceFrontController frontController;
    public PageNotFoundView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    public void show() throws RemoteException {
        System.out.println("******************Page Not Found*********************");
        System.out.println("Redirecting to home page ....");
        frontController.homeRedirect();
    }
}
