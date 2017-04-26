package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anaya on 4/22/2017.
 */
public class RemoveProductView implements MarketplaceView {

    private MarketplaceFrontController frontController;
    private List<Product> productList;
    public RemoveProductView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    public void show() throws RemoteException {
        System.out.println("***********************\t Items in Inventory******************************");
        // prints out all product ids and products names for admin's reference
        for (Product product : productList) {
            System.out.println(product.getProductId() + " : " + product.getProductName());
        }

        System.out.println("Enter ID of product you want to update or enter 0 to go back:");
        // reads the input
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        int productId = in.nextInt();
        // if input is not 0
        if (productId != 0) {
            boolean flag = false;
            // checks if the entered input matches any product id
            for (Product product : productList) {
                if (product.getProductId() == productId) {
                    // if input matched flag changed to true
                    flag = true;
                }
            }
            // on true
            if (flag) {
                // calls remove method
               boolean isRemoved = frontController.handleRemoveProduct(productId);
               // if true prints notice
               if(isRemoved){
                   System.out.println("*****************************\t product removed*****************************");
               }
            } else {
                // if input did not match any product id then asks again for product id
                System.out.println("Entered ID is invalid");
                show();
            }
        }else if (productId == 0){
            // go to home on 0
            frontController.homeRedirect();
        } else{
            System.out.println(" invalid input");
            show();
        }
    }

    public void bindData(List<Product> object) {
        this.productList = object;
    }
}
