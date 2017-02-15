package com.iupui.marketplace.client;

import com.iupui.marketplace.client.view.AbstractFactoryView;
import com.iupui.marketplace.client.view.ErrorViewFactory;
import com.iupui.marketplace.client.view.HomeViewFactory;
import com.iupui.marketplace.model.beans.Account;

/**
 * Created by anaya on 2/12/2017.
 */

public class MarketplaceDispatcher {

    public void dispatch(String page, Account account) {
        try {
            if (page.equals("HOME")) {
                AbstractFactoryView factoryView = new HomeViewFactory();
                factoryView.createView(account.getUserType()).show();
            } else if (page.equals("INVALID_CREDENTIALS")) {
                AbstractFactoryView factoryView = new ErrorViewFactory();
                factoryView.createView(page).show();
            }
        } catch (Exception ex) {
            AbstractFactoryView factoryView = new ErrorViewFactory();
            factoryView.createView("PAGE_NOT_FOUND").show();
        }
    }
}
