package com.iupui.marketplace.client;

import com.iupui.marketplace.client.view.MarketplaceViewAbstractFactory;
import com.iupui.marketplace.client.view.ErrorViewFactory;
import com.iupui.marketplace.client.view.HomeViewFactory;
import com.iupui.marketplace.model.beans.Account;

import java.rmi.RemoteException;

/**
 * Created by anaya on 2/12/2017.
 */

 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are included in each file.
 
public class MarketplaceDispatcher {
	
	// FrontController dispatcher 
    // Based upon the request - dispatches the view.
    public void dispatch(String page, Account account) throws RemoteException {
        try {
			// gets HomeView if page type is home, i.e when authentication is valid
            if (page.equals("HOME")) {
                MarketplaceViewAbstractFactory factoryView = new HomeViewFactory();
				// calls respective view based on account type i.e. customerview is type is customer,
				// adminview if type is admin
                factoryView.getView(account.getUserType()).show();
            }
            else if(page.equals("BROWSE")){
				//TODO: to be implemented
            }
			
            else if (page.equals("INVALID_CREDENTIALS")) {
                MarketplaceViewAbstractFactory factoryView = new ErrorViewFactory();
                factoryView.getView(page).show();
            }
        } catch (Exception ex) {
            MarketplaceViewAbstractFactory factoryView = new ErrorViewFactory();
            factoryView.getView("PAGE_NOT_FOUND").show();
        }
    }
}
