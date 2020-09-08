package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import com.java.entity.HibernateUtil;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.Category;
import com.java.pojo.Filter;

public class CategoryDAOImpl implements CategoryDAO {
	@Override
	public Status add(Category category) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(category);
			tx.commit();
		}catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.NONUNIQUEVALUE;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
	}
	@Override
	public Status Update(Category category) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(category);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Category> list(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Category> category = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Category.class);
			crit.add(Restrictions.eq("application_no",application_no));
			category = crit.list();

			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return category;

	}

	@Override
	public Status delete(Category category) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(category);
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
	public List<Category> getAll(String application_no, int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Category> cat = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Category.class);
			crit.add(Restrictions.eq("application_no",application_no));
		
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 }
			 }
			if(limit>0){
			crit.setFirstResult(start_index);
			crit.setMaxResults(limit);
			}
			cat = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cat;


	}

	@Override
	public int total_records(String application_no,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		int total_records= -1;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Category.class);
			crit.add(Restrictions.eq("application_no",application_no));
			
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
