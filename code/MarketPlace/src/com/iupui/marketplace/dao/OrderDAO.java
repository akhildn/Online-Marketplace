package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Address;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Order;
import java.util.*;


public class OrderDAO {

    // maps user and orders
    HashMap<String, List<Order>> userOrderMap;
    private int orderId;

    public OrderDAO(){
            userOrderMap = new HashMap<>();
            orderId = 1000;

    }


    // this method will finish processing the incoming purchase request
    //Step 1: check if user already has any orders placed in past, if yes current order is added to old list
    // else new order list is created to them
    //Step 2: sets order details : date, id, tax, total, address
    //Step 3: adds this order to the list
    public Order placeOrder(Account session, List<Item> orderItems, Address shippingAddress) {
        List<Order> orderList;
        if(userOrderMap.get(session.getUsername()) == null){
            orderList = new LinkedList<>();
        }
        else{
            orderList = userOrderMap.get(session.getUsername());
        }
        Order order = new Order();
        Date date = new Date();
        order.setOrderDate(date);
        order.setOrderId(orderId++);
        order.setOrderItems(orderItems);
        order.setOrderStatus("Placed");
        order.setShippingAddress(shippingAddress);
        double subTotal = calculateOderItems(orderItems);
        order.setOrderSubtotal(subTotal);
        double tax = subTotal * 0.07;
        order.setTax(tax);
        order.setOrderTotal(subTotal+tax);
        orderList.add(order);
        userOrderMap.put(session.getUsername(), orderList );
        return order;
    }

    // calculates order total only for the items which were processed, i.e where in stock
    private double calculateOderItems(List<Item> orderItems) {
        double subTotal=0;

        for(Item orderItem: orderItems){
            if(orderItem.isAvailable()){
                subTotal += orderItem.getTotalItemPrice();
            }
        }
        return subTotal;
    }

    // returns order history of the associated user
    public List<Order> getUserOrderHistory(Account session) {
        List<Order> orderList;
        if(userOrderMap.get(session.getUsername()) == null) {
            return null;
        }
        else{
            orderList = userOrderMap.get(session.getUsername());
            return orderList;
        }
    }
}
