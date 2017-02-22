package com.iupui.marketplace.model.beans;

import java.util.List;

// Ryan: make sure to include useful comments in each file.
public class ShoppingCart {
	private int cartId;
	private double cartTotal;
	private List<Item> cartItems;
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
