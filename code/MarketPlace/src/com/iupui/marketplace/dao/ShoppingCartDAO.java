package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;
import java.util.HashMap;

// This class is used to set and get values for items added to the cart for a user
public class ShoppingCartDAO {

    // maps shopping cart to its associated user
    HashMap<String, ShoppingCart> shoppingCartHashMap;
    int cartId;
    public ShoppingCartDAO(){
            shoppingCartHashMap = new HashMap<String, ShoppingCart>();
            cartId =0;
    }

    // adds item to cart which is requested by the user
    public boolean addToCart(Account account, Product product, int quantity) {
        ShoppingCart shoppingCart;
        if(shoppingCartHashMap.get(account.getUsername()) == null){
           shoppingCart = new ShoppingCart();
           shoppingCart.setUserName(account.getUsername());
           shoppingCart.setCartId(cartId++);
        }
        else{
             shoppingCart = shoppingCartHashMap.get(account.getUsername());
        }
        Item cartItem = new Item();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalItemPrice(product.getUnitPrice()*quantity);
        shoppingCart.getCartItems().add(cartItem);
        double newTotal = shoppingCart.getCartTotal()+ cartItem.getTotalItemPrice() ;
        shoppingCart.setCartTotal(newTotal);
        shoppingCartHashMap.put(account.getUsername(),shoppingCart);
        return true;
    }

    // returns shopping cart which is associated to the user
    public ShoppingCart getCartDetails(Account account){
        ShoppingCart shoppingCart = shoppingCartHashMap.get(account.getUsername());
        return shoppingCart;
    }

    //clears the shopping  cart for a specific user
    public void clearCart(String username) {
        if(shoppingCartHashMap.get(username) != null){
            shoppingCartHashMap.put(username,new ShoppingCart());
        }
    }
}
