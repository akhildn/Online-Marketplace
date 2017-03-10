package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.iupui.marketplace.dao.AccountDAO;
import com.iupui.marketplace.model.beans.Account;
import com.iupui.marketplace.model.beans.Product;
import com.iupui.marketplace.model.beans.ProductCategory;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.
public class MarketplaceControllerImpl extends UnicastRemoteObject implements MarketplaceController {
	

	private static final long serialVersionUID = 1L;

	public MarketplaceControllerImpl() throws RemoteException{}

	// to check if connection was made
	@Override
	public int connect() throws RemoteException {
		return 1;
	}

	// Login Handler : checks user credentials are valid or not.
	@Override
	public Account handleLogin(String username, String password) throws RemoteException {
		AccountDAO	accountDAO = new AccountDAO();
		// Below method checks if entered credentials are actually valid.
		boolean isValid = accountDAO.validateCredentials(username,password);
		if(isValid){
			 return accountDAO.getAccountDetails(username);
		}
		return null;
	}


	//TODO : All the below methods are yet to be implemented
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

	// Will return true when entered, only admin can use this method
	@Override
	public boolean handleEditItemName(Account account, int productId, String productName) throws RemoteException {
		/* System.out.println( productId + " will be edited and edit is done by "+account.getUsername()); */
		return true;

	}

	// Will return true when entered, only customer can use this method
	@Override
	public boolean handleAddToCart(Account account, int productId, int quantity) throws RemoteException {
		// System.out.println( productId + " has been  added to cart ");
		return true;
	}

}
