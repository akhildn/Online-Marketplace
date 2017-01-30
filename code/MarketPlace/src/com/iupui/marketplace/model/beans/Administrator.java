package com.iupui.marketplace.model.beans;

public class Administrator {
	private String adminFirstName;
	private String adminLastName;
	private String adminId;
	private Account account;
	/**
	 * @return the adminFirstName
	 */
	public String getAdminFirstName() {
		return adminFirstName;
	}
	/**
	 * @param adminFirstName the adminFirstName to set
	 */
	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}
	/**
	 * @return the adminLastName
	 */
	public String getAdminLastName() {
		return adminLastName;
	}
	/**
	 * @param adminLastName the adminLastName to set
	 */
	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}
	/**
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
