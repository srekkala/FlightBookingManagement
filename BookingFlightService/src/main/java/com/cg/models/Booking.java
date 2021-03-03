package com.cg.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Booking {
	@Id
	private String bookingId;
	private String userId;
	private int noOfPassengers;

	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Booking(String bookingId, String userId, int noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.userId=userId;
		this.noOfPassengers=noOfPassengers;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
