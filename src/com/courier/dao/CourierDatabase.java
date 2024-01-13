package com.courier.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.courier.entity.Courier;
import com.courier.entity.CourierCompany;
import com.courier.entity.Employee;
import com.courier.entity.Location;
import com.courier.entity.Payment;
import com.courier.entity.User;

public class CourierDatabase {

	private static List<User> users = new ArrayList<>();
	private static List<Courier> couriers = new ArrayList<>();
	private static List<Employee> employees = new ArrayList<>();
	private static List<Location> locations = new ArrayList<>();
	private static List<CourierCompany> courierCompanies = new ArrayList<>();
	private static List<Payment> payments = new ArrayList<>();

	// Initialize some sample data in the constructor (for testing purposes)
	static {
		users.add(new User(1, "John Doe", "john@example.com", "password", "1234567890", "123 Main St"));
		employees.add(new Employee(1, "Courier Staff 1", "staff1@example.com", "9876543210", "Courier", 50000.0));
		locations.add(new Location(1, "Warehouse A", "456 Storage St"));
		courierCompanies.add(new CourierCompany("ABC Couriers", new ArrayList<>(), employees, locations));

		// Manually add an entry for Courier
		Courier courier = new Courier();
		courier.setCourierID(1);
		courier.setSenderName("SenderName");
		courier.setSenderAddress("SenderAddress");
		courier.setReceiverName("ReceiverName");
		courier.setReceiverAddress("ReceiverAddress");
		courier.setWeight(2.5);
		courier.setStatus("In Transit");
		courier.setTrackingNumber("TN123456789");
		courier.setDeliveryDate(new Date());
		courier.setUserId(1);

		addCourier(courier);
	}

	// Implement methods to interact with the database
	public static List<User> getUsers() {
		return users;
	}

	public static List<Courier> getCouriers() {
		return couriers;
	}

	public static List<Employee> getEmployees() {
		return employees;
	}

	public static List<Location> getLocations() {
		return locations;
	}

	public static List<CourierCompany> getCourierCompanies() {
		return courierCompanies;
	}

	public static List<Payment> getPayments() {
		return payments;
	}

	public static void addUser(User user) {
		users.add(user);
	}

	public static void addCourier(Courier courier) {
		couriers.add(courier);
	}

	public static void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public static void addLocation(Location location) {
		locations.add(location);
	}

	public static void addCourierCompany(CourierCompany courierCompany) {
		courierCompanies.add(courierCompany);
	}

	public static void addPayment(Payment payment) {
		payments.add(payment);
	}
}
