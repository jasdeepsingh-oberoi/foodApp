package edu.sjsu.cmpe275.termprj.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.termprj.dao.OrderDao;
import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.model.Order;
import edu.sjsu.cmpe275.termprj.model.OrderDetails;
import edu.sjsu.cmpe275.termprj.model.UserOrderDetails;
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
	public List<MenuItem> getPrepTime(List<MenuItem> listMenuItem){
		List<MenuItem> dbData = orderDao.getPrepTime(listMenuItem);
		System.out.println("in order service impl");
		System.out.println(dbData.get(0).getName());
		return dbData;
	}
	public Order saveCompleteOrder(OrderDetails orderDetails){
		Order savedDbOrder = null;
		boolean flag = false;
		int conTinue = 0;
		long parsedEndTime = 0;
		long parsedFirstStartTime = 0;
		long parsedFirstStartPrepTime = 0;
		long parsedStartTime = 0;
		SimpleDateFormat sdfForDate = new SimpleDateFormat("dd-M-yyyy");
		Order order = new Order();
		String tempChefId = null;
		String finalchefId = null;
		Date finalStartTime = null;
		int i=0;
		long diff=0;
		int chef1 =0, chef2=0,chef3=0;
		try {
			List<Order> orders = orderDao.fetchChefAvailability(orderDetails);

			if(orders == null || orders.size() == 0){
				flag = true;
				finalchefId = "1";
				finalStartTime = orderDetails.getStartPrepTime();
				//order.setStart_time(orderDetails.getStartPrepTime());
			}else {
				for(int k=0; k<orders.size();k++){
					flag = false;
					tempChefId = orders.get(k).getChef_id();
					if(tempChefId.equals("1")){
						chef1 = 1;
					}else if(tempChefId.equals("2")){
						chef2 = 1;
					}else if(tempChefId.equals("3")){
						chef3 = 1;
					}

					if(flag == false){
						parsedFirstStartTime = orders.get(k).getStart_time().getTime();
						parsedFirstStartPrepTime = orderDetails.getStartPrepTime().getTime();
						/*System.out.println("parsedFirstStartTime");
						System.out.println(parsedFirstStartTime);
						System.out.println("parsedFirstStartPrepTime");
						System.out.println(parsedFirstStartPrepTime);*/
						diff = parsedFirstStartTime-parsedFirstStartPrepTime;
						if(diff >= ((orderDetails.getTotalPrepTime())*60000L)){
							System.out.println(parsedFirstStartTime - parsedFirstStartPrepTime);
							System.out.println((orderDetails.getTotalPrepTime())*60000L);
							flag = true;
							System.out.println("in first condition ");
							System.out.println(orderDetails.getStartPrepTime());
							System.out.println("chef  ");
							System.out.println(tempChefId);
							if(finalStartTime != null){
								if(parsedFirstStartPrepTime < finalStartTime.getTime()){
									finalStartTime = orderDetails.getStartPrepTime();
									finalchefId = tempChefId;
								}

							}else{
								finalStartTime = orderDetails.getStartPrepTime();
								finalchefId = tempChefId;
							}


							//hmap.put(tempChefId, orderDetails.getStartPrepTime());
							//order.setStart_time(orderDetails.getStartPrepTime());
							/*System.out.println("in nested second if");
							System.out.println(flag);*/
						}
					}
					if(flag == false){
						for(i=k;i<orders.size()-1;i++){
							if(orders.size()-1 != i && orders.get(i).getChef_id().equals(tempChefId) ){
								if(!orders.get(i).getChef_id().equals(orders.get(i+1).getChef_id()))
									break;
							}

							parsedEndTime = orders.get(i).getEnd_time().getTime();
							parsedStartTime = orders.get(i+1).getStart_time().getTime();
							diff = parsedStartTime - parsedEndTime;
							if(diff >=(orderDetails.getTotalPrepTime()*60000L)){
								System.out.println("in second condition");
								System.out.println(orders.get(i).getEnd_time());
								System.out.println("chef  ");
								System.out.println(tempChefId);
								flag = true;

								if(finalStartTime != null){
									if(orders.get(i).getEnd_time().getTime() < finalStartTime.getTime()){
										finalStartTime = orders.get(i).getEnd_time();
										finalchefId = tempChefId;
									}

								}else{
									finalStartTime = orders.get(i).getEnd_time();
									finalchefId = tempChefId;
								}


								//hmap.put(tempChefId, orders.get(i).getEnd_time());
								//order.setStart_time(orders.get(i).getEnd_time());
								break;
							}
							//System.out.println(flag);
						}
					}
					if(flag == false){
						//if(orders.get(i).getEnd_time().getTime() <= (orderDetails.getPickupTime().getTime() - orderDetails.getTotalPrepTime()*60000L)){
						/*System.out.println("sdf.parse(orders.get(orders.size()-1).getEnd_time()).getTime()");
							System.out.println(orders.get(2).getEnd_time().getTime());
							System.out.println("orderDetails.getStartPrepTime().getTime()");
							System.out.println(orderDetails.getStartPrepTime().getTime());
							System.out.println("in else second if");
							System.out.println(flag);*/
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
						String dbPickUpDate = sdfForDate.format(orderDetails.getPickupTime()) + " 21:00:00";

						Date lastPickUpDate =null;
						try {
							lastPickUpDate = sdf1.parse(dbPickUpDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(orders.get(i).getEnd_time().getTime() <= (lastPickUpDate.getTime() - orderDetails.getTotalPrepTime()*60000L)){	
							System.out.println("in third condition ");
							System.out.println(orders.get(i).getEnd_time());
							System.out.println("chef  ");
							System.out.println(tempChefId);
							if(finalStartTime != null){
								if(orders.get(i).getEnd_time().getTime() < finalStartTime.getTime()){
									finalStartTime = orders.get(i).getEnd_time();
									finalchefId = tempChefId;
								}

							}else{
								finalStartTime = orders.get(i).getEnd_time();
								finalchefId = tempChefId;
							}
							//hmap.put(tempChefId, orders.get(i-1).getEnd_time());
							//order.setStart_time(orders.get(orders.size()-1).getEnd_time());
							flag = true;
						}
					}

					while(orders.get(k).getChef_id().equals(tempChefId)){
						if(orders.size()-1 != k){
							if(orders.get(k).getChef_id().equals(orders.get(k+1).getChef_id()) ){
								k++;
								continue;
							}
						}
						break;
					}
					/*if(conTinue == 1){
						conTinue = 0;
						k--;
					}*/
				}

				if(chef1 == 0){
					finalStartTime = orderDetails.getStartPrepTime();
					finalchefId = "1";;
				}else if(chef2 == 0){
					finalStartTime = orderDetails.getStartPrepTime();
					finalchefId = "2";
				}else if(chef3 == 0){
					finalStartTime = orderDetails.getStartPrepTime();
					finalchefId = "3";
				}


			}


			if(finalStartTime != null && finalchefId != null){
				order.setEmail(orderDetails.getEmail());
				order.setTotal_price(orderDetails.getTotalPrice());
				//System.out.println("date part");
				Date orderDate = new Date();
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
				Date now = new Date();
				now = sdf2.parse(sdf2.format(now));
				orderDate = sdfForDate.parse(sdfForDate.format(orderDetails.getPickupTime()));
				//System.out.println(orderDate);
				order.setOrder_date(orderDate);
				order.setOrder_placed_date(null);
				order.setStatus("0");
				order.setPickup_time(orderDetails.getPickupTime());
				order.setChef_id(finalchefId);
				order.setOrder_placed_date(now);

				Date newEndTime = new Date(finalStartTime.getTime() + orderDetails.getTotalPrepTime()*60000L);

				//if(newEndTime.getTime() < new Date(sdfForDate.parse(")) )
				order.setEnd_time(newEndTime);
				order.setStart_time(finalStartTime);
				order.setChef_id(finalchefId);
				System.out.println("finalStartTime");
				System.out.println(finalStartTime);
				System.out.println("finalchefId");
				System.out.println(finalchefId);
				System.out.println("newEndTime");
				System.out.println(newEndTime);



				/*if(newEndTime.getTime() <= orderDetails.getPickupTime().getTime()){*/
				List<UserOrderDetails> menuDetForOrder = new ArrayList<UserOrderDetails>();

				for(int x=0;x<orderDetails.getMenuDetails().size();x++){
					System.out.println(orderDetails.getMenuDetails().size());
					System.out.println(orderDetails);
					System.out.println(orderDetails.getMenuDetails());

					UserOrderDetails userdet = new UserOrderDetails();
					userdet.setMenu_item_name(orderDetails.getMenuDetails().get(x).getName());
					userdet.setUnit_price(orderDetails.getMenuDetails().get(x).getUnit_price());
					userdet.setQuantity(orderDetails.getMenuDetails().get(x).getQuantity());
					userdet.setEmail(orderDetails.getEmail());
					menuDetForOrder.add(userdet);

					System.out.println(userdet);
				}

				for(UserOrderDetails userOrderDetails : menuDetForOrder){
					order.addDetails(userOrderDetails);
				}
				if(newEndTime.getTime() <= orderDetails.getPickupTime().getTime()){
					//order.setOrderDetailsList(menuDetForOrder);
					savedDbOrder = orderDao.saveFinalOrder(order);
					System.out.println(savedDbOrder.getTotal_price());
				}else{
					savedDbOrder = order;
					System.out.println("line 296:");
					System.out.println(savedDbOrder.getEnd_time());
					//savedDbOrder.setEnd_time(newEndTime);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return savedDbOrder;
	}
	
	public Order confirmOrder(Order order){
		return orderDao.saveFinalOrder(order);
	}

	public List<Order> showUserHistory(String email,String status) {
		return orderDao.showUserHistory(email,status);
	}

}
