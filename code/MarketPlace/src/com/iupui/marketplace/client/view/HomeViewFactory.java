package com.iupui.marketplace.client.view;

/**
 * Created by anaya on 2/14/2017.
 */

 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are included in each file.
 
public class HomeViewFactory extends MarketplaceViewAbstractFactory {
    @Override
    public MarketplaceView getView(String type) {
        MarketplaceView marketplaceView;
		
		// if the userType is admin then admin view is displayed
        if(type.equals("ADMIN")){
            marketplaceView = new AdminHomeView();
            return marketplaceView;
        }
		// if user type is customer then customer view is displayed
        else if(type.equals("CUSTOMER")){
            marketplaceView = new CustomerHomeView();
            return marketplaceView;
        }
        else{
            return null;
        }
    }
}
