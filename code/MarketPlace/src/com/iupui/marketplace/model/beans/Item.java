package com.iupui.marketplace.model.beans;

import java.io.Serializable;

// Ryan: Please include useful comments in each file.
//Fixed: comments are provided

// holds details of an item present in an shopping cart
public class Item implements Serializable{
	private Product product;
	private int quantity;
	private double totalItemPrice;
	private boolean isAvailable;
	private String statusMessage;
	private boolean isNew;

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the totalItemPrice
	 */
	public double getTotalItemPrice() {
		return totalItemPrice;
	}
	/**
	 * @param totalItemPrice the totalItemPrice to set
	 */
	public void setTotalItemPrice(double totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}
	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	/**
	 * @param isAvailable the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	/**
	 * @return the isNew
	 */
	public boolean isNew() {
		return isNew;
	}
	/**
	 * @param isNew the isNew to set
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
}
