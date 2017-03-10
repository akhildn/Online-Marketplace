package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.BrowseCommand;
import com.iupui.marketplace.client.command.CommandInvoker;
import com.iupui.marketplace.client.command.LoginCommand;
import com.iupui.marketplace.client.command.MarketplaceCommand;
import com.iupui.marketplace.client.handlers.BrowseHandler;
import com.iupui.marketplace.client.handlers.LoginHandler;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 2/10/2017.
 */
public class CustomerHomeView implements MarketplaceView {

// Customer view
    private MarketplaceFrontController fc;
    public CustomerHomeView(MarketplaceFrontController fc)  {
        this.fc=fc;
    }

    public void show() throws RemoteException {
        BrowseHandler handler = new BrowseHandler(fc);
        System.out.println("Welcome to Customer view");
        System.out.println("1. Browse Items");
        System.out.println("Enter choice :");
        int choice;
        Scanner in= new Scanner(System.in);
        choice = in.nextInt();
        // Browse Command wil be invoked
        if(choice==1){
            MarketplaceCommand command = new BrowseCommand(handler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
    }

}
