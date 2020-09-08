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
import com.java.entity.Extraction;
import com.java.pojo.Filter;

public class ExtractionDAOImpl implements ExtractionDAO {
	@Override
	public Status add(Extraction extraction) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(extraction);
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
	public Status update(Extraction extraction) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(extraction);
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
	public List<Extraction> list() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Extraction> extraction = null;
		try {
			tx = session.beginTransaction();

			extraction = session.createQuery("FROM extraction").list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return extraction;

	}

	@Override
	public List<Extraction> list(String user_name) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Extraction> applications = null;
		try {
			tx = session.beginTransaction();

			 applications = session.createQuery("FROM Extraction where created_user= '" + user_name + "'").list();
			
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
	public Status delete(Extraction extraction) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(extraction);
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
	public List<Extraction> getAll(String user_name,int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Extraction> applications = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Extraction.class);
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
	//-------------------------------------------
			//filter with master objects
		    //TODO add criteria query instead
			
	/*		for(int i=0; i<filters_list.size(); i++){
				Filter f = filters_list.get(i);
				
				if(f.getProperty().equals("applicationstatus")){
					for(int j=0; j<applications.size();j++){					
						Application acc = applications.get(j);
						boolean found = false;
						for(int v=0;(v<f.getValues().length)&&(found==false);v++){
							if(acc.getApplicationstatus().getDescription().equals(f.getValues()[v])){
								found = true;
							}
						}
						if(!found){
							applications.remove(acc);
							j--;
						
						}
					}
				}
				
				
			}*/
//-----------------------------------------------			


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
		List<Extraction> extraction = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Extraction.class);
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
			
			extraction = crit.list();


		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return extraction.size();
	}

}
