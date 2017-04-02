package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by anaya on 4/2/2017.
 */
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
        if(choice.equalsIgnoreCase("yes")){
            Product product = new Product();
            System.out.println("Enter Product id:");
            int id = in.nextInt();
            product.setProductId(id);

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

            boolean isSuccess = frontController.addItem(product);
            if(isSuccess){
                System.out.println(".........................Alert.........................");
                System.out.println("*********Item is Successfully added to inventory*************");
            }
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
