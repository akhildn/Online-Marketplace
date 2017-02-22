package com.iupui.marketplace.client;

import com.iupui.marketplace.client.handlers.BrowseHandler;
import com.iupui.marketplace.client.view.AbstractFactoryView;
import com.iupui.marketplace.client.view.ErrorViewFactory;
import com.iupui.marketplace.client.view.HomeViewFactory;
import com.iupui.marketplace.model.beans.Account;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/12/2017.
 */

 // Ryan: Please include usefull comments in each file.
public class MarketplaceDispatcher {

    public void dispatch(String page, Account account) throws RemoteException {
        try {
            if (page.equals("HOME")) {
                AbstractFactoryView factoryView = new HomeViewFactory();
                factoryView.createView(account.getUserType()).show();
            }
            else if(page.equals("BROWSE")){

            }
            else if (page.equals("INVALID_CREDENTIALS")) {
                AbstractFactoryView factoryView = new ErrorViewFactory();
                factoryView.createView(page).show();
            }
        } catch (Exception ex) {
            AbstractFactoryView factoryView = new ErrorViewFactory();
            factoryView.createView("PAGE_NOT_FOUND").show();
        }
    }
}
