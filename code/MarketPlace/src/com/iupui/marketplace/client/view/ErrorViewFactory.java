package com.iupui.marketplace.client.view;

/**
 * Created by anaya on 2/14/2017.
 */

public class ErrorViewFactory extends MarketplaceViewAbstractFactory {
    @Override
    public MarketplaceView getView(String type) {
        MarketplaceView marketplaceView;
        // When page type is "Invalid_credentials", i.e. the account object was null, then it displays
        // LoginErrorView
         if(type.equals("INVALID_CREDENTIALS")){
            marketplaceView = new LoginErrorView();
            return marketplaceView;
        }
        else if(type.equals("PAGE_NOT_FOUND")){
            marketplaceView = new PageNotFoundView();
            return marketplaceView;
        }
        else{
            return null;
        }
    }
}
