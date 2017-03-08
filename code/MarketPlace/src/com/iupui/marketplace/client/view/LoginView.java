package com.iupui.marketplace.client.view;

import java.rmi.RemoteException;
import java.util.Scanner;

import com.iupui.marketplace.client.*;
import com.iupui.marketplace.client.handlers.LoginHandler;
import com.iupui.marketplace.client.commands.CommandInvoker;
import com.iupui.marketplace.client.commands.LoginCommand;
import com.iupui.marketplace.client.commands.MarketplaceCommand;

// Ryan: Please include usefull comments in each file.
// Fixed: Comments are included in each file.

public class LoginView implements MarketplaceView{

    private MarketplaceFrontController fc;
    public LoginView(MarketplaceFrontController fc)  {
       this.fc=fc;
    }

    public void show() throws RemoteException {
		System.out.println("Welcome IUPUI OOAD Marketplace !!!");
		
		// Command Invoker 
        LoginHandler handler = new LoginHandler(fc);
        Scanner in = new Scanner(System.in);
		
		// reading username from user
        System.out.print("Enter Username:");
        handler.setUname(in.next());
		
		// reading password from user
        System.out.print("Enter password:");
        handler.setPass(in.next());
		
		// Invokes LoginCommand
        MarketplaceCommand command = new LoginCommand(handler);
        CommandInvoker invoker = new CommandInvoker();
        invoker.invoke(command);
    }
}