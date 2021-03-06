package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.CommandInvoker;
import com.iupui.marketplace.client.command.MarketplaceCommand;
import com.iupui.marketplace.client.command.ProductDetailCommand;
import com.iupui.marketplace.client.handlers.ProductDetailHandler;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anaya on 3/7/2017.
 */


// Browse View
public class BrowseView implements  MarketplaceView{

    private List<Product> productList;
    private MarketplaceFrontController frontController;
    public BrowseView(MarketplaceFrontController frontController){
        this.frontController = frontController;
    }
    @Override
    public void show() throws RemoteException {


        int itemCount = productList.size();
        System.out.println("********************\tBrowse View\t********************");
        System.out.println("Items:");
        for(int i=0;i<itemCount;i++){
            System.out.println((i+1) + "." + productList.get(i).getProductName());
        }
        System.out.println("Enter serial number of product for more details  or type home to go to home screen : ");
        Scanner in = new Scanner(System.in);
        String choice = in.next();

        if(choice.equalsIgnoreCase("home")){
            frontController.homeRedirect();
        }
        else{
            // if entered choice is integer
            if(choice.matches("[0-9)]+")) {
                int ch = Integer.parseInt(choice);
                if (ch > itemCount) {
                    System.out.println(".............Notice..................");
                    System.out.println("Invalid Serial Number");
                    System.out.println("........................................");
                    show();
                } else {
                    // when an appropriate product is selected
                    Product product = productList.get(ch - 1);
                    // invokes ProductDetail command is invoked
                    ProductDetailHandler handler = new ProductDetailHandler(frontController, product.getProductId());
                    MarketplaceCommand command = new ProductDetailCommand(handler);
                    CommandInvoker invoker = new CommandInvoker();
                    invoker.invoke(command);
                }
            }
            // if entered input is not an integer
            else{
                System.out.println(".............Notice..................");
                System.out.println("Enter an integer for product serial number");
                System.out.println("........................................");
                show();
            }

        }
    }

    // takes in the product list : all products which are in inventory
    public void bindData(List<Product> productList){
        this.productList = productList;
    }
}
