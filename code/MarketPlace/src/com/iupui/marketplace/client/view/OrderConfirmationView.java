package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Order;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Created by anaya on 4/2/2017.
 */

// displays order which was placed
public class OrderConfirmationView implements MarketplaceView {

    private Order order;
    private MarketplaceFrontController frontController;
    public OrderConfirmationView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void show() throws RemoteException {
        boolean flag = false;
        System.out.println("******************************\t Order Confirmation *********************************");
        System.out.println("Order id:" + order.getOrderId());
        System.out.println("Order Placed for:");
        for(Item item : order.getOrderItems()){
            // displays only items which were placed
            if(item.isAvailable()){
                flag = true;
                printOrderItemDetails(item);
                System.out.println("............................................................................");
            }
        }
        if(flag) {
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            System.out.println("Order Date: " + sdf.format(order.getOrderDate()));
            System.out.println("Order Shipped to: " + order.getShippingAddress().toString());
            System.out.println("Order Sub-Total:" + order.getOrderSubtotal());
            System.out.println("Order Tax:" + order.getTax());
            System.out.println("Order Total:" + order.getOrderTotal());
            System.out.println("_______________________________________________________________________________________");
        }
        else{
            System.out.println("\t None of the items were processed ");
        }
        // displays items which were not placed
        System.out.println("Unprocessed items from the cart:");
        for(Item item : order.getOrderItems()){
            if(!item.isAvailable()){
                printOrderItemDetails(item);
                System.out.println("Error reason: " + item.getStatusMessage());
                System.out.println("............................................................................");
            }
        }
        nextView();

    }

    // prints details of items which were in the order
    private void printOrderItemDetails(Item item) {
        System.out.println("id :" + item.getProduct().getProductId());
        System.out.println("Name: " + item.getProduct().getProductName());
        System.out.println("Price: " + item.getProduct().getUnitPrice());
        System.out.println("Quantity: " + item.getQuantity());
        System.out.println("Total Price: " + item.getTotalItemPrice());
    }

    // menu for navigation
    private void nextView() throws RemoteException {
        System.out.println("Menu: ");
        System.out.println("1.Home");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        if(i==1){
            frontController.homeRedirect();
        }else{
            nextView();
        }
    }

    public void bindData(Order object) {
        this.order = object;
    }
}
