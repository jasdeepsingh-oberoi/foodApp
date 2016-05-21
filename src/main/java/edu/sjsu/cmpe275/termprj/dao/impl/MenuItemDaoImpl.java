package edu.sjsu.cmpe275.termprj.dao.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.termprj.dao.MenuItemDao;
import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.model.MenuItemCount;
import edu.sjsu.cmpe275.termprj.model.Order;

@Repository
@SuppressWarnings("unchecked")
public class MenuItemDaoImpl implements MenuItemDao {

	@Autowired
	private SessionFactory sessionFactory;
	private int id = 0;

	/*@PersistenceContext
	private EntityManager em;*/

	public List<MenuItem> getAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<MenuItem> menuItem = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.is_deleted = :is_deleted order by menuitem.category asc";
			Query query = session.createQuery(hql);
			query.setInteger("is_deleted", 0);
			menuItem = (List<MenuItem>) query.list();
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return menuItem;
	}


	public List<MenuItem> getMenuByCategory(Integer category) {
		Session session = sessionFactory.openSession();
		List<MenuItem> results = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.category = :category and menuitem.is_deleted = :is_deleted";
			Query query = session.createQuery(hql);
			query.setInteger("category",category);
			query.setInteger("is_deleted", 0);
			results = (List<MenuItem>) query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return results;
	}


	public MenuItem getById(Integer mId) {
		Session session = sessionFactory.openSession();
		List<MenuItem> p1 = null;
		System.out.println(mId);
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.id = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id",mId);
			//query.setMaxResults(1);
			//p1 = (MenuItem)query.uniqueResult();
			p1 = query.list();
			System.out.println(p1.size());
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return p1.get(0);
	}

	public List<MenuItem> getByName(String name) {
		Session session = sessionFactory.openSession();
		List<MenuItem> results = null;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.name = :name";
			Query query = session.createQuery(hql);
			query.setString("name",name);
			results = (List<MenuItem>) query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return results;
	}


	public MenuItem add(MenuItem p) {
		//        System.out.println("in add method");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<MenuItem> results = null;
		MenuItem menuitem = null;
		try{
			session.save(p);
			tx.commit();
			String hql1 = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.name = :name and menuitem.category = :category";
			Query query1 = session.createQuery(hql1);
			query1.setString("name", p.getName());
			query1.setInteger("category", p.getCategory());
			query1.setMaxResults(1);
			results = query1.list();
			if(results.size()!=0){
				System.out.println("in add menu dao: "+ results.get(0).getName());
				menuitem = results.get(0);
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return menuitem;
	}

	public int update(MenuItem p) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(p);
			tx.commit();
			id = 1;
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return id;
	}

	public int delete(Integer mId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.delete(session.load(MenuItem.class, mId));
			tx.commit();
			id = 1;
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return id;
	}

	public MenuItem addImage(MenuItem p){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<MenuItem>  menuitems = null;
		MenuItem dbItem = null;
		int modification = 0;
		try{
			String hql="FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem order by menuitem.id desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			menuitems = query.list();
			dbItem = menuitems.get(0);
			dbItem.setImage_path(p.getImage_path());
			String hql1 = "UPDATE edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem set menuitem.image_path = :image_path WHERE menuitem.id = :id";
			Query query1 = session.createQuery(hql1);
			query1.setString("id", dbItem.getId());
			query1.setString("image_path", p.getImage_path());
			modification = query1.executeUpdate();
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		if (modification == 1)
			return dbItem;
		else
			return null;

	}

	public List<MenuItemCount> getPopRepByCategory(Integer category,String startDatestring,String endDatestring) {
		Session session = sessionFactory.openSession();
		List<MenuItemCount> results = new ArrayList<MenuItemCount>();
		Date stDt = null,enDt = null;
			try {
				 stDt = new SimpleDateFormat("dd-M-yyyy").parse(startDatestring);
				 enDt = new SimpleDateFormat("dd-M-yyyy HH:mm:ss").parse(endDatestring);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	
		try{
			String sql = "select count(*) as cnt, menu_item_name from user_orders_details where order_id in "
					+ "(select order_id from order_details where order_placed_date between :startdate and "
					+ ":enddate and status = 0) and menu_item_name in(select name from menu_items where category = :category) group by menu_item_name order by cnt desc";
			SQLQuery query = session.createSQLQuery(sql);
			query.setDate("startdate", stDt);
			query.setParameter("enddate", enDt);
			query.setParameter("category", category);
			List results1 = query.list();
			Iterator iterator = results1.iterator();
			while (iterator.hasNext()) {
				Object[] row = (Object[])iterator.next();
				MenuItemCount menuItemCntObj = new MenuItemCount();
				menuItemCntObj.setItem_count(((BigInteger)row[0]).intValue());
				menuItemCntObj.setItem_name(row[1].toString());
				results.add(menuItemCntObj);
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return results;
	}
	
	public List<Order> getStatRep(String startDatestring,String endDatestring) {
		Session session = sessionFactory.openSession();
		List<Order> results = new ArrayList<Order>();
		Date stDt = null,enDt = null;
			try {
				 stDt = new SimpleDateFormat("dd-M-yyyy").parse(startDatestring);
				 enDt = new SimpleDateFormat("dd-M-yyyy HH:mm:ss").parse(endDatestring);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.Order as order where order.order_placed_date BETWEEN :startdate and :enddate order by order.order_id desc";
			Query query = session.createQuery(hql);
			query.setDate("startdate", stDt);
			query.setParameter("enddate", enDt);
			results = query.list();
			
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return results;
	}
}

