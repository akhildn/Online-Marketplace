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
        for(Product product: productList){
           System.out.println(product.getProductId() + " : " + product.getProductName() );
        }

        System.out.println("Enter ID of product you want to update or enter 0 to go back:");
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        int productId = in.nextInt();
        if(productId != 0){
        boolean flag = false;
        for(Product product : productList){
            if(product.getProductId() == productId){
                flag =true;
            }
        }
        Product product  = new Product();
        if(flag) {

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
            if(isUpdated){
                System.out.println("***************************\t Product is Updated ******************************");
                frontController.homeRedirect();
            }
        }else{
            System.out.println("Entered ID is invalid");
            show();
        }
        }else if (productId == 0){
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
