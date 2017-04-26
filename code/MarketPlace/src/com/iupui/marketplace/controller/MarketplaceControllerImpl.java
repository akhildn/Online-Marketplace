package com.iupui.marketplace.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    public static ConcurrentHashMap<Integer, Object> productLockMap = new ConcurrentHashMap<Integer, Object>();

	public MarketplaceControllerImpl() throws RemoteException{
	    // makes connection to database on starting the server.
		MarketplaceDBConnection.getMarketplaceDbConnection();
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

	// returns true is product is updated.
    @Override
    public boolean handleUpdateProduct(Account session, Product product) throws RemoteException, SQLException {
		boolean status;
        ProductDAO productDAO = new ProductDAO();
        System.out.println(Thread.currentThread().getName() +" -: update product entry ");

        // checks if there is already an object attached to the mentioned key: productId, if no object is associated
        // then a object is created and associated to it. If yes, then it will return the existing object
        // Ref : https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentMap.html#putIfAbsent(K,%20V)
        MarketplaceControllerImpl.productLockMap.putIfAbsent(product.getProductId(), new Object());

        // synchronized on productId, i.e when customer thread is trying to processing a order of a specific product
        // and admin thread is trying update the same product, we may get issues when this happen concurrently, to avoid
        // update and purchase happen same time we used product id as the monitor object criteria to achieve
        // synchronization. To achieve this synchronization between methods of 2 different classes i.e. purchase
        // and update product method, we need make use of the same monitor object to achieve synchronization.

        // this also ensures no 2 admin threads can update an same product at the same time
        synchronized(MarketplaceControllerImpl.productLockMap.get(product.getProductId())){
			System.out.println(Thread.currentThread().getName() +" -: update product critical section ");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			status = productDAO.updateProduct(product);
        }
		System.out.println(Thread.currentThread().getName() +" -: update product exit ");
		return status;
    }

    @Override
    public boolean handleRemoveProduct(Account session, int productId) throws RemoteException, SQLException {
        boolean status;
        ProductDAO productDAO = new ProductDAO();
        System.out.println(Thread.currentThread().getName() +" -: remove product entry ");

        // checks if there is already an object attached to the mentioned key: productId, if no object is associated
        // then a object is created and associated to it. If yes, then it will return the existing object
        // Ref : https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentMap.html#putIfAbsent(K,%20V)
        MarketplaceControllerImpl.productLockMap.putIfAbsent(productId, new Object());

        // synchronized on productId, i.e when customer thread is trying to processing a order of a specific product
        // and admin thread is trying update the same product, we may get issues when this happen concurrently, to avoid
        // update and purchase happen same time we used product id as the monitor object criteria to achieve
        // synchronization. To achieve this synchronization between methods of 2 different classes i.e. purchase
        // and update product method, we need make use of the same monitor object to achieve synchronization.

        // this also ensures no 2 admin threads can remove an same product at the same time

        synchronized(MarketplaceControllerImpl.productLockMap.get(productId)){
            System.out.println(Thread.currentThread().getName() +" -: remove product critical section ");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            status = productDAO.removeProduct(productId);
        }
        System.out.println(Thread.currentThread().getName() +" -: remove product exit ");
        return  status;
    }

    // Will return true is item is added to cart
	@Override
	public boolean handleAddToCart(Account account, Product product, int quantity) throws RemoteException,
            SQLException {
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
	public Order handlePlaceOrder(Account session, ShoppingCart shoppingCart, String shippingAddress)
			throws RemoteException, SQLException {
		ProductDAO productDAO = new ProductDAO();
		ShoppingCartDAO shoppingCartDAO= new ShoppingCartDAO();
		OrderDAO orderDAO= new OrderDAO();
		List<Item> orderItems = productDAO.processOrderItems(shoppingCart);
		Order order = orderDAO.placeOrder(session, orderItems,shippingAddress);
		shoppingCartDAO.clearCart(shoppingCart.getCartId());
		return order;
	}

	// returns all orders which were placed by the user
	@Override
	public List<Order> handleGetOrderHistory(Account account) throws RemoteException, SQLException {
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orderList = orderDAO.getUserOrderHistory(account);
		return orderList;
	}

	@Override
	public ShoppingCart handleClearCart(Account account, int cartId) throws RemoteException , SQLException {
		ShoppingCartDAO shoppingCartDAO = new ShoppingCartDAO();
		shoppingCartDAO.clearCart(cartId);
		ShoppingCart shoppingCart = shoppingCartDAO.getCartDetails(account);
		return  shoppingCart;
	}

}
