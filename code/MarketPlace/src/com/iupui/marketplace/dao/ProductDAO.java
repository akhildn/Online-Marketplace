package com.iupui.marketplace.dao;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.util.*;

public class ProductDAO {

    /*list to store product details */
    List<Product> productList = new ArrayList<>();
    Product productOne = new Product();
    Product productTwo = new Product();
    Product returnDetails;
    public ProductDAO(){

        /* details for item 1 */

        productOne.setProductName("iPhone");
        productOne.setProductId(9005676);
        productOne.setDescription("This is a phone \n model:2015 \n feature:touch");
        productOne.setUnitPrice(450);
        productOne.setUnitCount(5);
        productOne.setAvailable(true);

        /*details for item 2 */
        productTwo.setProductName("iPad");
        productTwo.setProductId(9005421);
        productTwo.setDescription("This is a tablet \n model:2015 \n feature:touch");
        productTwo.setUnitPrice(550);
        productTwo.setUnitCount(5);
        productTwo.setAvailable(true);

        productList.add(productOne);
        productList.add(productTwo);
    }

    public List<Product> returnItemList(){
        return productList;
    }

    public Product getDetails(int id) {
        for(int i=0; i<productList.size(); i++){
            if(productList.get(i).getProductId()==id){
                return productList.get(i);
            }
        }
        return null;
    }

    public boolean addNewItem(Product product) {
        productList.add(product);
        return true;
    }

    public List<Item> processOrderItems(ShoppingCart shoppingCart) {
        List<Item> orderItems = new ArrayList<>();
        List<Item> cartItems = shoppingCart.getCartItems();
        for(Item item : cartItems){
            boolean isAvailable = isProductAvailable(item.getProduct().getProductId(), item.getQuantity());
            Item orderItem = new Item();
            Product latestProductObject = getProductDetails(item.getProduct().getProductId());
            if(latestProductObject == null){
                orderItem.setProduct(item.getProduct());
                orderItem.setAvailable(false);
                orderItem.setStatusMessage("Item no longer available");
            }else{
                orderItem.setProduct(latestProductObject);
                orderItem.setAvailable(isAvailable);
                if(isAvailable){
                    updateProductQuantity(latestProductObject.getProductId(),item.getQuantity());
                    orderItem.setTotalItemPrice(latestProductObject.getUnitPrice()*item.getQuantity());
                    orderItem.setQuantity(item.getQuantity());
                }else{
                    orderItem.setTotalItemPrice(0);
                    orderItem.setStatusMessage("Requested Number of items not available, Current Stock:"
                            + latestProductObject.getUnitCount());
                    orderItem.setQuantity(item.getQuantity());
                }
            }
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private boolean updateProductQuantity(int productId, int orderQuanity) {
        for(Product product : productList){
            if(product.getProductId() == productId ){
                product.setUnitCount(product.getUnitCount()-orderQuanity);
                return true;
            }
        }
        return false;
    }

    public boolean isProductAvailable(int productId, int quantity){
        for(Product product : productList){
            if(product.getProductId() == productId ){
                return product.getUnitCount()>=quantity ? true : false;
            }
        }
        return false;
    }

    public Product getProductDetails(int productId){
        for(Product product : productList){
            if(product.getProductId() == productId ){
                return product;
            }
        }
        return null;
    }
}
