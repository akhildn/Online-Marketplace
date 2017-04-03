package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.iupui.marketplace.dao.AccountDAO;
import com.iupui.marketplace.dao.OrderDAO;
import com.iupui.marketplace.dao.ProductDAO;
import com.iupui.marketplace.dao.ShoppingCartDAO;
import com.iupui.marketplace.model.beans.*;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.
public class MarketplaceControllerImpl extends UnicastRemoteObject implements MarketplaceController {
	

	private static final long serialVersionUID = 1L;
    AccountDAO accountDAO;
    ProductDAO productDAO;
    ShoppingCartDAO shoppingCartDAO;
    OrderDAO orderDAO;
	public MarketplaceControllerImpl() throws RemoteException{
       	this.accountDAO = new AccountDAO();
        this.productDAO = new ProductDAO();
        this.shoppingCartDAO = new ShoppingCartDAO();
        this.orderDAO = new OrderDAO();
    }

	// to check if connection was made
	@Override
	public int connect() throws RemoteException {
		return 1;
	}

	// Login Handler : checks user credentials are valid or not.
	@Override
	public Account handleLogin(String username, String password) throws RemoteException {
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
		List<Product> productList = productDAO.returnItemList();
		return productList;
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
		Product product = productDAO.getDetails(productId);
		return product;
	}

	// Will return true when entered, only admin can use this method
	@Override
	public boolean handleAddItem(Account account, Product product) throws RemoteException {
	    return productDAO.addNewItem(product);
	}

	// Will return true when entered, only customer can use this method
	@Override
	public boolean handleAddToCart(Account account, Product product, int quantity) throws RemoteException {
        return shoppingCartDAO.addToCart(account,product,quantity);

	}

    @Override
    public ShoppingCart handleGetCartDetails(Account session)throws RemoteException {
        return shoppingCartDAO.handleGetCartDetials(session);
    }

	@Override
	public Order handlePlaceOrder(Account session, ShoppingCart shoppingCart, Address shippingAddress) throws RemoteException {
		List<Item> orderItems = productDAO.processOrderItems(shoppingCart);
		Order order = orderDAO.placeOrder(session, orderItems,shippingAddress);
		shoppingCartDAO.clearCart(session.getUsername());
		return order;
	}

}
