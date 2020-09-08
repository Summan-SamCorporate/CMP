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

import com.java.entity.ApplicationStatus;
import com.java.pojo.Filter;
import com.java.entity.HibernateUtil;
import com.java.entity.Status;
import com.java.entity.Dictionary;

public class DictionaryDAOImpl implements DictionaryDAO{

	@Override
	public List<Dictionary> list() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Dictionary> dictionaries = null;
		try {
			tx = session.beginTransaction();
			
			dictionaries = session.createQuery("FROM Dictionary").list();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dictionaries;

	}

	@Override
	public Status add(Dictionary dictionary) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.save(dictionary);
			tx.commit();
		}catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.NONUNIQUEVALUE;
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			session.close();
		}
	return Status.SUCCESS;
	}
	@Override
	public Status update(Dictionary dictionary) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.update(dictionary);
			tx.commit();
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			session.close();
		}
	return Status.SUCCESS;
	}

	@Override
	public Status delete(Dictionary dictionary) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.delete(dictionary);
			tx.commit();
		} catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			return Status.CANNOTDELETE;
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			session.close();
		}
	return Status.SUCCESS;
	}
	@Override
	public List<Dictionary> getAll(int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Dictionary> dictionaries = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Dictionary.class);
			crit.addOrder(Order.asc("dictionary_code"));
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 }
				 }
			if(limit >0){
			crit.setFirstResult(start_index);
			crit.setMaxResults(limit);
			}
			dictionaries = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dictionaries;


	}

	@Override
	public int total_records(List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		int total_records= -1;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Dictionary.class);
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
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
