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
import com.java.entity.Application;
import com.java.entity.CustDim10;
import com.java.pojo.Filter;

public class ApplicationDAOImpl implements ApplicationDAO {
	@Override
	public Status add(Application application) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(application);
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
	public Status Update(Application application) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(application);
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
	public List<Application> list() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Application> applications = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Application.class);
			
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
	public List<Application> list(String user_name) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Application> applications = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Application.class);
			crit.add(Restrictions.eq("app_owner",user_name));
			
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
	public Status delete(Application application) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(application);
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
	public List<Application> getAll(String user_name,int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Application> applications = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Application.class);
			crit.add(Restrictions.eq("app_owner",user_name));
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					if (f.getOperator().equals("like")) {
						crit.add(Restrictions.ilike(f.getProperty(), f.getValue().get(0), MatchMode.ANYWHERE));
					}
					else if (f.getOperator().equals("in")) {
						if (f.getProperty().equals("model")) {
							crit.add(Restrictions.in("model.model_code", f.getValue()));
						} else if (f.getProperty().equals("applicationstatus")) {
							crit.add(Restrictions.in("applicationstatus.status_code", f.getValue()));
						}
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
		List<Application> applications = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Application.class);
			crit.add(Restrictions.eq("app_owner",user_name));
			
			//Adding critera according to filters provided
			 if(filter != null){
				 for(int i=0; i<filter.size(); i++){
					 Filter f = filter.get(i);
					 if(f.getOperator().equals("like")){
						 crit.add(Restrictions.ilike(f.getProperty(),f.getValue().get(0),MatchMode.ANYWHERE));
						 }
					 else if(f.getOperator().equals("in")){
							if(f.getProperty().equals("model")){
								 crit.add(Restrictions.in("model.model_code", f.getValue()));
							}
							else if(f.getProperty().equals("applicationstatus")){
								 crit.add(Restrictions.in("applicationstatus.status_code", f.getValue()));
							}
				     			 }
					 }
				 }
			
			applications = crit.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return applications.size();
	}

}
