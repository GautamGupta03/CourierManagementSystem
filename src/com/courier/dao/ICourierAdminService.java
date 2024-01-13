package com.courier.dao;

import com.courier.entity.Employee;
import com.courier.exception.InvalidEmployeeIdException;

public interface ICourierAdminService {

	int addCourierStaff(Employee employee) throws InvalidEmployeeIdException;
}
