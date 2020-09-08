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
import com.java.entity.Scenario_Version;

public class VersionDAOImpl implements VersionDAO {
	@Override
	public Status add(Scenario_Version version) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(version);
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
			s.close();
		}
		return Status.SUCCESS;
	}
	@Override
	public Status update(Scenario_Version version) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(version);
			tx.commit();
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public List<Scenario_Version> list() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario_Version> version = null;
		try {
			tx = session.beginTransaction();

			version = session.createQuery("FROM Version").list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return version;

	}

	@Override
	public Status delete(Scenario_Version version) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(version);
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
	public List<Scenario_Version> getAll(int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario_Version> version = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Scenario_Version.class);
			crit.addOrder(Order.asc("version_code"));
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
			version = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return version;


	}

	@Override
	public int total_records(List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		int total_records= -1;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Scenario_Version.class);
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
