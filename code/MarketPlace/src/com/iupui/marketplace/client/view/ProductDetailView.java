package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.AddCartCommand;
import com.iupui.marketplace.client.command.BrowseCommand;
import com.iupui.marketplace.client.command.CommandInvoker;
import com.iupui.marketplace.client.command.MarketplaceCommand;
import com.iupui.marketplace.client.handlers.AddCartHandler;
import com.iupui.marketplace.client.handlers.BrowseHandler;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 4/1/2017.
 */


// Product detal view : view which displays all the information of selected product
public class ProductDetailView implements MarketplaceView{

    private Product product;
    private MarketplaceFrontController frontController;

    public ProductDetailView(MarketplaceFrontController frontController) {
        this.frontController = frontController;

    }
    @Override
    public void show() throws RemoteException {

        int productId = product.getProductId();
        String productName = product.getProductName();
        String productDescription = product.getDescription();
        double unitPrice = product.getUnitPrice();
        int unitCount = product.getUnitCount();

        System.out.println("********************\tProduct Detail View\t********************");
        System.out.println("Product_Id:" + productId);
        System.out.println("Product_Name:" + productName);
        System.out.println("Product_Description:" + productDescription);
        System.out.println("Product_Price:" + unitPrice);
        System.out.println("Number of units left:" + unitCount);

        System.out.println("\n\n");
        System.out.println("1. Add to cart");
        System.out.println("2. Back to Browse");
        System.out.println("Enter your choice");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        // if user wants to add this product to cart
        if(choice == 1){
            System.out.println("Enter Quantity:");
            int quantity = in.nextInt();
            // checks if quantity user wants is valid i.e. less than what is in stock
            if(quantity <= unitCount){
                AddCartHandler addCartHandler = new AddCartHandler(frontController,product,quantity);
                MarketplaceCommand command = new AddCartCommand(addCartHandler);
                CommandInvoker invoker = new CommandInvoker();
                invoker.invoke(command);
            }else{
                System.out.println(".............Notice..................");
                System.out.println("Sorry only " + unitCount + " are available");
                System.out.println("........................................");
                show();
            }
        }
        // goes back to browse view
        else if(choice == 2){
            BrowseHandler handler = new BrowseHandler(frontController);
            MarketplaceCommand command = new BrowseCommand(handler);
            CommandInvoker invoker = new CommandInvoker();
            invoker.invoke(command);
        }
        else{
            System.out.println(".............Notice..................");
            System.out.println("Sorry invalid choice");
            show();
        }
    }

    public void bindData(Product object) {
        this.product = object;
    }
}
