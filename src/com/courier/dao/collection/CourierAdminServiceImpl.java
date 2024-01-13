package com.courier.dao.collection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.courier.dao.CourierDatabase;
import com.courier.dao.ICourierAdminService;
import com.courier.entity.CourierCompany;
import com.courier.entity.Employee;
import com.courier.exception.InvalidEmployeeIdException;

public class CourierAdminServiceImpl extends CourierUserServiceImpl implements ICourierAdminService {

	private Connection connection;

	public CourierAdminServiceImpl(CourierCompany companyObj, Connection connection) {
		super(companyObj);
		this.connection = connection;
	}

	@Override
	public int addCourierStaff(Employee employee) throws InvalidEmployeeIdException {
		try {
			// Generate a unique ID based on the current size of the employees list
			int newEmployeeID = CourierDatabase.getEmployees().size() + 1;
			employee.setEmployeeID(newEmployeeID);

			// Add the new courier staff member to the database
			CourierDatabase.addEmployee(employee);

			// Log successful insertion in the local data structure
			System.out.println("Courier staff member inserted successfully with ID: " + newEmployeeID);

			// Insert into the actual database using JDBC
			insertEmployeeIntoDatabase(employee);

			return newEmployeeID;
		} catch (Exception e) {
			// Log exception
			System.err.println("Error inserting courier staff member: " + e.getMessage());
			e.printStackTrace();

			// Throw custom exception
			throw new InvalidEmployeeIdException("Failed to add courier staff member");
		}
	}

	// Helper method to insert an employee into the actual database using JDBC
	// Inside CourierAdminServiceImpl class

	private void insertEmployeeIntoDatabase(Employee employee) throws SQLException {
		try {
			String query = "INSERT INTO employee (Name, Email, ContactNumber, Role, Salary) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, employee.getName());
			pstmt.setString(2, employee.getEmail());
			pstmt.setString(3, employee.getContactNumber());
			pstmt.setString(4, employee.getRole());
			pstmt.setDouble(5, employee.getSalary());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Failed to insert employee, no rows affected.");
			}

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					employee.setEmployeeID(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Failed to obtain auto-generated key.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace(); // Handle exception properly in a real-world scenario
			throw new SQLException("Failed to insert employee into the database");
		}
	}

//	@Override
//	public int addCourierStaff(Employee employee) {
//		// Generate a unique ID based on the current size of the employees list
//		int newEmployeeID = CourierDatabase.getEmployees().size() + 1;
//		employee.setEmployeeID(newEmployeeID);
//
//		// Add the new courier staff member to the database
//		CourierDatabase.addEmployee(employee);
//
//		return newEmployeeID;
//	}
}
