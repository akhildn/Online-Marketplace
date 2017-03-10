package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;

/**
 * Created by anaya on 2/14/2017.
 */

 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are included in each file.

public class HomeViewFactory extends MarketplaceViewAbstractFactory {

    private MarketplaceFrontController frontController;

    public HomeViewFactory(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public MarketplaceView getView(String type) {
        MarketplaceView marketplaceView;
        // if the userType is admin then admin view is displayed
        if(type.equals("ADMIN")){
            marketplaceView = new AdminHomeView(frontController);
            return marketplaceView;
        }
        // if user type is customer then customer view is displayed
        else if(type.equals("CUSTOMER")){
            marketplaceView = new CustomerHomeView(frontController);
            return marketplaceView;
        }
        else{
            return null;
        }
    }
}
