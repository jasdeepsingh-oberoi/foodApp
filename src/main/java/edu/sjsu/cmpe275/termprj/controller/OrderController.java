package edu.sjsu.cmpe275.termprj.controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe275.termprj.model.MenuDetails;
import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.model.Order;
import edu.sjsu.cmpe275.termprj.model.OrderDetails;
import edu.sjsu.cmpe275.termprj.model.UserOrderDetails;
import edu.sjsu.cmpe275.termprj.service.OrderService;

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
	public ResponseEntity<Object> postOrder(@RequestBody String obj) {
		Order orderInCtrl = null;
		System.out.println(obj);
		int totalprepTime = 0;
		boolean conflict = false;
		boolean userSelectedForTime = false;
		Date finalDate;
		Date firstPickupTime = null;
		String orderdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		ObjectMapper mapper = new ObjectMapper();
		List<MenuItem> listMenuObj = new ArrayList();
		List<MenuItem> dbMenuData = new ArrayList();
		ArrayList<MenuDetails> cartDetails = new ArrayList<MenuDetails>();
		OrderDetails orDetObj = new OrderDetails();
		try {
			JsonNode jsonObj = mapper.readTree(obj);
			for(int i= 0; i<jsonObj.path("items").size();i++){
				MenuItem menuObj = new MenuItem();
				menuObj.setId(jsonObj.path("items").path(i).path("id").toString());
				MenuDetails menuDetObj = new MenuDetails();
				menuDetObj.setCategory(jsonObj.path("items").path(i).path("category").toString());
				menuDetObj.setId(jsonObj.path("items").path(i).path("id").textValue());
				menuDetObj.setName(jsonObj.path("items").path(i).path("name").textValue());
				menuDetObj.setQuantity(jsonObj.path("quantity").path(i).textValue());
				menuDetObj.setUnit_price(jsonObj.path("items").path(i).path("unit_price").textValue());
				cartDetails.add(menuDetObj);
				listMenuObj.add(menuObj);
			}
			System.out.println(jsonObj);
			orDetObj.setMenuDetails(cartDetails);
			orDetObj.setEmail(jsonObj.path("email").textValue());
			orDetObj.setTotalPrice(jsonObj.path("totalprice").toString());
			System.out.println(orDetObj.getTotalPrice());
			String pickuptime = jsonObj.path("pickuptime").textValue();
			if(pickuptime.substring(0, 10).equals("01-01-1970")){
				userSelectedForTime = true;
			}
			/*orderdate = pickuptime.substring(0, 10);
			String minStartPrepTime = orderdate + " 05:00:00";
			String mornPickupTime = orderdate + " 06:00:00";
			firstPickupTime = sdf.parse(mornPickupTime);
			Date date = sdf.parse(pickuptime);

			Date allowedStartPrepTime = sdf.parse(minStartPrepTime);
			System.out.println("pickuptime: " + pickuptime);
			System.out.println("allowedStartPrepTime" + allowedStartPrepTime);*/

			dbMenuData = ordersvc.getPrepTime(listMenuObj);
			List <Object> deletedItemList = new ArrayList<Object>();


			if(dbMenuData.size() != jsonObj.path("items").size()){
				HashMap<String, Integer> nameMap = new HashMap<String, Integer>();
				for(int z=0;z<jsonObj.path("items").size();z++){
					nameMap.put(jsonObj.path("items").path(z).path("name").textValue(),0);
					for(int y=0;y<dbMenuData.size();y++){
						String j = jsonObj.path("items").path(z).path("name").textValue();
						String d = dbMenuData.get(y).getName();
						if(j.equals(d)){
							nameMap.put(j,1);
						}
					}
				}

				for(Object o:nameMap.keySet()){
					if(nameMap.get(o).equals(0)){
						deletedItemList.add(o);
					}						
				}
				return new ResponseEntity<Object>(deletedItemList,HttpStatus.NOT_FOUND);
			}

			for(int j=0; j<dbMenuData.size();j++){
				for(int k=0; k<jsonObj.path("items").size();k++){

					String userInputCartData = jsonObj.path("items").path(k).path("id").textValue();
					String userInputQtyData = jsonObj.path("quantity").path(k).textValue();

					if(userInputCartData.equals(dbMenuData.get(j).getId())){
						totalprepTime += Integer.parseInt(userInputQtyData) * dbMenuData.get(j).getPrep_time();
						System.out.println(totalprepTime);
					}

				}
			}
			
			Date sysdate = new Date();
			String sysdateString = sdf.format(sysdate);
			if(userSelectedForTime == true){
				pickuptime = sysdateString;
			}
			orderdate = pickuptime.substring(0, 10);
			String minStartPrepTime = orderdate + " 05:00:00";
			String mornPickupTime = orderdate + " 06:00:00";
			firstPickupTime = sdf.parse(mornPickupTime);
			Date date = sdf.parse(pickuptime);

			Date allowedStartPrepTime = sdf.parse(minStartPrepTime);
			System.out.println("pickuptime: " + pickuptime);
			System.out.println("allowedStartPrepTime" + allowedStartPrepTime);
			
			
			if(userSelectedForTime == true){
				date = sdf.parse(sysdateString);
				finalDate = allowedStartPrepTime;
				System.out.println("in sysdate block");
				System.out.println(finalDate);
			}else{
				if((date.getTime() - (totalprepTime*60000)-(60*60000)) < allowedStartPrepTime.getTime()){

					conflict = true;
					finalDate = allowedStartPrepTime;
					orDetObj.setPickupTime(date);
				}else {
					finalDate = new Date(date.getTime()-(totalprepTime*60000)-(60*60000));
					orDetObj.setPickupTime(date);
				}
			}

			orDetObj.setPickupTime(date);
			System.out.println(finalDate);
			System.out.println(totalprepTime);

			orDetObj.setStartPrepTime(finalDate);
			/*orDetObj.setPickupTime(date);*/
			orDetObj.setTotalPrepTime(totalprepTime);

			System.out.println(orDetObj.getEmail() + " " + orDetObj.getMenuDetails().iterator().next().getName()+" "+orDetObj.getMenuDetails().iterator().next().getId()+" "+orDetObj.getMenuDetails().iterator().next().getCategory()+ " "+orDetObj.getMenuDetails().iterator().next().getQuantity());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ParseException e){
			e.printStackTrace();
		}


		orderInCtrl = ordersvc.saveCompleteOrder(orDetObj);

		if(orderInCtrl == null){
			return new ResponseEntity<Object>(orderInCtrl,HttpStatus.OK);
		}else if(orderInCtrl.getEnd_time().getTime() > orDetObj.getPickupTime().getTime()){
			System.out.println("outside reqd condition");
			//if(orderInCtrl.getEnd_time().before(firstPickupTime)){
			System.out.println(orderInCtrl.getEnd_time().getTime());
			System.out.println(firstPickupTime.getTime());
			System.out.println(orderInCtrl.getEnd_time());
			System.out.println(firstPickupTime);
			if(orderInCtrl.getEnd_time().getTime()<firstPickupTime.getTime()){
				System.out.println("inside reqd condition");
				//orderInCtrl.setEnd_time(firstPickupTime);
				orderInCtrl.setDisplay_end_time(firstPickupTime);
			}
			else{
				orderInCtrl.setDisplay_end_time(orderInCtrl.getEnd_time());
			}
			return new ResponseEntity<Object>(orderInCtrl,HttpStatus.FORBIDDEN);
		}else{
			if(orderInCtrl.getEnd_time().before(firstPickupTime)){
				orderInCtrl.setEnd_time(firstPickupTime);
			}
			return new ResponseEntity<Object>(orderInCtrl,HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public ResponseEntity<Order> confirmOrder(@RequestBody Order order){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
		Order tempOrder = new Order();
		tempOrder.setOrderDetailsList(order.getOrderDetailsList());
		tempOrder.setChef_id(order.getChef_id());
		tempOrder.setEmail(order.getEmail());
		tempOrder.setEnd_time(order.getEnd_time());
		tempOrder.setOrder_placed_date(order.getOrder_placed_date());
		tempOrder.setOrder_date(order.getOrder_date());
		tempOrder.setPickup_time(order.getPickup_time());
		tempOrder.setStart_time(order.getStart_time());
		tempOrder.setStatus(order.getStatus());
		tempOrder.setTotal_price(order.getTotal_price());
		
		
		String orderdate = sdf.format(order.getPickup_time()).substring(0, 10);
		//String minStartPrepTime = orderdate + " 05:00:00";
		String mornPickupTime = orderdate + " 06:00:00";
		Date firstPickupTime = null;
		try {
			firstPickupTime = sdf.parse(mornPickupTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(order.getEnd_time().before(firstPickupTime)){
			tempOrder.setPickup_time(firstPickupTime);
		} 
		if(order.getPickup_time().compareTo(firstPickupTime) <= 0){
			tempOrder.setPickup_time(order.getEnd_time());
		}		
		
		//tempOrder.setPickup_time(order.getEnd_time());
		Order respOrder = ordersvc.confirmOrder(tempOrder);
		if(respOrder.getEnd_time().before(firstPickupTime)){
			respOrder.setEnd_time(firstPickupTime);
		}

		return new ResponseEntity<Order>(respOrder,HttpStatus.OK);

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

	@RequestMapping(value = "/showUserHistoryByStatus/{email}/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>>  showUserHistory(@PathVariable String email, @PathVariable String status) {

		List<Order> order = ordersvc.showUserHistory(email,status);

		if (order != null) {
			return new ResponseEntity<List<Order>>(order,HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Order>>(order,HttpStatus.NOT_FOUND);
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
