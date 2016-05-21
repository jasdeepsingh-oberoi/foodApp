package edu.sjsu.cmpe275.termprj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_details")
public class Order implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id", unique = true, nullable = false)
    private int order_id;
    @Column(name = "chef_id")
    private String chef_id;
    @Column(name = "email")
    private String email;
    @Column(name = "start_time")
    private Date  start_time;
    @Column(name = "end_time")
    private Date  end_time;
    @Column(name = "pickup_time")
    private Date  pickup_time;
    @Column(name = "status")
    private String status;
    @Column(name = "order_placed_date")
    private Date order_placed_date;
    @Column (name = "total_price")
    private String total_price;
    
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "order")
    @JsonIgnoreProperties(value = "order")
    private List<UserOrderDetails> orderDetailsList = new ArrayList<UserOrderDetails>();
    
    
    /*@Column(name = "menu_id")
    private String menu_id;*/
    @Column (name = "order_date")
    private Date order_date;
    
    public void addDetails(UserOrderDetails uoDetails){
    	uoDetails.setOrder(this);
    	this.orderDetailsList.add(uoDetails);
    }
    
    public List<UserOrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}
	public void setOrderDetailsList(List<UserOrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getChef_id() {
		return chef_id;
	}
	public void setChef_id(String chef_id) {
		this.chef_id = chef_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getPickup_time() {
		return pickup_time;
	}
	public void setPickup_time(Date pickup_time) {
		this.pickup_time = pickup_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Date getOrder_placed_date() {
		return order_placed_date;
	}

	public void setOrder_placed_date(Date order_placed_date) {
		this.order_placed_date = order_placed_date;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	
}
