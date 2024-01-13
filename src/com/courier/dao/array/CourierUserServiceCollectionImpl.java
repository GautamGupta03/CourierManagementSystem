package com.courier.dao.array;

import java.util.ArrayList;
import java.util.List;

import com.courier.dao.CourierDatabase;
import com.courier.dao.ICourierUserService;
import com.courier.entity.Courier;
import com.courier.entity.CourierCompanyCollection;
import com.courier.exception.TrackingNumberNotFoundException;

public class CourierUserServiceCollectionImpl implements ICourierUserService {

	private CourierCompanyCollection companyObj;

	public CourierUserServiceCollectionImpl(CourierCompanyCollection companyObj) {
		this.companyObj = companyObj;
	}

	@Override
	public String placeOrder(Courier courierObj) {
		String trackingNumber = "TN" + System.currentTimeMillis();
//		String trackingNumber = "TN123456789";
		courierObj.setTrackingNumber(trackingNumber);

		CourierDatabase.addCourier(courierObj);

		return trackingNumber;
	}

	@Override
	public String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException {
		for (Courier courier : CourierDatabase.getCouriers()) {
			if (courier.getTrackingNumber().equals(trackingNumber)) {
				return courier.getStatus();
			}
		}
		throw new TrackingNumberNotFoundException("Order not found");
	}

	@Override
	public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException {
		List<Courier> couriers = CourierDatabase.getCouriers();

		for (int i = 0; i < couriers.size(); i++) {
			Courier courier = couriers.get(i);
			if (courier.getTrackingNumber().equals(trackingNumber)) {
				couriers.remove(i);
				return true;
			}
		}
		throw new TrackingNumberNotFoundException("Order not found");
	}

	@Override
	public List<Courier> getAssignedOrder(int courierStaffId) {
		List<Courier> assignedOrders = new ArrayList<>();

		for (Courier courier : CourierDatabase.getCouriers()) {
			if (courier.getUserId() == courierStaffId) {
				assignedOrders.add(courier);
			}
		}

		return assignedOrders;
	}
}