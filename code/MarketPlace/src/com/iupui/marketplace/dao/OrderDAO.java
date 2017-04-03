package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Address;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDAO {

    HashMap<String, List<Order>> userOrderMap;
    private int orderId;

    public OrderDAO(){
            userOrderMap = new HashMap<>();
            orderId = 1000;

    }

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

    private double calculateOderItems(List<Item> orderItems) {
        double subTotal=0;

        for(Item orderItem: orderItems){
            if(orderItem.isAvailable()){
                subTotal += orderItem.getTotalItemPrice();
            }
        }
        return subTotal;
    }

}
