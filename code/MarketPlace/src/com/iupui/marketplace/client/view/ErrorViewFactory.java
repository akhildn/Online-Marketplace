package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;

/**
 * Created by anaya on 2/14/2017.
 */

public class ErrorViewFactory extends MarketplaceViewAbstractFactory {
    private MarketplaceFrontController frontController;
    public ErrorViewFactory(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

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
            marketplaceView = new PageNotFoundView(frontController);
            return marketplaceView;
        }
        else{
            return null;
        }
    }
}
