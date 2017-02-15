package com.iupui.marketplace.client.view;

/**
 * Created by anaya on 2/14/2017.
 */
public class HomeViewFactory extends AbstractFactoryView {
    @Override
    public MarketplaceView createView(String type) {
        MarketplaceView marketplaceView;
        if(type.equals("ADMIN")){
            marketplaceView = new AdminView();
            return marketplaceView;
        }
        else if(type.equals("CUSTOMER")){
            marketplaceView = new CustomerView();
            return marketplaceView;
        }
        else{
            return null;
        }
    }
}
