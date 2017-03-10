package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 3/7/2017.
 */


// Browse View
public class BrowseView implements  MarketplaceView{

    private MarketplaceFrontController frontController;
    public BrowseView(MarketplaceFrontController frontController){
        this.frontController = frontController;
    }
    @Override
    public void show() throws RemoteException {
        System.out.println("Items:");
        System.out.println("Pid_1. Item_one");
        System.out.println();
        System.out.println("1.Edit item");
        System.out.println("2.Add to cart");
        System.out.println("enter a choice");
        int choice;
        Scanner in = new Scanner(System.in);
        choice = in.nextInt();
        // Only admin has access to this method
        if(choice==1){
            frontController.editItems();
        }
        // Only customer has access to this method
        else if (choice==2){
            frontController.addCart();
        }
        else{

        }
    }
}
