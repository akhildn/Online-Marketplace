package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.BrowseCommand;
import com.iupui.marketplace.client.command.CommandInvoker;
import com.iupui.marketplace.client.command.MarketplaceCommand;
import com.iupui.marketplace.client.handlers.BrowseHandler;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 2/10/2017.
 */
public class CustomerHomeView implements MarketplaceView {

// Customer view
    private MarketplaceFrontController frontController;
    public CustomerHomeView(MarketplaceFrontController fc)  {
        this.frontController =fc;
    }

    public void show() throws RemoteException {
        BrowseHandler handler = new BrowseHandler(frontController);
        System.out.println("Welcome to Customer view");
        System.out.println("1. Browse Items");
        System.out.println("2. View Cart");
        System.out.println("3. Purchase History");
        System.out.println("4. Logout");
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
        // call passes to front controller
        else if(choice==2){
            frontController.handleViewCart();
        }
        //call passes to front controller
        else if(choice==3){
           frontController.handlePurchaseHistory();
        }
        //process will be killed
        else if(choice==4){
            System.exit(0);
        }
        else {
            System.out.println("............................Notice!.......................");
            System.out.println("Invalid choice");
            System.out.println("..........................................................");
            show();
        }
    }

}
