package com.iupui.marketplace.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Ryan: make sure to include useful comments in each file.
//holds details of shopping cart of an user
public class ShoppingCart implements Serializable {
	private int cartId;
	private double cartTotal;
	private List<Item> cartItems;
	private String userName;

	public ShoppingCart(){
		cartItems = new ArrayList<>();
		cartTotal =0;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the cartId
	 */
	public int getCartId() {
		return cartId;
	}
	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	/**
	 * @return the cartTotal
	 */
	public double getCartTotal() {
		return cartTotal;
	}
	/**
	 * @param cartTotal the cartTotal to set
	 */
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
	/**
	 * @return the cartItems
	 */
	public List<Item> getCartItems() {
		return cartItems;
	}
	/**
	 * @param cartItems the cartItems to set
	 */
	public void setCartItems(List<Item> cartItems) {
		this.cartItems = cartItems;
	}
	
}
