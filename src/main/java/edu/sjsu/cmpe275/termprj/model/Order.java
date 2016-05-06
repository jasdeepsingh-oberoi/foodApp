package edu.sjsu.cmpe275.termprj.model;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.sjsu.cmpe275.termprj.util.JsonDateSerializer;


@Entity
@Table(name = "order_details")
public class Order {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_id")
    private String order_id;
    @Column(name = "chef_id")
    private String chef_id;
    @Column(name = "email")
    private String email;
    @Column(name = "start_time")
    private Date start_time;
    @Column(name = "end_time")
    private Date end_time;
    @Column(name = "pickup_time")
    private Date pickup_time;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "menu_id")
    private String menu_id;



    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setChef_id(String chef_id) {
        this.chef_id = chef_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public void setPickup_time(Date pickup_time) {
        this.pickup_time = pickup_time;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getOrder_id() {

        return order_id;
    }

    public String getChef_id() {
        return chef_id;
    }

    public String getEmail() {
        return email;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public Date getPickup_time() {
        return pickup_time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getMenu_id() {
        return menu_id;
    }
}
