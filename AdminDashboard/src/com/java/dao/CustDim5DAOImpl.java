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
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.entity.CustDim1;
import com.java.entity.CustDim5;
import com.java.pojo.Filter;

public class CustDim5DAOImpl implements CustDim5DAO {
	@Override
	public Status add(CustDim5 custdim) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(custdim);
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
	public Status update(CustDim5 custdim) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(custdim);
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
	public List<CustDim5> list(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<CustDim5> custdim = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(CustDim5.class);
			crit.add(Restrictions.eq("application_no",application_no));
			custdim = crit.list();


		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return custdim;

	}

	@Override
	public Status delete(CustDim5 custdim) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(custdim);
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
	public List<CustDim5> getAll(String application_no, int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<CustDim5> dim = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(CustDim5.class);
			crit.add(Restrictions.eq("application_no",application_no));
			crit.addOrder(Order.asc("custdim5_code"));
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
			
			dim = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dim;


	}

	@Override
	public int total_records(String application_no,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		int total_records= -1;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(CustDim5.class);
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
