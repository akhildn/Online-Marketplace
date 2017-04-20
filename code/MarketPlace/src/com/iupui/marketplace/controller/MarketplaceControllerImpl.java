package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import com.iupui.marketplace.database.MarketplaceDBConnection;
import com.iupui.marketplace.dao.AccountDAO;
import com.iupui.marketplace.dao.OrderDAO;
import com.iupui.marketplace.dao.ProductDAO;
import com.iupui.marketplace.dao.ShoppingCartDAO;
import com.iupui.marketplace.model.beans.*;

// Ryan: Please include useful comments in each file.
// Fixed: Comments are included in each file.
public class MarketplaceControllerImpl extends UnicastRemoteObject implements MarketplaceController {
	

	private static final long serialVersionUID = 1L;
	// remove
    OrderDAO orderDAO;

	public MarketplaceControllerImpl() throws RemoteException{
		MarketplaceDBConnection.getMarketplaceDbConnection();
		//remove
        this.orderDAO = new OrderDAO();
    }

	// to check if connection was made
	@Override
	public int connect() throws RemoteException {
	    Thread thread = Thread.currentThread();
	    // prints out thread id and name on which client has made a connection
	    System.out.println(thread.getId()+" "+thread.getName());
		return 1;
	}

	// Login Handler : checks user credentials are valid or not.
	@Override
	public Account handleLogin(String username, String password) throws RemoteException, SQLException {
		// Below method checks if entered credentials are actually valid.
		AccountDAO accountDAO = new AccountDAO();
		boolean isValid = accountDAO.validateCredentials(username,password);
		System.out.println(isValid);
		if(isValid){
			 return accountDAO.getAccountDetails(username);
		}
		return null;
	}

	// returns product list i.e. all the products which are to be displayed
	@Override
	public List<Product> handleBrowseItems() throws RemoteException, SQLException {
		ProductDAO productDAO = new ProductDAO();
		List<Product> productList = productDAO.returnItemList();
		return productList;
	}

	// returns product object which contains product details
	@Override
	public Product handlegetProductDetails(int productId) throws RemoteException, SQLException {
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProductDetails(productId);
		return product;
	}

	// returns true is item is added
	@Override
	public boolean handleAddItem(Account account, Product product) throws RemoteException, SQLException {
		ProductDAO productDAO = new ProductDAO();
	    return productDAO.addNewItem(product);
	}

	// Will return true is item is added to cart
	@Override
	public boolean handleAddToCart(Account account, Product product, int quantity) throws RemoteException, SQLException {
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        return shoppingCartDAO.addToCart(account,product,quantity);

	}

	// return shopping cart object of logged in user, cart has list of items which were added by him
    @Override
    public ShoppingCart handleGetCartDetails(Account session) throws RemoteException, SQLException {
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
        return shoppingCartDAO.getCartDetails(session);
    }

    // passes list of items from shopping cart, these items are processed i.e. checks if quantity user wanted is still
	// available, if available that item will be placed, and returns order which contains order details, items which
	// were placed and which were not.
	// shopping cart is cleared once order iss processed
	@Override
	public Order handlePlaceOrder(Account session, ShoppingCart shoppingCart, Address shippingAddress) throws RemoteException, SQLException {
		ProductDAO productDAO = new ProductDAO();
		List<Item> orderItems = productDAO.processOrderItems(shoppingCart);
		Order order = orderDAO.placeOrder(session, orderItems,shippingAddress);
		//shoppingCartDAO.clearCart(session.getUsername());
		return order;
	}

	// returns all orders which were placed by the user
	@Override
	public List<Order> handleGetOrderHistory(Account account) throws RemoteException {
		List<Order> orderList = orderDAO.getUserOrderHistory(account);
		return orderList;
	}


	//TODO : All the below methods are yet to be implemented
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


}
