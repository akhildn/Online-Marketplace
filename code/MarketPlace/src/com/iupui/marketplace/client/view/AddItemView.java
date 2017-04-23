package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 4/2/2017.
 */

// Add item view : admin specific view
public class AddItemView implements  MarketplaceView{

    private MarketplaceFrontController frontController;
    public AddItemView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void show() throws RemoteException {
        System.out.println("*****************************\tAdd Item View\t***********************************");
        System.out.println("do you want to add item: type yes to add OR no to go back to home");
        System.out.println("Enter your choice:");
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        String choice = in.next();
        // if yes takes in the new product details
        if(choice.equalsIgnoreCase("yes")){
            Product product = new Product();

            System.out.println("Enter Product Name:");
            String name = in.next();
            product.setProductName(name);

            System.out.println("Enter Product Description:");
            String description = in.next();
            product.setDescription(description);

            System.out.println("Enter Product Unit Price:");
            int price = in.nextInt();
            product.setUnitPrice(price);

            System.out.println("Enter Product Unit Count:");
            int quantity = in.nextInt();
            product.setUnitCount(quantity);

            if(quantity > 0){
                product.setAvailable(true);
            }

            // passes these details of product which is to be added
            boolean isSuccess = frontController.handleAddItem(product);
            // if successfully added displays below message
            if(isSuccess){
                System.out.println(".........................Alert.........................");
                System.out.println("*********Item is Successfully added to inventory***********");
            }
            // goes back to home
            showHome();
        }
        else if(choice.equalsIgnoreCase("no")){
            showHome();
        }else{
            System.out.println(".........................Notice.........................");
            System.out.println("invalid choice");
            System.out.println("........................................");
            show();
        }
    }

    public void showHome() throws RemoteException {
        frontController.homeRedirect();
    }
}
