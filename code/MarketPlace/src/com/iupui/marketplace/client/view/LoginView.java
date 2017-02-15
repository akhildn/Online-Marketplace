package com.iupui.marketplace.client.view;

import java.rmi.RemoteException;
import java.util.Scanner;

import com.iupui.marketplace.client.*;

public class LoginView{
 //TODO:


    private MarketplaceFrontController fc;
    public LoginView(MarketplaceFrontController fc)  {
       this.fc=fc;
    }

    public void show() throws RemoteException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Username:");
        String uname= in.next();
        System.out.print("Enter password:");
        String pass=in.next();
        fc.authenticate(uname,pass);
    }
}