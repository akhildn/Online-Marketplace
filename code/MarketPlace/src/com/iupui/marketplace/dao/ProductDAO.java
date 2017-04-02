package com.iupui.marketplace.dao;
import com.iupui.marketplace.model.beans.Product;

import java.util.*;

public class ProductDAO {

    /*list to store product details */
    List<Product> productList = new ArrayList<>();
    Product productOne = new Product();
    Product productTwo = new Product();
    Product returnDetails;
    public ProductDAO(){

        /* details for item 1 */

        productOne.setProductName("Phone");
        productOne.setProductId(9005676);
        productOne.setDescription("This is a phone \n model:2015 \n feature:touch");
        productOne.setUnitPrice(450);
        productOne.setUnitCount(5);
        productOne.setAvailable(true);

        /*details for item 2 */
        productTwo.setProductName("Phone");
        productTwo.setProductId(9005676);
        productTwo.setDescription("This is a phone \n model:2015 \n feature:touch");
        productTwo.setUnitPrice(450);
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
}
