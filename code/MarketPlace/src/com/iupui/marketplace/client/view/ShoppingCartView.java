package com.iupui.marketplace.client.view;

import com.iupui.marketplace.client.MarketplaceFrontController;
import com.iupui.marketplace.client.command.CommandInvoker;
import com.iupui.marketplace.client.command.MarketplaceCommand;
import com.iupui.marketplace.client.command.PurchaseCommand;
import com.iupui.marketplace.client.handlers.PurchaseHandler;
import com.iupui.marketplace.model.beans.Address;
import com.iupui.marketplace.model.beans.Item;
import com.iupui.marketplace.model.beans.ShoppingCart;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anaya on 4/1/2017.
 */
public class ShoppingCartView implements MarketplaceView {

    ShoppingCart shoppingCart;
    private MarketplaceFrontController frontController;
    List<Item> cartItems = new ArrayList<>();

    public ShoppingCartView(MarketplaceFrontController frontController) {
        this.frontController = frontController;
    }

    @Override
    public void show() throws RemoteException {
        System.out.println("******************************\t CART \t*********************************");
        if (isCartEmpty()) {
            System.out.println("Shopping cart id: " + shoppingCart.getCartId());
            System.out.println("\n");
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.println("Item " + (i + 1) + ":");
                System.out.println("id :" + cartItems.get(i).getProduct().getProductId());
                System.out.println("Name: " + cartItems.get(i).getProduct().getProductName());
                System.out.println("Description: " + cartItems.get(i).getProduct().getDescription());
                System.out.println("Price: " + cartItems.get(i).getProduct().getUnitPrice());
                System.out.println("Quantity: " + cartItems.get(i).getQuantity());
                System.out.println("Total Price: " + cartItems.get(i).getTotalItemPrice());
                System.out.println("............................................................................");
            }
            System.out.println("Total Cart Value:" + shoppingCart.getCartTotal());
            nextView(true);
        } else {
            System.out.println("***************************\tShopping cart is empty\t********************");
            nextView(false);
        }
    }

    public void nextView(boolean check) throws RemoteException {
        if (check) {
            System.out.println("\n \n 1. Purchase");
            System.out.println("\n \n 2. Home");
            System.out.println("Enter your choice:");
            int ch;
            Scanner in = new Scanner(System.in).useDelimiter("\\n");
            ch = in.nextInt();
            if (ch == 1 || ch == 2) {
                if (ch == 1) {
                    // shipping address popup
                    Address shippingAddress =  shippingAddressPopUp(in);
                    // calls purchase command
                    PurchaseHandler purchaseHandler = new PurchaseHandler(frontController,shoppingCart,shippingAddress);
                    MarketplaceCommand command = new PurchaseCommand(purchaseHandler);
                    CommandInvoker invoker = new CommandInvoker();
                    invoker.invoke(command);

                } else {
                    frontController.homeRedirect();
                }
            } else {
                System.out.println(".............Notice..................");
                System.out.println("invalid choice");
                System.out.println(".....................................");
                show();
            }
        } else {
            System.out.println("\n \n 1. Home");
            System.out.println("Enter your choice:");
            int ch;
            Scanner in = new Scanner(System.in);
            ch = in.nextInt();
            if (ch == 1) {
                frontController.homeRedirect();
            } else {
                System.out.println(".............Notice..................");
                System.out.println("invalid choice");
                System.out.println(".....................................");
                show();
            }
        }
    }

    private Address shippingAddressPopUp(Scanner in) {
        System.out.println("Enter StreetAddress:");
        String streetAdress = in.next();
        System.out.println("Enter City:");
        String city = in.next();
        System.out.println("Enter State:");
        String state = in.next();
        System.out.println("Enter zip code:");
        String zip = in.next();
        Address shippingAddress = new Address();
        shippingAddress.setStreetAddress(streetAdress);
        shippingAddress.setCity(city);
        shippingAddress.setState(state);
        shippingAddress.setZip(zip);
        return shippingAddress;
    }

    public boolean isCartEmpty() {
        if (shoppingCart != null) {
            cartItems = shoppingCart.getCartItems();
            return true;
        }
        return false;
    }

    public void bindData(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
