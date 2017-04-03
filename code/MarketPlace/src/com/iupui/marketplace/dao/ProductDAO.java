package com.iupui.marketplace.dao;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.util.*;

// This class will responsible creating new products or adding new products, updating attributes, returns products
// details upon request
public class ProductDAO {

    /*list to store product details */
    List<Product> productList = new ArrayList<>();

    // mocked up products
    Product productOne = new Product();
    Product productTwo = new Product();
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

    // returns all the products which are available in inventory
    public List<Product> returnItemList(){
        return productList;
    }

    // returns details of specific product by product id
    public Product getProductDetails(int id) {
        for(Product product : productList){
            if(product.getProductId() == id ){
                return product;
            }
        }
        return null;
    }

    // adds new product to inventory, accessible only to admin
    public boolean addNewItem(Product product) {
        productList.add(product);
        return true;
    }

    // this method will process each item in the incoming purchase request
    public List<Item> processOrderItems(ShoppingCart shoppingCart) {
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
        for(Product product : productList){
            if(product.getProductId() == productId ){
                product.setUnitCount(product.getUnitCount()-orderQuanity);
                return true;
            }
        }
        return false;
    }

    // checks if request quantity is still available
    public boolean isProductAvailable(int productId, int quantity){
        for(Product product : productList){
            if(product.getProductId() == productId ){
                return product.getUnitCount()>=quantity ? true : false;
            }
        }
        return false;
    }

}
