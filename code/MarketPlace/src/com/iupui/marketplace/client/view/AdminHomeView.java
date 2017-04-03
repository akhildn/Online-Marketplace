package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.AddItemCommand;
import com.iupui.marketplace.client.command.BrowseCommand;
import com.iupui.marketplace.client.command.CommandInvoker;
import com.iupui.marketplace.client.command.MarketplaceCommand;
import com.iupui.marketplace.client.handlers.AddItemHandler;
import com.iupui.marketplace.client.handlers.BrowseHandler;

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
        System.out.println("3. Logout");
        System.out.println("Enter choice :");
        int choice;
        Scanner in= new Scanner(System.in);
        choice = in.nextInt();
        // Browse Command will be invoked
        if(choice == 1){
            BrowseHandler handler = new BrowseHandler(fc);
            MarketplaceCommand command = new BrowseCommand(handler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        // AddItem command will be invoked
        else if(choice == 2 ){
            AddItemHandler handler = new AddItemHandler(fc);
            MarketplaceCommand command = new AddItemCommand(handler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        // kills process
        else if(choice == 3){
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
