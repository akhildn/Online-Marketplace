package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Product;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anaya on 4/22/2017.
 */
public class UpdateProductView implements MarketplaceView {

    private MarketplaceFrontController frontController;
    private List<Product> productList;
    public UpdateProductView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    public void show() throws RemoteException {
        System.out.println("***********************\t Items in Inventory******************************");
        // prints out all product ids and products names for admin's reference
        for(Product product: productList){
           System.out.println(product.getProductId() + " : " + product.getProductName() );
        }

        System.out.println("Enter ID of product you want to update or enter 0 to go back:");
        // reads the input
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        int productId = in.nextInt();
        // if input is not 0
        if(productId != 0){
        boolean flag = false;
        // checks if the entered input matches any product id
        for(Product product : productList){
            if(product.getProductId() == productId){
                // if input matched flag changed to true
                flag =true;
            }
        }
        Product product  = new Product();
        // on true
        if(flag) {
            // populates new product details for selected product
            product.setProductId(productId);

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

            boolean isUpdated = frontController.handleUpdateProduct(product);
            // on true prints the notice
            if(isUpdated){
                System.out.println("***************************\t Product is Updated ******************************");
                frontController.homeRedirect();
            }
        }else{
            // if input did not match any product id then asks again for product id
            System.out.println("Entered ID is invalid");
            show();
        }
        }else if (productId == 0){
            // go to home on 0
            frontController.homeRedirect();
        } else{
            // on any other input
            System.out.println(" invalid input");
            show();
        }
    }

    public void bindData(List<Product> object) {
        this.productList = object;
    }
}
