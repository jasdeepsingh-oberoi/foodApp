package edu.sjsu.cmpe275.termprj.model;

import javax.persistence.ManyToOne;

import javax.persistence.Column;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "user_orders_details")
public class UserOrderDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_order_details_id" ,unique = true, nullable = false)
    private int user_order_details_id;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "menu_item_name")
	private String menu_item_name;
	
	@Column(name = "unit_price")
	private String unit_price;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	@JsonIgnoreProperties(value = "orderDetailsList")
	private Order order;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getUser_order_details_id() {
		return user_order_details_id;
	}

	public void setUser_order_details_id(int user_order_details_id) {
		this.user_order_details_id = user_order_details_id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMenu_item_name() {
		return menu_item_name;
	}

	public void setMenu_item_name(String menu_item_name) {
		this.menu_item_name = menu_item_name;
	}
}
