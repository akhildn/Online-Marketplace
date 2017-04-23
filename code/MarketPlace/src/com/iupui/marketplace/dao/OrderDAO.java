package com.iupui.marketplace.dao;

import com.iupui.marketplace.database.MarketplaceDBConnection;
import com.iupui.marketplace.model.beans.*;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class OrderDAO {

    private Connection dbConnection;
    public OrderDAO(){
        dbConnection = MarketplaceDBConnection.getMarketplaceDbConnection().getConnection();
    }

    // this method will finish processing the incoming purchase request
    //Step 1: check if user already has any orders placed in past, if yes current order is added to old list
    // else new order list is created to them
    //Step 2: sets order details : date, id, tax, total, address
    //Step 3: adds this order to the list
    public Order placeOrder(Account session, List<Item> orderItems, String shippingAddress) throws SQLException {
        Statement statement = (Statement) dbConnection.createStatement();
        Order order = new Order();
        String date = getDate();
        order.setOrderDate(date);
        order.setOrderId(session.getUsername()+ System.currentTimeMillis());
        order.setOrderItems(orderItems);
        order.setOrderStatus("Placed");
        order.setShippingAddress(shippingAddress);
        double subTotal = calculateOderItems(orderItems);
        order.setOrderSubtotal(subTotal);
        double tax = subTotal * 0.07;
        order.setTax(tax);
        order.setOrderTotal(subTotal+tax);
        String insertQuery = "insert into anayabu_db.order_details (order_id,order_date,order_status,order_sub_total," +
                "order_tax," +
                "order_total,"+ "shipping_address,username) values('"+order.getOrderId() +"','" +
                order.getOrderDate()+"','"+order.getOrderStatus()+"',"+
                order.getOrderSubtotal()+","+order.getTax()+","+order.getOrderTotal()+",'"+
                order.getShippingAddress().toString()+"','"+session.getUsername()+"')";
        statement.executeUpdate(insertQuery);
        for(Item item:orderItems) {
            insertQuery = "insert into order_items (order_id,oi_product_id,oi_product_name,oi_description,oi_unit_price," +
                    "oi_unit_count,oi_availability,oi_status_message,oi_total_item_price,oi_quantity) values('" +
                    order.getOrderId() + "'," + item.getProduct().getProductId()+",'"+ item.getProduct().getProductName()+
                    "','"+item.getProduct().getDescription()+"',"+item.getProduct().getUnitPrice()+","+
                    item.getProduct().getUnitCount()+","+ item.isAvailable()+",'"+ item.getStatusMessage()+"',"+
                    item.getTotalItemPrice()+","+item.getQuantity()+")";
            statement.executeUpdate(insertQuery);
        }
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
   public List<Order> getUserOrderHistory(Account session) throws SQLException {
        Item orderItem;
        Order order;
        List<Order> orderList = new ArrayList<>();
        List<Item> orderItems = new ArrayList<>();
        String query = " select * from order_details where username='"+session.getUsername()+"' order by order_date " +
                "desc ";
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            order = new Order();
            String date = getDate();
            order.setOrderDate(date);
            order.setOrderId(session.getUsername()+ System.currentTimeMillis());
            String queryItems = "select * from order_items where order_id='"+resultSet.getString("order_id")+
                    "'";
            Statement statement1 = (Statement) dbConnection.createStatement();
            ResultSet rs=statement1.executeQuery(queryItems);
            while(rs.next()){
                orderItem = new Item();
                Product product= new Product();
                product.setProductId(rs.getInt("oi_product_id"));
                product.setProductName(rs.getString("oi_product_name"));
                product.setDescription(rs.getString("oi_description"));
                product.setUnitPrice(rs.getDouble("oi_unit_price"));
                product.setUnitCount(rs.getInt("oi_unit_count"));
                product.setAvailable(rs.getBoolean("oi_availability"));
                orderItem.setProduct(product);
                orderItem.setAvailable(rs.getBoolean("oi_availability"));
                orderItem.setStatusMessage(rs.getString("oi_status_message"));
                orderItem.setTotalItemPrice(rs.getDouble("oi_total_item_price"));
                orderItem.setQuantity(rs.getInt("oi_quantity"));
                orderItems.add(orderItem);
            }
            order.setOrderItems(orderItems);
            order.setOrderStatus(resultSet.getString("order_status"));
            order.setShippingAddress(resultSet.getString("shipping_address"));
            double subTotal = calculateOderItems(orderItems);
            order.setOrderSubtotal(resultSet.getDouble("order_sub_total"));
            double tax = subTotal * 0.07;
            order.setTax(resultSet.getDouble("order_tax"));
            order.setOrderTotal(resultSet.getDouble("order_total"));
            orderList.add(order);
            }
            return orderList;
        }

    public String getDate() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return  currentTime;
    }
}
