package edu.sjsu.cmpe275.termprj.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.termprj.dao.UserDao;
import edu.sjsu.cmpe275.termprj.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	private Long id;
	
	public User insertUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			id = (Long) session.save(user);
			tx.commit();
			user.setId(id);
		}catch(HibernateException e){
			user.setId(0);
			tx.rollback();
		}finally{
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public User getVerifiedUser(String email){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = null;
		List<User> users;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.User as user WHERE user.email = :email";
			Query query = session.createQuery(hql);
			query.setString("email", email);
			query.setMaxResults(1);
			users = query.list();
			if(users.size()!=0){
				user = users.get(0);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}
	public int getUser(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	public User setUserVerified(String email,String verification_code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = null;
		List<User> users;
		int modification = 0;
		try{
			String hql = "UPDATE edu.sjsu.cmpe275.termprj.model.User as user set user.isVerified = :is_verified WHERE user.email = :email and user.verification_code = :verification_code";
			Query query = session.createQuery(hql);
			query.setString("is_verified", "1");
			query.setString("email", email);
			query.setString("verification_code", verification_code);
			modification = query.executeUpdate();
			tx.commit();
			String hql1 = "FROM edu.sjsu.cmpe275.termprj.model.User as user WHERE user.email = :email and user.isVerified = :is_verified";
			Query query1 = session.createQuery(hql1);
			query1.setString("email", email);
			query1.setString("is_verified", "1");
			query1.setMaxResults(1);
			users = query1.list();
			if(users.size()!=0){
				System.out.println("in authenticateUser: "+ users.get(0).getEmail());
				user = users.get(0);
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if (modification == 1)
			return user;
		else
			return null;
	}

	public String authenticateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<User> users;
		try{
			String hql = "FROM edu.sjsu.cmpe275.termprj.model.User as user WHERE user.email = :email";
			Query query = session.createQuery(hql);
			query.setString("email", user.getEmail());
			query.setMaxResults(1);
			users = query.list();
			if(users.size()!=0){
				System.out.println("in authenticateUser: "+ users.get(0));
				user = users.get(0);
			}
			tx.commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user.getPassword();
	}
	
}
