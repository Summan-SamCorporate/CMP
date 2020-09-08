package com.java.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import com.java.entity.HibernateUtil;
import com.java.entity.Status;
import com.java.entity.AppDimensionsConfig;
import com.java.entity.*;
public class AppDimensionsConfigDAOImpl implements AppDimensionsConfigDAO {

	@Override
	public List<AppDimensionsConfig> listAll(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AppDimensionsConfig> dimensions = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AppDimensionsConfig.class);
			crit.add(Restrictions.eq("application_no", application_no));
			
			dimensions = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dimensions;
	}

	@Override
	public List<AppDimensionsConfig> listVisible(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AppDimensionsConfig> dimensions = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AppDimensionsConfig.class);
			crit.add(Restrictions.eq("visibility", true));
			crit.add(Restrictions.eq("application_no", application_no));
			
			dimensions = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dimensions;
	}

	@Override
	public List<AppDimensionsConfig> listCustomDimensions(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AppDimensionsConfig> dimensions = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AppDimensionsConfig.class);
			crit.add(Restrictions.eq("visibility", true));
			crit.add(Restrictions.eq("application_no", application_no));
			crit.add(Restrictions.ne("column_status", "B"));
			
			dimensions = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dimensions;
	}
	@Override
	public Status update(AppDimensionsConfig dimensions) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(dimensions);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public Status add(AppDimensionsConfig dimensions) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(dimensions);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
}
	
	@Override
	public Status addAll(List<AppDimensionsConfig> dimensions) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			for(int i=0;i<dimensions.size();i++){
			s.save(dimensions.get(i));
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;
}

	@Override
	public Status checkDefault(String dimension_class,String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(getDimension(dimension_class));
			crit.add(Restrictions.eq("default_flag", "D"));
			crit.add(Restrictions.eq("application_no", application_no));
			int size =crit.list().size();
			if(size >0){
				return Status.SUCCESS; //A default value exists
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null; //Create new deafult value
	}

	@Override
	public Status createDefault(String table_name, String default_value,String user_created,String application_no) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			
			if(table_name.equals("Entity"))
			{
				Entities default_object = new Entities();
				default_object.setEntity_code(default_value);
				default_object.setEntity_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
			}
			else if (table_name.equals("Accounts")){
				Account default_object = new Account();
				default_object.setAccount_code(default_value);
				default_object.setAccount_description(default_value);
				//default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
			}
			else if (table_name.equals("CustomDimension1")){
				CustDim1 default_object = new CustDim1();
				default_object.setCustdim1_code(default_value);
				default_object.setCustdim1_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension2")){
				CustDim2 default_object = new CustDim2();
				default_object.setCustdim2_code(default_value);
				default_object.setCustdim2_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension3")){
				CustDim3 default_object = new CustDim3();
				default_object.setCustdim3_code(default_value);
				default_object.setCustdim3_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension4")){
				CustDim4 default_object = new CustDim4();
				default_object.setCustdim4_code(default_value);
				default_object.setCustdim4_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension5")){
				CustDim5 default_object = new CustDim5();
				default_object.setCustdim5_code(default_value);
				default_object.setCustdim5_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension6")){
				CustDim6 default_object = new CustDim6();
				default_object.setCustdim6_code(default_value);
				default_object.setCustdim6_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension7")){
				CustDim7 default_object = new CustDim7();
				default_object.setCustdim7_code(default_value);
				default_object.setCustdim7_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension8")){
				CustDim8 default_object = new CustDim8();
				default_object.setCustdim8_code(default_value);
				default_object.setCustdim8_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension9")){
				CustDim9 default_object = new CustDim9();
				default_object.setCustdim9_code(default_value);
				default_object.setCustdim9_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension10")){
				CustDim10 default_object = new CustDim10();
				default_object.setCustdim10_code(default_value);
				default_object.setCustdim10_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension11")){
				CustDim11 default_object = new CustDim11();
				default_object.setCustdim11_code(default_value);
				default_object.setCustdim11_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}
			else if (table_name.equals("CustomDimension12")){
				CustDim12 default_object = new CustDim12();
				default_object.setCustdim12_code(default_value);
				default_object.setCustdim12_description(default_value);
				default_object.setColumn_status("");
				default_object.setCreated_user(user_created);
				default_object.setCreated_date(new Date());
				default_object.setDefault_flag("D");
				default_object.setApplication_no(application_no);
				s.save(default_object);
				}

		
		tx.commit();
	} catch (HibernateException e) {
		if (tx != null)
			tx.rollback();
		return Status.HibernateException;
	} finally {
		s.close();
	}
		
		
		return Status.SUCCESS;
	}

	private Class getDimension(String table_name){

		
		if(table_name.equals("Entity"))
		{
			return Entities.class;
		}
		else if (table_name.equals("Accounts")){
			return Account.class;
		}
		else if (table_name.equals("CustomDimension1")){
			return CustDim1.class;
		}
		else if (table_name.equals("CustomDimension2")){
			return CustDim2.class;
		}
		else if (table_name.equals("CustomDimension3")){
			return CustDim3.class;
		}
		else if (table_name.equals("CustomDimension4")){
			return CustDim4.class;
		}
		else if (table_name.equals("CustomDimension5")){
			return CustDim5.class;
		}
		else if (table_name.equals("CustomDimension6")){
			return CustDim6.class;
		}
		else if (table_name.equals("CustomDimension7")){
			return CustDim7.class;
		}
		else if (table_name.equals("CustomDimension8")){
			return CustDim8.class;
		}
		else if (table_name.equals("CustomDimension9")){
			return CustDim9.class;
		}
		else if (table_name.equals("CustomDimension10")){
			return CustDim10.class;
		}
		else if (table_name.equals("CustomDimension11")){
			return CustDim11.class;
		}
		else if (table_name.equals("CustomDimension12")){
			return CustDim12.class;
		}
		
	return null;
	}
}
