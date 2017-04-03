package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Order;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anaya on 4/3/2017.
 */

// Purchase history view : displays all orders which were played by the user
public class PurchaseHistoryView implements MarketplaceView {

    private MarketplaceFrontController frontController;
    private List<Order> orderList;
    public PurchaseHistoryView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void show() throws RemoteException {
        boolean flag = false;
        System.out.println("*******************Orders Placed So Far*****************************");
        // Displays all orders which were placed
        for (Order order : orderList) {
            System.out.println("\tOrder id:" + order.getOrderId());
            System.out.println("Order Placed for:");
            // displays all items which were successfully placed in that order
            for (Item item : order.getOrderItems()) {
                if (item.isAvailable()) {
                    flag = true;
                    printOrderItemDetails(item);
                }
            }
            if (flag) {
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                System.out.println("Order Date: " + sdf.format(order.getOrderDate()));
                System.out.println("Order Shipped to: " + order.getShippingAddress().toString());
                System.out.println("Order Sub-Total:" + order.getOrderSubtotal());
                System.out.println("Order Tax:" + order.getTax());
                System.out.println("Order Total:" + order.getOrderTotal());
                System.out.println(".................................................................");
            }
            System.out.println();
        }
        nextView();
    }

    // next navigation
    private void nextView() throws RemoteException {
        System.out.println("Menu:  ");
        System.out.println("1.Home");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        if(i==1){
            frontController.homeRedirect();
        }else{
            nextView();
        }
    }

    // prints item details in that order
    private void printOrderItemDetails(Item item) {
        System.out.println("Product id :" + item.getProduct().getProductId());
        System.out.println("Name: " + item.getProduct().getProductName());
        System.out.println("Price: " + item.getProduct().getUnitPrice());
        System.out.println("Quantity: " + item.getQuantity());
        System.out.println("Total Price: " + item.getTotalItemPrice());
    }

    // gets order list from server
    public void bindData(List<Order> object) {
        this.orderList = object;
    }
}
