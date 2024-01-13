package com.courier.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.courier.dao.CourierServiceDb;
import com.courier.dao.ICourierAdminService;
import com.courier.dao.ICourierUserService;
import com.courier.dao.array.CourierUserServiceCollectionImpl;
import com.courier.dao.collection.CourierAdminServiceImpl;
import com.courier.entity.Courier;
import com.courier.entity.CourierCompany;
import com.courier.entity.CourierCompanyCollection;
import com.courier.entity.Employee;
import com.courier.exception.InvalidEmployeeIdException;

public class MainModule {

	public static void main(String[] args) throws InvalidEmployeeIdException, SQLException {
		// Creating Courier Company and Collections
		CourierCompanyCollection companyCollection = new CourierCompanyCollection("XYZ Courier Company", null, null,
				null);
		CourierCompany courierCompany = new CourierCompany("XYZ Courier Company", null, null, null);

		Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courier_management",
				"root", "xxx123xxx");
		// Creating instances of services
		ICourierAdminService adminService = new CourierAdminServiceImpl(courierCompany, databaseConnection);
		ICourierUserService userService = new CourierUserServiceCollectionImpl(companyCollection);

		// Adding a courier staff member dynamically
		Employee newCourierStaff = new Employee();
		newCourierStaff.setEmployeeID(1); // Set the EmployeeID explicitly or use a unique value
		newCourierStaff.setName("Smith lol");
		newCourierStaff.setEmail("smith.lol@example.com");
		newCourierStaff.setContactNumber("123450");
		newCourierStaff.setRole("Staff");
		newCourierStaff.setSalary(5000.0);

		// Adding the courier staff member
		int newEmployeeID = adminService.addCourierStaff(newCourierStaff);
		System.out.println("New Courier Staff added with ID: " + newEmployeeID);

//		// Placing a new courier order 
//		Courier newCourierOrder = new Courier();
//		newCourierOrder.setSenderName("Sender");
//		newCourierOrder.setSenderAddress("Sender Address");
//		newCourierOrder.setReceiverName("Receiver");
//		newCourierOrder.setReceiverAddress("Receiver Address");
//		newCourierOrder.setWeight(5.0);
//		newCourierOrder.setStatus("Pending");
//		newCourierOrder.setDeliveryDate(new Date());
//		newCourierOrder.setUserId(newEmployeeID);
//
//		String trackingNumber = userService.placeOrder(newCourierOrder);
//		System.out.println("New Courier Order placed with Tracking Number: " + trackingNumber);

		// Getting order status dynamically
//		try {
//			String orderStatus = userService.getOrderStatus(trackingNumber);
//			System.out.println("Order Status: " + orderStatus);
//		} catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//		}

//		// Canceling the order dynamically
//		try {
//			boolean isCancelled = userService.cancelOrder(trackingNumber);
//			if (isCancelled) {
//				System.out.println("Order with Tracking Number " + trackingNumber + " cancelled successfully.");
//			} else {
//				System.out.println("Order with Tracking Number " + trackingNumber + " not found.");
//			}
//		} catch (Exception e) {
//			System.out.println("Error: " + e.getMessage());
//		}

//		// Getting assigned orders for courier staff dynamically
//		List<Courier> assignedOrders = userService.getAssignedOrder(newEmployeeID);
//		System.out.println("Assigned Orders for Courier Staff ID " + newEmployeeID + ":");
//		for (Courier order : assignedOrders) {
//			System.out.println(order.toString());
//		}

		// Testing database operations
		CourierServiceDb courierServiceDb = new CourierServiceDb();

		// Inserting a new courier order into the database
		Courier dbCourier = new Courier(2, "DBSender", "DBSender Address", "DBReceiver", "DBReceiver Address", 10.0,
				"Pending", null, new Date(), 1);
		courierServiceDb.insertOrder(dbCourier);

		// Updating courier status in the database
		courierServiceDb.updateCourierStatus(2, "Delivered");

		// Retrieving delivery history from the database
		List<Courier> deliveryHistory = courierServiceDb.getDeliveryHistory(2);
		System.out.println("Delivery History from the Database:");
		for (Courier historyOrder : deliveryHistory) {
			System.out.println(historyOrder.toString());
		}

		// Generating and displaying a shipment status report
		courierServiceDb.generateShipmentStatusReport();

		// Canceling the order in the database
//		courierServiceDb.cancelOrder(2);

		// Generating and displaying a revenue report
		courierServiceDb.generateRevenueReport();
	}
}
