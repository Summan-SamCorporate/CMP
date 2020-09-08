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
import com.java.entity.Model;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.Account;
import com.java.entity.Transformation;
import com.java.pojo.Filter;

public class TransformationDAOImpl implements TransformationDAO {
	@Override
	public Status add(Transformation transformation) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(transformation);
			tx.commit();
		} catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.NONUNIQUEVALUE;
		}catch (HibernateException e) {
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
	public Status update(Transformation transformation) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(transformation);
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
	public List<Transformation> list() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Transformation> transformation = null;
		try {
			tx = session.beginTransaction();

			transformation = session.createQuery("FROM transformation").list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return transformation;

	}

	@Override
	public List<Transformation> list(String user_name) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Transformation> applications = null;
		try {
			tx = session.beginTransaction();

			 applications = session.createQuery("FROM Transformation where created_user= '" + user_name + "'").list();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
	
		} finally {
			session.close();
		}
		return applications;

	}

	@Override
	public Status delete(Transformation transformation) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(transformation);
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
	public List<Transformation> getAll(String user_name,int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Transformation> applications = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Transformation.class);
			crit.add(Restrictions.eq("created_user",user_name));
			
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
			applications = crit.list();
			} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return applications;


	}

	@Override
	public int total_records(String user_name,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Transformation> transformation = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Transformation.class);
			crit.add(Restrictions.eq("created_user",user_name));
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 }
				 }
			
			transformation = crit.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return transformation.size();
	}

}
