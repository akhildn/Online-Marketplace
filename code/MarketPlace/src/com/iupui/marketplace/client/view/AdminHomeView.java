package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.*;
import com.iupui.marketplace.client.handlers.AddItemHandler;
import com.iupui.marketplace.client.handlers.BrowseHandler;
import com.iupui.marketplace.client.handlers.RemoveProductHandler;
import com.iupui.marketplace.client.handlers.UpdateProductHandler;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 2/10/2017.
 */

// Admin view
public class AdminHomeView implements MarketplaceView {

    private MarketplaceFrontController fc;
    public AdminHomeView(MarketplaceFrontController fc)  {
        this.fc=fc;
    }

    public void show() throws RemoteException {
        System.out.println("Welcome to Admin view");
        System.out.println("1. Browse Items");
        System.out.println("2. Add Items");
        System.out.println("3. Update Product");
        System.out.println("4. Remove Product");
        System.out.println("5. Logout");
        System.out.println("Enter choice :");
        int choice;
        Scanner in= new Scanner(System.in);
        choice = in.nextInt();
        // Browse Command will be invoked
        if(choice == 1){
            BrowseHandler browseHandler = new BrowseHandler(fc);
            MarketplaceCommand command = new BrowseCommand(browseHandler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        // AddItem command will be invoked
        else if(choice == 2 ){
            AddItemHandler addItemHandler = new AddItemHandler(fc);
            MarketplaceCommand command = new AddItemCommand(addItemHandler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        // update product command
        else if(choice == 3){
            UpdateProductHandler updateProductHandler = new UpdateProductHandler(fc);
            MarketplaceCommand command = new UpdateProductCommand(updateProductHandler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        // remove product command
        else if(choice ==4 ){
            RemoveProductHandler removeProductHandler = new RemoveProductHandler(fc);
            MarketplaceCommand command = new RemoveProductCommand(removeProductHandler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        // kills process
        else if(choice == 5){
            System.exit(0);
        }
        else{
            System.out.println(".........................Notice.........................");
            System.out.println("invalid choice");
            System.out.println("........................................");
            show();
        }
    }
}
