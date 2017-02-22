package com.iupui.marketplace.client.view;

/**
 * Created by anaya on 2/14/2017.
 */
 // Ryan: Please include usefull comments in each file.
public class HomeViewFactory extends AbstractFactoryView {
    @Override
    public MarketplaceView createView(String type) {
        MarketplaceView marketplaceView;
        if(type.equals("ADMIN")){
            marketplaceView = new AdminHomeView();
            return marketplaceView;
        }
        else if(type.equals("CUSTOMER")){
            marketplaceView = new CustomerHomeView();
            return marketplaceView;
        }
        else{
            return null;
        }
    }
}
