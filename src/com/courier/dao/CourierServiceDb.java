package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.courier.entity.Courier;
import com.courier.util.DBConnection;

public class CourierServiceDb {

	private static Connection connection;

	public CourierServiceDb() {
		connection = DBConnection.getConnection();
	}

	// Method to insert a new courier order into the database
	public void insertOrder(Courier courier) {
		try {
//			System.out.println("inside insert");
			String query = "INSERT INTO courier (SenderName, SenderAddress, ReceiverName, ReceiverAddress, "
					+ "Weight, Status, TrackingNumber, DeliveryDate, UserID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			String trackingNumber = generateUniqueTrackingNumber(); // Generate a unique tracking number
			courier.setTrackingNumber(trackingNumber);

			PreparedStatement pstmt = connection.prepareStatement(query);
//			System.out.println("inside set");
			pstmt.setString(1, courier.getSenderName());
			pstmt.setString(2, courier.getSenderAddress());
			pstmt.setString(3, courier.getReceiverName());
			pstmt.setString(4, courier.getReceiverAddress());
			pstmt.setDouble(5, courier.getWeight());
			pstmt.setString(6, courier.getStatus());
			pstmt.setString(7, courier.getTrackingNumber());
			pstmt.setDate(8, new java.sql.Date(courier.getDeliveryDate().getTime()));
			pstmt.setInt(9, courier.getUserId());

			pstmt.executeUpdate();
//			System.out.println("inserted");

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
		}
	}

	// Method to generate a unique tracking number
	private String generateUniqueTrackingNumber() {
		return "TN" + System.currentTimeMillis();
	}

	// Method to update courier status in the database
	public void updateCourierStatus(int courierId, String newStatus) {
		try {
//			System.out.println("inside update");
			String query = "UPDATE courier SET status = ? WHERE CourierID = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
//			System.out.println("set string");
			pstmt.setString(1, newStatus);
//			System.out.println("set courier id");
			pstmt.setInt(2, courierId);

			pstmt.executeUpdate();
//			System.out.println("updated");

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
		}
	}

	// Method to retrieve delivery history from the database for a specific parcel
	public List<Courier> getDeliveryHistory(int courierId) {
		List<Courier> deliveryHistory = new ArrayList<>();

		try {
			String query = "SELECT * FROM courier WHERE CourierID = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, courierId);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Courier courier = new Courier();
				courier.setCourierID(resultSet.getInt("CourierID"));
				courier.setSenderName(resultSet.getString("SenderName"));
				courier.setSenderAddress(resultSet.getString("SenderAddress"));
				courier.setReceiverName(resultSet.getString("ReceiverName"));
				courier.setReceiverAddress(resultSet.getString("ReceiverAddress"));
				courier.setWeight(resultSet.getDouble("Weight"));
				courier.setStatus(resultSet.getString("Status"));
				courier.setTrackingNumber(resultSet.getString("TrackingNumber"));
				courier.setDeliveryDate(resultSet.getDate("DeliveryDate"));
				courier.setUserId(resultSet.getInt("UserID"));

				deliveryHistory.add(courier);
			}

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
		}

		return deliveryHistory;
	}

	// Method to generate and display a shipment status report
	public void generateShipmentStatusReport() {
		try {
			String query = "SELECT Status, COUNT(*) AS count FROM courier GROUP BY status";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String status = resultSet.getString("Status");
				int count = resultSet.getInt("count");

				// Display or format the report as needed
				System.out.println("Status: " + status + ", Count: " + count);
			}

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
		}
	}

	// Method to generate and display a revenue report
	public void generateRevenueReport() {
		try {
			String query = "SELECT MONTH(PaymentDate) AS month, YEAR(PaymentDate) AS year, SUM(amount) AS totalRevenue "
					+ "FROM payment GROUP BY YEAR(PaymentDate), MONTH(PaymentDate)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int month = resultSet.getInt("month");
				int year = resultSet.getInt("year");
				double totalRevenue = resultSet.getDouble("totalRevenue");

				// Display or format the report as needed
				System.out.println("Month: " + month + ", Year: " + year + ", Total Revenue: " + totalRevenue);
			}

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
		}
	}

	//// Method to cancel an order
	// Method to cancel an order in the database
	public void cancelOrder(int orderId) {
		try {
			// Delete related records in the 'parcels' table
			String deleteParcelsQuery = "DELETE FROM parcels WHERE OrderID IN (SELECT OrderID FROM orders WHERE CourierID = ?)";
			PreparedStatement pstmtParcels = connection.prepareStatement(deleteParcelsQuery);
			pstmtParcels.setInt(1, orderId);
			pstmtParcels.executeUpdate();

			// Delete related records in the 'orders' table
			String deleteOrdersQuery = "DELETE FROM orders WHERE CourierID = ?";
			PreparedStatement pstmtOrders = connection.prepareStatement(deleteOrdersQuery);
			pstmtOrders.setInt(1, orderId);
			pstmtOrders.executeUpdate();

			System.out.println("Order with OrderID " + orderId + " cancelled successfully.");

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
		}
	}

}
