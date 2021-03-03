package com.cg.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String flightFrom;
	private String flightTo;
	private String date;
	private int fare;
	private int seatCapacity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlightFrom() {
		return flightFrom;
	}
	public void setFlightFrom(String flightFrom) {
		this.flightFrom = flightFrom;
	}
	public String getFlightTo() {
		return flightTo;
	}
	public void setFlightTo(String flightTo) {
		this.flightTo = flightTo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	public Flight(int id, String flightFrom, String flightTo, String date, int fare, int seatCapacity) {
		super();
		this.id = id;
		this.flightFrom = flightFrom;
		this.flightTo = flightTo;
		this.date = date;
		this.fare = fare;
		this.seatCapacity = seatCapacity;
	}
	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
