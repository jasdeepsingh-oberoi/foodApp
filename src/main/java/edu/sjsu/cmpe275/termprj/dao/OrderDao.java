package edu.sjsu.cmpe275.termprj.dao;

import java.util.List;

import edu.sjsu.cmpe275.termprj.model.Order;
import java.util.Date;

public interface OrderDao {
	public List<Order> getAll();
	public Order getById(String id);
	public List<Order> getByStartTime(Date starttime);
	public void add(Order p);
	public void update(Order p);
	public void delete(String id);
}	
