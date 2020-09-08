package com.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.Transformers;

import com.java.entity.*;

public class QueryAnalyzerImplDAO implements QueryAnalyzerDAO{

	@Override
	public List<Object[]> execute(String query_string) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Object[]> rows = null;
		try {
			tx = session.beginTransaction();		
			SQLQuery query = session.createSQLQuery(query_string);
			rows = query.list();
			
			tx.commit();
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return null;
		} finally {
			session.close();
		}
		return rows;

	}

	@Override
	public List<Object> execute(String table_name, String table_column_name, String column_name,String operator, String text) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Object> results = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = createCriteria(table_name,session);
			if(operator.equals("=")){
				crit.add(Restrictions.eq(column_name,text));
			}
			else if(operator.equals(">")){
				crit.add(Restrictions.gt(column_name,text));
			}
			else if(operator.equals("<")){
				crit.add(Restrictions.lt(column_name,text));
			}
			else if(operator.equals(">=")){
				crit.add(Restrictions.ge(column_name,text));
			}
			else if(operator.equals("<-")){
				crit.add(Restrictions.le(column_name,text));
			}
			else if(operator.equals("!=")){
				crit.add(Restrictions.ne(column_name,text));
			}
			else if(operator.equals("like")){
				crit.add(Restrictions.like(column_name,text));
			}
				
				
				
			results = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return results;
	}
	
	@Override
	public List<Object> execute(String table_name, String table_column_name) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Object> results = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = createCriteria(table_name,session);
			results = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return results;
	}
	
	@Override
	public List<Object> execute(String table_name, String table_column_name,String advance_query) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Object> results = null;
		try {
			tx = session.beginTransaction();
			/*Criteria crit = createCriteria(table_name,session);
			results = crit.list();
*/
			SQLQuery query = (SQLQuery) session.createSQLQuery(advance_query).setResultTransformer(Transformers.aliasToBean(getEntity(table_name)));
			results= query.list();
			
			
		}catch(SQLGrammarException g){
			tx.rollback();
			//g.printStackTrace();
			return null;
		} 
		catch (Exception e) {
			if (tx != null)
				tx.rollback();
		//	e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return results;
	}

	private Criteria createCriteria(String table_name, Session session){

		Criteria criteria = null;
		if(table_name.equals("ACCOUNT"))
		{
			criteria=session.createCriteria(Account.class);
		}
		else if(table_name.equals("APPLICATION"))
		{
			criteria=session.createCriteria(Application.class);
		}
		else if(table_name.equals("Application_Status"))
		{
			criteria=session.createCriteria(ApplicationStatus.class);
		}
		else if(table_name.equals("CATEGORY"))
		{
			criteria=session.createCriteria(Category.class);
		}
		else if(table_name.equals("Category_Type"))
		{
			criteria=session.createCriteria(CategoryType.class);
		}
		else if(table_name.equals("CURRENCY"))
		{
			criteria=session.createCriteria(Currency.class);
		}
		else if(table_name.equals("CustDim1"))
		{
			criteria=session.createCriteria(CustDim1.class);
		}
		else if(table_name.equals("CustDim10"))
		{
			criteria=session.createCriteria(CustDim10.class);
		}
		else if(table_name.equals("CustDim11"))
		{
			criteria=session.createCriteria(CustDim11.class);
		}
		else if(table_name.equals("CustDim12"))
		{
			criteria=session.createCriteria(CustDim12.class);
		}
		else if(table_name.equals("CustDim2"))
		{
			criteria=session.createCriteria(CustDim2.class);
		}
		else if(table_name.equals("CustDim3"))
		{
			criteria=session.createCriteria(CustDim3.class);
		}
		else if(table_name.equals("CustDim4"))
		{
			criteria=session.createCriteria(CustDim4.class);
		}
		else if(table_name.equals("CustDim5"))
		{
			criteria=session.createCriteria(CustDim5.class);
		}
		else if(table_name.equals("CustDim6"))
		{
			criteria=session.createCriteria(CustDim6.class);
		}
		else if(table_name.equals("CustDim7"))
		{
			criteria=session.createCriteria(CustDim7.class);
		}
		else if(table_name.equals("CustDim8"))
		{
			criteria=session.createCriteria(CustDim8.class);
		}
		else if(table_name.equals("CustDim9"))
		{
			criteria=session.createCriteria(CustDim9.class);
		}
		else if(table_name.equals("ENTITIES"))
		{
			criteria=session.createCriteria(Entities.class);
		}
		else if(table_name.equals("MODEL"))
		{
			criteria=session.createCriteria(Model.class);
		}
		else if(table_name.equals("NATURE"))
		{
			criteria=session.createCriteria(Nature.class);
		}
		else if(table_name.equals("PERIOD"))
		{
			criteria=session.createCriteria(Period.class);
		}
		else if(table_name.equals("ratios"))
		{
			criteria=session.createCriteria(Ratios.class);
		}
		else if(table_name.equals("ROLES"))
		{
			criteria=session.createCriteria(Roles.class);
		}
		else if(table_name.equals("SCENARIO"))
		{
			criteria=session.createCriteria(Scenario.class);
		}
		else if(table_name.equals("TYPE"))
		{
			criteria=session.createCriteria(Type.class);
		}
		else if(table_name.equals("USERS"))
		{
			criteria=session.createCriteria(Users.class);
		}
		else if(table_name.equals("User_Status"))
		{
			criteria=session.createCriteria(UserStatus.class);
		}
		
		return criteria;
	}

	private Class getEntity(String table_name){

		
		if(table_name.equals("ACCOUNT"))
		{
			return com.java.pojo.Account.class;
		}
		else if(table_name.equals("APPLICATION"))
		{
			return com.java.pojo.Application.class;
		}
		else if(table_name.equals("Application_Status"))
		{
			return com.java.pojo.ApplicationStatus.class;
		}
		else if(table_name.equals("CATEGORY"))
		{
			return com.java.pojo.Category.class;
		}
		else if(table_name.equals("Category_Type"))
		{
			return com.java.pojo.CategoryType.class;
		}
		else if(table_name.equals("CURRENCY"))
		{
			return com.java.pojo.Currency.class;
		}
		else if(table_name.equals("CustDim1"))
		{
			return com.java.pojo.CustDim1.class;
		}
		else if(table_name.equals("CustDim10"))
		{
			return com.java.pojo.CustDim10.class;
		}
		else if(table_name.equals("CustDim11"))
		{
			return com.java.pojo.CustDim11.class;
		}
		else if(table_name.equals("CustDim12"))
		{
			return com.java.pojo.CustDim12.class;
		}
		else if(table_name.equals("CustDim2"))
		{
			return com.java.pojo.CustDim2.class;
		}
		else if(table_name.equals("CustDim3"))
		{
			return com.java.pojo.CustDim3.class;
		}
		else if(table_name.equals("CustDim4"))
		{
			return com.java.pojo.CustDim4.class;
		}
		else if(table_name.equals("CustDim5"))
		{
			return com.java.pojo.CustDim5.class;
		}
		else if(table_name.equals("CustDim6"))
		{
			return com.java.pojo.CustDim6.class;
		}
		else if(table_name.equals("CustDim7"))
		{
			return com.java.pojo.CustDim7.class;
		}
		else if(table_name.equals("CustDim8"))
		{
			return com.java.pojo.CustDim8.class;
		}
		else if(table_name.equals("CustDim9"))
		{
			return com.java.pojo.CustDim9.class;
		}
		else if(table_name.equals("ENTITIES"))
		{
			return com.java.pojo.Entities.class;
		}
		else if(table_name.equals("MODEL"))
		{
			return com.java.pojo.Model.class;
		}
		else if(table_name.equals("NATURE"))
		{
			return com.java.pojo.Nature.class;
		}
		else if(table_name.equals("PERIOD"))
		{
			return com.java.pojo.Period.class;
		}
		else if(table_name.equals("ROLES"))
		{
			return com.java.pojo.Roles.class;
		}
		else if(table_name.equals("SCENARIO"))
		{
			return com.java.pojo.Scenario.class;
		}
		else if(table_name.equals("TYPE"))
		{
			return com.java.pojo.Type.class;
		}
		else if(table_name.equals("USERS"))
		{
			return com.java.pojo.Users.class;
		}
		else if(table_name.equals("User_Status"))
		{
			return com.java.pojo.UserStatus.class;
		}
		
		return null;
	}

}
