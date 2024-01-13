package com.courier.dao;

import java.util.List;

import com.courier.entity.Courier;
import com.courier.exception.TrackingNumberNotFoundException;

public interface ICourierUserService {

	String placeOrder(Courier courierObj);

	String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException;

	boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException;

	List<Courier> getAssignedOrder(int courierStaffId);
}
