package com.iupui.marketplace.dao;

import com.iupui.marketplace.database.MarketplaceDBConnection;
import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// This class is used to set and get values for items added to the cart for a user
public class ShoppingCartDAO {

    private Connection dbConnection;
    public ShoppingCartDAO(){
        dbConnection = MarketplaceDBConnection.getMarketplaceDbConnection().getConnection();
    }

    // adds item to cart which is requested by the user
    public boolean addToCart(Account account, Product product, int quantity) throws SQLException {
        int cartId = 0;
        double cartTotal = 0;
        // query to retrieve cart_id associated to username in DB
        String query = "select * from anayabu_db.shopping_cart where username='"+account.getUsername()+"'";
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        // if result set is empty
        if(!resultSet.next()){
            query = " insert into anayabu_db.shopping_cart (username,cart_total) values('"+account.getUsername()
                    +"',0.000)";
            statement = (Statement) dbConnection.createStatement();
            statement.executeUpdate(query);
            ResultSet rs = statement.executeQuery("select * from anayabu_db.shopping_cart where username='"+
            account.getUsername()+"'");
            if(rs.next()) {
                cartId = rs.getInt("cart_id");
                cartTotal = rs.getDouble("cart_total");
            }
        }
        else{
            cartId = resultSet.getInt("cart_id");
            cartTotal = resultSet.getDouble("cart_total");
            query = " select quantity from anayabu_db.cart_items where cart_id="+cartId+" and product_id="+
                    product.getProductId();
            ResultSet rs =statement.executeQuery(query);
            if(rs.next()){
                statement.executeUpdate("update cart_items set quantity=quantity+"+quantity+"" +
                        " , total_item_price=" +(product.getUnitPrice()*quantity)+
                        " where cart_id="+cartId+" and product_id="+
                        product.getProductId());

                double newTotal = cartTotal +  (product.getUnitPrice()*quantity) ;
                statement.executeUpdate("update anayabu_db.shopping_cart set cart_total="+newTotal+"where cart_id="
                        + cartId);
                return true;
            }

        }
        String insertCartQuery = " insert into anayabu_db.cart_items values("+cartId+","+product.getProductId()+
                ","+quantity+","+(product.getUnitPrice()*quantity)+")";
        statement.executeUpdate(insertCartQuery);
        double newTotal = cartTotal +  (product.getUnitPrice()*quantity) ;
        statement.executeUpdate("update anayabu_db.shopping_cart set cart_total="+newTotal+"where cart_id="
                                + cartId);
        return true;
    }

    // returns shopping cart which is associated to the user
    public ShoppingCart getCartDetails(Account account) throws SQLException {
        List<Item> itemList = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        String query = "select s.cart_id as cart_id, s.username as username, s.cart_total as cart_total,"+
                " c.product_id as product_id, c.quantity as quantity, c.total_item_price as total_item_price,"+
                "p.product_name as product_name, p.description as description, p.unit_price as unit_price, " +
                "p.unit_count as unit_count " +
                "from anayabu_db.shopping_cart s " +
                "join anayabu_db.cart_items c on c.cart_id=s.cart_id "+
                "join anayabu_db.product p on p.product_id=c.product_id " +
                "where s.username='"+account.getUsername()+"'";
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            Item item = new Item();
            Product product = new Product();
            shoppingCart.setCartId(resultSet.getInt("cart_id"));
            shoppingCart.setUserName(resultSet.getString("username"));
            shoppingCart.setCartTotal(resultSet.getDouble("cart_total"));
            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setDescription(resultSet.getString("description"));
            product.setUnitPrice(resultSet.getDouble("unit_price"));
            product.setUnitCount(resultSet.getInt("unit_count"));
            item.setProduct(product);
            item.setQuantity(resultSet.getInt("quantity"));
            // update price from product
            item.setTotalItemPrice(resultSet.getDouble("total_item_price"));
            itemList.add(item);
        }
        shoppingCart.setCartItems(itemList);
        return shoppingCart;
    }

    //clears the shopping  cart for a specific user
    public void clearCart(int cartId) throws SQLException {
        String query = "select * from anayabu_db.shopping_cart where cart_id='"+cartId+"'";
        Statement statement = (Statement) dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            statement.executeUpdate("delete from cart_items where cart_id='"+cartId+"'");
            statement.executeUpdate("update shopping_cart set cart_total=0.000 where cart_id="+cartId);
        }
    }
}
