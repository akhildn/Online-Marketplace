package com.iupui.marketplace.client.view;

/**
 * Created by anaya on 2/14/2017.
 */
public class ErrorViewFactory extends AbstractFactoryView {
    @Override
    public MarketplaceView createView(String type) {
        MarketplaceView marketplaceView;
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
