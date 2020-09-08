package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Model;
import com.java.entity.Period;
import com.java.entity.Status;
import com.java.entity.Users;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public Users searchUser(String user) {

		long startTime = System.currentTimeMillis();
		Session s = HibernateUtil.openSession();
		Transaction tx = null;
		Users loggedUser = new Users();
		try {
			tx = s.beginTransaction();
			// Changed by Bijoy
			List<Users> users = s.createQuery("FROM Users where USERNAME= '" + user + "'").list();// and PASSWORD ='"
//					+ user.getPassword() + "'").list();

			int size = users.size();
			if (size == 1) {
				loggedUser = users.get(0); // found a user
			} else {
				loggedUser.setUsername("-1"); // if no user is found
			}

			tx.commit();
		}

		catch (JDBCConnectionException e) {
			if (tx != null)
				tx.rollback();
			log.fatal(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.fatal(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}

		return loggedUser;

	}

	@Override
	public Status add(Users user) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		}
		catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			log.error(e);
			e.printStackTrace();
			return Status.NONUNIQUEVALUE;
		}
		catch (Exception e) {
			if (tx != null)
				tx.rollback();
			log.error(e);
			//e.printStackTrace();
			return Status.HibernateException;
		}
		finally {
			s.close();
		}
		return Status.SUCCESS;
	}
	@Override
	public Users update(Users user) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(user);
			tx.commit();
		} catch (JDBCConnectionException e) {
			if (tx != null)
				tx.rollback();
			log.error(e);
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}
		return user;
	}

	@Override
	public Status changePassword(Users user) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(user);
			tx.commit();
		} catch (JDBCConnectionException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Users> list() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Users> users = null;
		try {
			tx = session.beginTransaction();

			users = session.createQuery("FROM Users").list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;

	}

	//No reference found
	@Override
	public Status addUpdateAll(List<Users> users) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.saveOrUpdate(users);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public Status delete(Users user) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Users> getAll(int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Users> users = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Users.class);
			crit.addOrder(Order.asc("username"));
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 else if(f.getOperator().equals("in")){
						 if(f.getProperty().equals("role")){
							 crit.add(Restrictions.in("role.role_code", f.getValue()));
						}
						else if(f.getProperty().equals("status")){
							 crit.add(Restrictions.in("status.status_code", f.getValue()));
						}
		     			 }
					 }
				 }
			 
			 if(limit >0){
				 crit.setFirstResult(start_index);
				 crit.setMaxResults(limit);
				 users = crit.list();
				 }
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;


	}

	@Override
	public int total_records(List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		int total_records= -1;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Users.class);
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 else if(f.getOperator().equals("in")){
						if(f.getProperty().equals("role")){
							 crit.add(Restrictions.in("role.role_code", f.getValue()));
						}
						else if(f.getProperty().equals("status")){
							 crit.add(Restrictions.in("status.status_code", f.getValue()));
						}
			     			 }
					 }
				 }
			 
			 crit.setProjection(Projections.rowCount());
			 total_records = (Integer)crit.uniqueResult();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return total_records;

	}
}
