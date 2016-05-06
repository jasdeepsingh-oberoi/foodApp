package edu.sjsu.cmpe275.termprj.controller;
import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import edu.sjsu.cmpe275.termprj.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import edu.sjsu.cmpe275.termprj.model.Order;
import java.util.Date;

import java.util.List;

/**
 * Created by wanghao on 5/1/16.
 */
@RestController
public class OrderController {

	@Autowired
	private OrderService ordersvc;

	@RequestMapping(value = "/orderForm", method = RequestMethod.GET)
	public ModelAndView getOrderForm() {
		System.out.println("in menu form");

		ModelAndView model = new ModelAndView("View/OrderTemplate");

		return model;
	}


	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public ResponseEntity<String> postOrder(@RequestBody Order order1) {

		ordersvc.add(order1);

		return new ResponseEntity<String>("success",HttpStatus.OK);
	}


	@RequestMapping(value = "/showorderhistory", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> showAllOrders() {

		List<Order> orderList = ordersvc.getAll();
		/*for(Order od: orderList) {
			System.out.println(od.getStart_time());
		}*/
		if (orderList == null) {
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/vieworderbystarttime/{starttime}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> displayOrderByStartTime(@PathVariable("starttime") Date starttime ) {

		List<Order> orderList = ordersvc.getByStartTime(starttime);
		if (orderList == null) {
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
		}
	}


	//Delete order By ID
	@RequestMapping(value = "/deleteorder/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteOrder(@PathVariable("id") String id) {

		Order order1 = ordersvc.getById(id);
		if (order1 == null) {
			throw new RuntimeException("order can not found");
		}
		ordersvc.delete(id);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}






}
