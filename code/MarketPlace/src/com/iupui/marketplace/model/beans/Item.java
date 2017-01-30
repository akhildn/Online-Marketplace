package com.iupui.marketplace.model.beans;

public class Item {
	private Product product;
	private int quantity;
	private double totalItemPrice;
	private boolean isAvailable;
	private boolean isNew;
	
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
