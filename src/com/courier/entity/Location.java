package com.courier.entity;

public class Location {

	private int locationID;
	private String locationName;
	private String address;

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Location [locationID=" + locationID + ", locationName=" + locationName + ", address=" + address + "]";
	}

	public Location(int locationID, String locationName, String address) {
		super();
		this.locationID = locationID;
		this.locationName = locationName;
		this.address = address;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

}
