package com.iupui.marketplace.model.beans;

import java.sql.Time;
import java.util.List;

public class Order {
	private int orderId;
	private Time orderDate;
	private String shippingType;
	private String orderStatus;
	private double orderSubtotal;
	private double tax;
	private double orderTotal;
	private List<Item> orderItems;
	
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderDate
	 */
	public Time getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Time orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the shippingType
	 */
	public String getShippingType() {
		return shippingType;
	}
	/**
	 * @param shippingType the shippingType to set
	 */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the orderSubtotal
	 */
	public double getOrderSubtotal() {
		return orderSubtotal;
	}
	/**
	 * @param orderSubtotal the orderSubtotal to set
	 */
	public void setOrderSubtotal(double orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}
	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}
	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	/**
	 * @return the orderTotal
	 */
	public double getOrderTotal() {
		return orderTotal;
	}
	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public List<Item> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
