package com.iupui.marketplace.dao;

import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.io.Serializable;
import java.util.HashMap;

public class ShoppingCartDAO {

    HashMap<String, ShoppingCart> shoppingCartHashMap;
    int cartId;
    public ShoppingCartDAO(){
            shoppingCartHashMap = new HashMap<String, ShoppingCart>();
            cartId =0;
    }

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

    public ShoppingCart handleGetCartDetials(Account account){
        ShoppingCart shoppingCart = shoppingCartHashMap.get(account.getUsername());
        return shoppingCart;
    }
}
