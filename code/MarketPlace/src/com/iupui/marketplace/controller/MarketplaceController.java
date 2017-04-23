/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.iupui.marketplace.model.beans.*;
import com.iupui.marketplace.server.RequiresRole;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.
public interface MarketplaceController extends java.rmi.Remote {
	public int connect() throws RemoteException;

	// Ryan: I am guessing this is just for this assignment - otherwise the method below could handle 
	// functionality.
	//Fixed : welcomeMessage() removed and welcome string included in LoginView
	//public String welcomeMessage() throws RemoteException;


	//TODO : yet to be implemented based on requirement of the assignment
	public List<ProductCategory> handleListProductCategories() throws RemoteException;
	public List<Product> handleBrowseItemsByCategoryId(int categoryId) throws RemoteException;
	public List<Product> handleBrowseItemsByCategoryName(String categoryName) throws RemoteException;
	public List<Product> handleBrowseItemsByProductName(String productName) throws RemoteException;

	// login handler, checks if user credentials are valid.
	public Account handleLogin(String username, String password) throws RemoteException, SQLException;

	// common for admin and customer
	public List<Product> handleBrowseItems() throws RemoteException, SQLException;
	public Product handlegetProductDetails(int productId) throws RemoteException, SQLException;

	// Admin functions
	@RequiresRole("ADMIN")
	public boolean handleAddItem(Account account, Product product) throws RemoteException, SQLException;

	// Customer functions
	@RequiresRole("CUSTOMER")
	public boolean handleAddToCart(Account account, Product product, int quantity) throws RemoteException, SQLException;
	@RequiresRole("CUSTOMER")
	public ShoppingCart handleGetCartDetails(Account session) throws RemoteException, SQLException;
	@RequiresRole("CUSTOMER")
    public Order handlePlaceOrder(Account session, ShoppingCart shoppingCart,String shippingAddress) throws RemoteException, SQLException;
	@RequiresRole("CUSTOMER")
	public List<Order> handleGetOrderHistory(Account account) throws RemoteException, SQLException;

   public ShoppingCart handleClearCart(Account account, int cartId) throws RemoteException, SQLException;
}