package com.cg.models;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Passenger")
public class Passenger implements Serializable{
	
	@Id
	private String passengerId;
	private String firstName;
	private String lastName;
	private String gender;
	private String bookingId;
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Passenger(String passengerId, String firstName, String lastName, String gender,String bookingId) {
		super();
		this.passengerId = passengerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.bookingId=bookingId;
	}
	public Passenger() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	
}
