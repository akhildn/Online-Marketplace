package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.iupui.marketplace.dao.AccountDAO;
import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ProductCategory;

// Ryan: Please include usefull comments in each file.
public class MarketplaceControllerImpl extends UnicastRemoteObject implements MarketplaceController {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MarketplaceControllerImpl() throws RemoteException{}
	@Override
	public int connect() throws RemoteException {
		return 1;
	}

	@Override
	public String welcomeMessage() throws RemoteException {
		return "Welcome IUPUI OOAD Marketplace !!!";
	}

	@Override
	public Account handleLogin(String username, String password) throws RemoteException {
		AccountDAO	accountDAO = new AccountDAO();
		boolean isValid = accountDAO.validateCredentials(username,password);
		if(isValid){
			 return accountDAO.getAccountDetails(username);
		}
		return null;
	}

	@Override
	public List<Product> handleBrowseItems() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategory> handleListProductCategories() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> handleBrowseItemsByCategoryId(int categoryId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> handleBrowseItemsByCategoryName(String categoryName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> handleBrowseItemsByProductName(String productName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product handlegetProductDetails(int productId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
