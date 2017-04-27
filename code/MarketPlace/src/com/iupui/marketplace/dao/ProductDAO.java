package com.iupui.marketplace.dao;
import com.iupui.marketplace.controller.MarketplaceControllerImpl;
import com.iupui.marketplace.database.MarketplaceDBConnection;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;
import com.mysql.jdbc.Statement;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// This class will responsible creating new products or adding new products, updating attributes, returns products
// details upon request
public class ProductDAO {


    //Connection object
    private Connection dbConnection;
    public ProductDAO(){
        // gets db connection
        dbConnection = MarketplaceDBConnection.getMarketplaceDbConnection().getConnection();
    }

    // returns all the products which are available in inventory
    public List<Product> returnItemList() throws SQLException {
         /*list to store product details */
        List<Product> productList = new ArrayList<>();
        // gets all products
        String query = " select * from anayabu_db.product";
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        // iterate through all the rows
        while(resultSet.next()) {
            Product product = new Product();
            // populate product object
            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setDescription(resultSet.getString("description"));
            product.setUnitPrice(resultSet.getDouble("unit_price"));
            product.setUnitCount(resultSet.getInt("unit_count"));
            product.setAvailable(resultSet.getBoolean("availability"));
            // adds the populated product object to product list
            productList.add(product);
        }
        return productList;
    }

    // returns details of specific product by product id
    public Product getProductDetails(int id) throws SQLException {
        Product product = new Product();
        // gets product by id
        String query = " select * from anayabu_db.product where product_id="+id;
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        // iterate through all the rows
        if(resultSet.next()) {
            // populate product object
            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setDescription(resultSet.getString("description"));
            product.setUnitPrice(resultSet.getDouble("unit_price"));
            product.setUnitCount(resultSet.getInt("unit_count"));
            product.setAvailable(resultSet.getBoolean("availability"));
            return product;
        }
        return null;
    }

    // adds new product to inventory, accessible only to admin
    public boolean addNewItem(Product product) throws SQLException {
        String insertProductQuery = " insert into product (product_name,description,unit_price,unit_count,availability"
                + ") values ( '"+product.getProductName()+"','"+product.getDescription()+"',"
                +product.getUnitPrice()+","+product.getUnitCount()+","+product.isAvailable()+")";
        Statement statement = (Statement) dbConnection.createStatement();
        int status = statement.executeUpdate(insertProductQuery);
        return status==1 ? true : false;
    }

    // this method will process each item in the incoming purchase request
    public List<Item> processOrderItems(ShoppingCart shoppingCart) throws SQLException {
        List<Item> orderItems = new ArrayList<>();
        List<Item> cartItems = shoppingCart.getCartItems();
        // for every item in the cart checks if item is still available

        for(Item item : cartItems) {
            System.out.println(Thread.currentThread().getName() +" -: purchase item entry ");

            // checks if there is already an object attached to the mentioned key: productId, if no object is associated
            // then a object is created and associated to it. If yes, then it will return the existing object
            // Ref: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentMap.html#putIfAbsent(K,%20V)
            MarketplaceControllerImpl.productLockMap.putIfAbsent(item.getProduct().getProductId(), new Object());

            // synchronized on productId, i.e when customer thread is trying to processing a order of a specific product
            // and admin thread is trying update the same product, we may get issues when this happen concurrently, to avoid
            // update and purchase happen same time we used product id as the monitor object criteria to achieve
            // synchronization. To achieve this synchronization between methods of 2 different classes i.e. purchase
            // and update product method, we need make use of the same monitor object to achieve synchronization.

            // this also ensures no 2 customer threads can process an same product at the same time.Q
            synchronized(MarketplaceControllerImpl.productLockMap.get(item.getProduct().getProductId())) {

                System.out.println(Thread.currentThread().getName() +" -: purchase item critical entry ");
                // to be removed, added for testing
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // checks if item is available
                boolean isAvailable = isProductAvailable(item.getProduct().getProductId(), item.getQuantity());
                Item orderItem = new Item();
                // gets latest details for product
                Product latestProductObject = getProductDetails(item.getProduct().getProductId());
                // checks if product is still available in inventory

                if (latestProductObject == null) {
                    // if product is not available in inventory sets appropriate status message
                    orderItem.setProduct(item.getProduct());
                    orderItem.setAvailable(false);
                    orderItem.setStatusMessage("Item no longer available");
                } else {
                    orderItem.setProduct(latestProductObject);
                    orderItem.setAvailable(isAvailable);
                    if (isAvailable) {
                        //if requested quantity is available, quantity is deducted in inventory and reserve the product
                        // for user
                        updateProductQuantity(latestProductObject.getProductId(), item.getQuantity());
                        orderItem.setTotalItemPrice(latestProductObject.getUnitPrice() * item.getQuantity());
                        orderItem.setQuantity(item.getQuantity());
                    } else {
                        // if requested quantity is not available for user, appropriate status message is set
                        // this item is will not be processed in order, but the user will be notified
                        orderItem.setTotalItemPrice(0);
                        orderItem.setStatusMessage("Requested Number of items not available, Current Stock:"
                                + latestProductObject.getUnitCount());
                        orderItem.setQuantity(item.getQuantity());
                    }
                }
                //item in the order is processed and added to order list of items
                orderItems.add(orderItem);
                System.out.println(Thread.currentThread().getName() +" -: purchase item exit ");
            }

        }
        // returns final processed order items
        return orderItems;
    }

    // updates product quantity
    private boolean updateProductQuantity(int productId, int orderQuantity) throws SQLException {
        Statement statement = (Statement) dbConnection.createStatement();
        int status =  statement.executeUpdate("update anayabu_db.product set unit_count= unit_count - "
                        +orderQuantity+" where product_id="
                        +productId);
        return status == 0 ? false:true;
    }

    // checks if request quantity is still available
    public boolean isProductAvailable(int productId, int quantity) throws SQLException {
        Product product =getProductDetails(productId);
        if(product!=null){
            return product.getUnitCount()>=quantity ? true : false;
            }
        return false;
    }

    public boolean updateProduct(Product product) throws SQLException {
        if(product!=null) {
            // update query
            String updateQuery = " update product set product_name='" + product.getProductName() + "'," +
                    " description='" +
                    product.getDescription() + "', unit_price=" + product.getUnitPrice() + ", unit_count=" +
                    product.getUnitCount() + ", availability=" + product.isAvailable() + " where product_id="
                    + product.getProductId();
            Statement statement = (Statement) dbConnection.createStatement();
            //executes the query
            statement.executeUpdate(updateQuery);
            return  true;
        }
        return false;
    }

    public boolean removeProduct(int productId) throws SQLException {
        // delete query
        String removeQuery = " delete from product where product_id ="+productId;
        Statement statement = (Statement) dbConnection.createStatement();
        //excutes the query
        int isRemoved = statement.executeUpdate(removeQuery);
        // if at least 1 row is effected then returns true
        if(isRemoved != 0){
            return  true;
        }
        return false;
    }
}
