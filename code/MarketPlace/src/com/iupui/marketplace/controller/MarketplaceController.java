/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.util.List;

import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ProductCategory;

// Ryan: Please include usefull comments in each file.
// Fixed: Comments are included in each file.
public interface MarketplaceController extends java.rmi.Remote {
	public int connect() throws RemoteException;
	
	// Ryan: I am guessing this is just for this assignment - otherwise the method below could handle 
	// functionality.
	// Fixed: It was that specific assignment. Method removed.	
	// public String welcomeMessage() throws RemoteException;
	
	
	// login handler, checks if user credentials are valid.
	public Account handleLogin(String username, String password) throws RemoteException;
	
	//TODO : yet to be implemented based on requirement of the assignment
	public List<Product> handleBrowseItems() throws RemoteException;
	public List<ProductCategory> handleListProductCategories() throws RemoteException;
	public List<Product> handleBrowseItemsByCategoryId(int categoryId) throws RemoteException;
	public List<Product> handleBrowseItemsByCategoryName(String categoryName) throws RemoteException;
	public List<Product> handleBrowseItemsByProductName(String productName) throws RemoteException;
	public Product handlegetProductDetails(int productId) throws RemoteException;
}