package com.courier.dao.collection;

import com.courier.dao.CourierDatabase;
import com.courier.dao.ICourierAdminService;
import com.courier.dao.array.CourierUserServiceCollectionImpl;
import com.courier.entity.CourierCompanyCollection;
import com.courier.entity.Employee;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceCollectionImpl
		implements ICourierAdminService {

	public CourierAdminServiceCollectionImpl(CourierCompanyCollection companyObj) {
		super(companyObj);
	}

	@Override
	public int addCourierStaff(Employee employee) {
		// Generate a unique ID based on the current size of the employees list
		int newEmployeeID = CourierDatabase.getEmployees().size() + 1;
		employee.setEmployeeID(newEmployeeID);

		// Add the new courier staff member to the database
		CourierDatabase.addEmployee(employee);

		return newEmployeeID;
	}

}
