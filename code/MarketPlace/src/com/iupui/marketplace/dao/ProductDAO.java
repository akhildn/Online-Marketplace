package com.iupui.marketplace.dao;
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
    public boolean addNewItem(Product product) {
       // productList.add(product);
        return true;
    }

    // this method will process each item in the incoming purchase request
    public List<Item> processOrderItems(ShoppingCart shoppingCart) throws SQLException {
        List<Item> orderItems = new ArrayList<>();
        List<Item> cartItems = shoppingCart.getCartItems();

        // for every item in the cart checks if item is still available
        for(Item item : cartItems){
            // checks if item is available
            boolean isAvailable = isProductAvailable(item.getProduct().getProductId(), item.getQuantity());
            Item orderItem = new Item();
            // gets latest details for product
            Product latestProductObject = getProductDetails(item.getProduct().getProductId());
            // checks if product is still available in inventory
            if(latestProductObject == null){
                // if product is not available in inventory sets appropriate status message
                orderItem.setProduct(item.getProduct());
                orderItem.setAvailable(false);
                orderItem.setStatusMessage("Item no longer available");
            }else{
                orderItem.setProduct(latestProductObject);
                orderItem.setAvailable(isAvailable);
                if(isAvailable){
                    //if requested quantity is available, quantity is deducted in inventory and reserve the product
                    // for user
                    updateProductQuantity(latestProductObject.getProductId(),item.getQuantity());
                    orderItem.setTotalItemPrice(latestProductObject.getUnitPrice()*item.getQuantity());
                    orderItem.setQuantity(item.getQuantity());
                }else{
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
        }
        // returns final processed order items
        return orderItems;
    }

    // updates product quantity
    private boolean updateProductQuantity(int productId, int orderQuanity) {
       /* for(Product product : productList){
            if(product.getProductId() == productId ){
                product.setUnitCount(product.getUnitCount()-orderQuanity);
                return true;
            }
        }*/
        return false;
    }

    // checks if request quantity is still available
    public boolean isProductAvailable(int productId, int quantity){
       /* for(Product product : productList){
            if(product.getProductId() == productId ){
                return product.getUnitCount()>=quantity ? true : false;
            }
        }*/
        return false;
    }

}
