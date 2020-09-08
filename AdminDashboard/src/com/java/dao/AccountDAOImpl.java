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

import com.java.entity.HibernateUtil;
import com.java.entity.Nature;
import com.java.entity.Period;
import com.java.entity.Account;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.Filter;

public class AccountDAOImpl implements AccountDAO {
	@Override
	public Status add(Account account) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(account);
			tx.commit();
		}catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.NONUNIQUEVALUE;
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		}finally {
			s.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public Status Update(Account account) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(account);
			tx.commit();
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		}finally {
			s.close();
		}
		return Status.SUCCESS;
	}


	@Override
	public Status delete(Account account) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(account);
			tx.commit();
		}  catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			return Status.CANNOTDELETE;
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		}finally {
			session.close();
		}
		return Status.SUCCESS;
	}
	
	@Override
	public List<Account> getAll(String application_no, int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Account> accounts = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Account.class);
			crit.add(Restrictions.eq("application_no",application_no));
			crit.addOrder(Order.asc("account_code"));
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 else if(f.getOperator().equals("in")){
						 if(f.getProperty().equals("nature")){
							 crit.add(Restrictions.in("nature.nature_code", f.getValue()));
						}
						else if(f.getProperty().equals("label")){
							 crit.add(Restrictions.in("label.label_code", f.getValue()));
						}
						else if(f.getProperty().equals("type")){
							 crit.add(Restrictions.in("type.type_code", f.getValue()));
						}
		     			 }
					 }
				 }
		
			if(limit >0){
			crit.setFirstResult(start_index);
			crit.setMaxResults(limit);
			}
			accounts = crit.list();			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return accounts;


	}

	@Override
	public int total_records(String application_no,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Account> accounts = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Account.class);
			crit.add(Restrictions.eq("application_no",application_no));
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 else if(f.getOperator().equals("in")){
						 if(f.getProperty().equals("nature")){
							 crit.add(Restrictions.in("nature.nature_code", f.getValue()));
						}
						 else if(f.getProperty().equals("label")){
							 crit.add(Restrictions.in("label.label_code", f.getValue()));
						}
						else if(f.getProperty().equals("type")){
							 crit.add(Restrictions.in("type.type_code", f.getValue()));
						}
		     			 }
					 }
				 }
			accounts = crit.list();
			} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return accounts.size();


	}
}
