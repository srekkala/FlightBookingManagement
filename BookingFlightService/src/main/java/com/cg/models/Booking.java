package com.cg.models;


import org.springframework.data.annotation.Id;
import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;
@RedisHash("Booking")
public class Booking implements Serializable{
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
