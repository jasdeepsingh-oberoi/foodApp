package edu.sjsu.cmpe275.termprj.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.termprj.dao.MenuItemDao;
import edu.sjsu.cmpe275.termprj.model.MenuItem;

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
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem";
			Query query = session.createQuery(hql);
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
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.MenuItem as menuitem where menuitem.category = :category";
			Query query = session.createQuery(hql);
			query.setInteger("category",category);
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


	public int add(MenuItem p) {
		//        System.out.println("in add method");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(p);
			tx.commit();
			id = 1;
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return id;
	}
	/*
	public int update(MenuItem p) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.saveOrUpdate(p);
			tx.commit();
			id = 1;
		}catch(HibernateException e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return id;
	}*/

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
}
