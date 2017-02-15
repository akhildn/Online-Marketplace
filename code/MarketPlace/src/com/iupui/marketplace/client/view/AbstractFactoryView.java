package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.view.MarketplaceView;

/**
 * Created by anaya on 2/14/2017.
 */
public abstract class AbstractFactoryView {
    abstract public MarketplaceView createView(String type);
}
