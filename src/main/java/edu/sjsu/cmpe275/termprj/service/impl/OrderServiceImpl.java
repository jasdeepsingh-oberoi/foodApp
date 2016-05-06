package edu.sjsu.cmpe275.termprj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.termprj.dao.OrderDao;
import edu.sjsu.cmpe275.termprj.model.Order;
import edu.sjsu.cmpe275.termprj.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	
	public List<Order> getAll() {
		
		return orderDao.getAll();
	}

	public Order getById(String id) {
		return orderDao.getById(id);
	}

	public List<Order> getByStartTime(Date starttime) {
		return orderDao.getByStartTime(starttime);
	}

	public void add(Order p) {
		orderDao.add(p);
	}

	public void update(Order p) {
		orderDao.update(p);
	}

	public void delete(String id) {
		orderDao.delete(id);
	}
	
}
