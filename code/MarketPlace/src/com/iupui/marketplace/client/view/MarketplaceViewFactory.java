package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;

/**
 * Created by anaya on 3/7/2017.
 */

// this factory will create various types of views that a customer can access

public class MarketplaceViewFactory extends MarketplaceViewAbstractFactory {

    private MarketplaceFrontController frontController;
    public MarketplaceViewFactory(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public MarketplaceView getView(String type) {
        MarketplaceView marketplaceView;
        // checks condition and calls specific view on satisfied condition
        if(type.equals("BROWSE")){
            marketplaceView = new BrowseView(frontController);
            return marketplaceView;
        }
        else if(type.equals("PRODUCT_DETAILS")){
            marketplaceView = new ProductDetailView(frontController);
            return marketplaceView;
        }
        else if(type.equals("CART_VIEW")){
            marketplaceView = new ShoppingCartView(frontController);
            return marketplaceView;
        }
        else if(type.equals("ADD_ITEM")){
            marketplaceView = new AddItemView(frontController);
            return marketplaceView;
        }
        else if(type.equals("ORDER_CONFIRMATION")){
            marketplaceView = new OrderConfirmationView(frontController);
            return marketplaceView;
        }else if(type.equals("ORDER_HISTORY")){
            marketplaceView = new PurchaseHistoryView(frontController);
            return marketplaceView;
        }else if(type.equals("UPDATE_PRODUCT")){
            marketplaceView = new UpdateProductView(frontController);
            return marketplaceView;
        }else if(type.equals("REMOVE_PRODUCT")){
            marketplaceView = new RemoveProductView(frontController);
            return marketplaceView;
        }
        else{
            return null;
        }
    }

}
