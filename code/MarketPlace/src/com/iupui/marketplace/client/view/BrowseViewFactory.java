package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;

/**
 * Created by anaya on 3/7/2017.
 */
public class BrowseViewFactory extends MarketplaceViewAbstractFactory {


    private MarketplaceFrontController frontController;

    public BrowseViewFactory(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public MarketplaceView getView(String type) {
        MarketplaceView marketplaceView;
        // will call browse view, view might change in future assignments
        if(type.equals("BROWSE_ALL")){
            marketplaceView = new BrowseView(frontController);
            return marketplaceView;
        }
        else{
            return null;
        }
    }

}
