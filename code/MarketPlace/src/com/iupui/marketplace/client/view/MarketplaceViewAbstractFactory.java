package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.view.MarketplaceView;

/**
 * Created by anaya on 2/14/2017.
 */
 // Ryan: Please include useful comments in each file.
 // Fixed: Comments are provided in each file.

 // Ryan: Should the abstract factory really have a view?
 // Fixed: Renamed class and methods to clear the confusion

public abstract class MarketplaceViewAbstractFactory {
    abstract public MarketplaceView getView(String type);
}
