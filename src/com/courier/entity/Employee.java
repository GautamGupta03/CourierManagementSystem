package com.courier.entity;

public class Employee {

	private int employeeID;
	private String name;
	private String email;
	private String contactNumber;
	private String role;
	private double salary;

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", name=" + name + ", email=" + email + ", contactNumber="
				+ contactNumber + ", role=" + role + ", salary=" + salary + "]";
	}

	public Employee(int employeeID, String name, String email, String contactNumber, String role, double salary) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.salary = salary;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

}
