package com.fynd.batchprocessing.pojo;

public class CustomerPayload {

	private String eventType;
	
	private String customerName;
	
	private String customerAddress;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Override
	public String toString() {
		return "CustomerPayload [eventType=" + eventType + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + "]";
	}
	
	
	
}
