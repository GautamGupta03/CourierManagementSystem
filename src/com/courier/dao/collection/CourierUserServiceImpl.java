package com.courier.dao.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.courier.dao.ICourierUserService;
import com.courier.entity.Courier;
import com.courier.entity.CourierCompany;

public class CourierUserServiceImpl implements ICourierUserService {

	private CourierCompany companyObj;

	public CourierUserServiceImpl(CourierCompany companyObj) {
		this.companyObj = companyObj;
	}

	@Override
	public String placeOrder(Courier courierObj) {
		if (courierObj != null) {
			String trackingNumber = UUID.randomUUID().toString();
			courierObj.setTrackingNumber(trackingNumber);

			companyObj.getCourierDetails().add(courierObj);

			return trackingNumber;
		} else {
			return null;
		}
	}

	@Override
	public String getOrderStatus(String trackingNumber) {
		for (Courier courier : companyObj.getCourierDetails()) {
			if (courier.getTrackingNumber().equals(trackingNumber)) {
				return courier.getStatus(); // Assuming status is a field in the Courier class
			}
		}
		return "Order Not Found";
	}

	@Override
	public boolean cancelOrder(String trackingNumber) {
		for (Courier courier : companyObj.getCourierDetails()) {
			if (courier.getTrackingNumber().equals(trackingNumber)) {
				companyObj.getCourierDetails().remove(courier);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Courier> getAssignedOrder(int courierStaffId) {
		List<Courier> assignedOrders = new ArrayList<>();

		for (Courier courier : companyObj.getCourierDetails()) {
			if (courier.getUserId() == courierStaffId) {
				assignedOrders.add(courier);
			}
		}

		return assignedOrders;
	}
}
