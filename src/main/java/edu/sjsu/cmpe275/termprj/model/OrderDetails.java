package edu.sjsu.cmpe275.termprj.model;

import java.util.ArrayList;
import java.util.Date;

public class OrderDetails {

	private String email;
	private String id;
	private int totalPrepTime;
	private Date pickupTime;
	private Date startPrepTime;
	private String totalPrice;
	private ArrayList<MenuDetails> menuDetails;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTotalPrepTime() {
		return totalPrepTime;
	}
	public void setTotalPrepTime(int totalPrepTime) {
		this.totalPrepTime = totalPrepTime;
	}
	public ArrayList<MenuDetails> getMenuDetails() {
		return menuDetails;
	}
	public void setMenuDetails(ArrayList<MenuDetails> menuDetails) {
		this.menuDetails = menuDetails;
	}

	public Date getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}
	public Date getStartPrepTime() {
		return startPrepTime;
	}
	public void setStartPrepTime(Date startPrepTime) {
		this.startPrepTime = startPrepTime;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
}
