package edu.sjsu.cmpe275.termprj.dao.impl;

import edu.sjsu.cmpe275.termprj.dao.OrderDao;
import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.model.Order;
import edu.sjsu.cmpe275.termprj.model.OrderDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;


	public List<Order> getAll() {
		Session session = sessionFactory.openSession();
		List<Order> result = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order";
			Query query = session.createQuery(hql);
			result = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}


	public Order getById(String id) {
		Session session = sessionFactory.openSession();
		Order order = null;
		List<Order> orders;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order as order where order.id = :id";
			Query query = session.createQuery(hql);
			query.setString("id", id);
			query.setMaxResults(1);
			orders = query.list();
			if(orders.size()!=0){
				order = orders.get(0);
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return order;
	}

	public List<Order> getByStartTime(Date starttime) {
		Session session = sessionFactory.openSession();
		List<Order> orders = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order as order where order.start_time = :start_time";
			Query query = session.createQuery(hql);
			query.setParameter("start_time", starttime);
			orders = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return orders;
	}

	public void add(Order p) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(p);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void update(Order p) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(p);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void delete(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order as order where order.id = :id";
			Query query = session.createQuery(hql);
			query.setString("id", id);
			query.setMaxResults(0);
			Order order = (Order) query.list().get(0);
			if(order != null){
				session.delete(id);
			}
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}
	}

	public List<MenuItem> getPrepTime(List<MenuItem> listMenuItem){
		Session session = sessionFactory.openSession();
		List<MenuItem> dbMenuList = new ArrayList<MenuItem>();
		try{
			List listIds = new ArrayList();
			if(listMenuItem.size() != 0){
				for(int i=0;i<listMenuItem.size();i++){
					String check = listMenuItem.get(i).getId().substring(1, listMenuItem.get(i).getId().length()-1);
					listIds.add(check);
				}
			}
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.id IN (:ids) and menuitem.is_deleted = 0";
			Query query = session.createQuery(hql);
			query.setParameterList("ids", listIds);
			dbMenuList = query.list();	
			/*System.out.println("in orderaoimpl b4");
			System.out.println(dbMenuList.get(0).getName());
			System.out.println("in orderaoimpl after");*/
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return dbMenuList;
	}

	public List<Order> fetchChefAvailability(OrderDetails orderDetails){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Order> orders = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order as order where order.order_date = :date and order.start_time BETWEEN :date1 and :date2 "
					+ "OR order.end_time BETWEEN :date1 and :date2 order by order.chef_id,order.start_time asc";
			Query query = session.createQuery(hql);
			System.out.println("in order dao : ");
			System.out.println(orderDetails.getStartPrepTime().toString());
			System.out.println("before setting value of dbpickupdate");
			System.out.println(orderDetails.getPickupTime());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
			String dbPickUpDate = sdf.format(orderDetails.getPickupTime()) + " 21:00:00";
			
			Date dbDate =null;
			try {
				dbDate = sdf1.parse(dbPickUpDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("after setting value of dbpickupdate");
			System.out.println(orderDetails.getPickupTime());
			System.out.println(dbPickUpDate);
			
			query.setDate("date", orderDetails.getStartPrepTime());		
			query.setParameter("date1", orderDetails.getStartPrepTime());
			//query.setParameter("date2", orderDetails.getPickupTime());
			
			query.setParameter("date2", dbDate);
			orders = query.list();
			for(int i=0;i<orders.size();i++){
				System.out.println(i);
				System.out.println(orders.get(i).getEmail());
				System.out.println(orders.get(i).getStart_time());
				System.out.println(orders.get(i).getEnd_time());
				System.out.println(orders.get(i).getOrder_id());
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		return orders;
	}
	
	public Order saveFinalOrder(Order order){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(order);
			tx.commit();
			System.out.println("i am here ");
			System.out.println(order.getOrder_id());
			System.out.println(order.getChef_id());
			System.out.println(order.getStart_time());
		}catch(HibernateException e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
		return order;
	}

	public List<Order> showUserHistory(String email,String status){
		Session session = sessionFactory.openSession();
		List<Order> result = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order as order where order.email = :email and order.status = :status";
			Query query = session.createQuery(hql);
			query.setString("email", email);
			query.setString("status", status);
			result = query.list();
			System.out.println("in dao user history");
			System.out.println(result);
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
}