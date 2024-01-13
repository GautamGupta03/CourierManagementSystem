package com.courier.entity;

public class User {

	private int userID;

	private String userName;

	private String email;

	private String password;

	private String contactNumber;

	private String address;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", contactNumber=" + contactNumber + ", address=" + address + "]";
	}

	public User(int userID, String userName, String email, String password, String contactNumber, String address) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.contactNumber = contactNumber;
		this.address = address;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
