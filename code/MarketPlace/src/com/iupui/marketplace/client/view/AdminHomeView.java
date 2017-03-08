package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.commands.BrowseCommand;
import com.iupui.marketplace.client.commands.CommandInvoker;
import com.iupui.marketplace.client.commands.MarketplaceCommand;
import com.iupui.marketplace.client.handlers.BrowseHandler;

import java.util.Scanner;

/**
 * Created by anaya on 2/10/2017.
 */
public class AdminHomeView implements MarketplaceView {

    public void show(){
        System.out.println("Welcome to Admin view");
    }

}
