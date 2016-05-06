package edu.sjsu.cmpe275.termprj.dao.impl;

import edu.sjsu.cmpe275.termprj.dao.OrderDao;
import edu.sjsu.cmpe275.termprj.model.Order;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

}