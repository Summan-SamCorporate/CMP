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
import com.java.entity.Period;
import com.java.entity.Scenario;
import com.java.entity.Scenario_Tree_Node;
import com.java.entity.Status;
import com.java.entity.Scenario_Version;
import com.java.entity.Account;
import com.java.pojo.Filter;

public class ScenarioDAOImpl implements ScenarioDAO {
	
	@Override
	public Status add(Scenario scenario) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(scenario);
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
	public Status update(Scenario scenario) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(scenario);
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
	public List<Scenario> list(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario> scenarioes = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Scenario.class);
			crit.add(Restrictions.eq("application_no",application_no));
			scenarioes = crit.list();
					
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenarioes;

	}

	@Override
	public Status delete(Scenario scenario) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(scenario);
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
	public List<Scenario> list(String application_no, int start_index, int limit) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario> scenarioes = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Scenario.class);
			crit.add(Restrictions.eq("application_no",application_no));
			crit.setFirstResult(start_index);
			crit.setMaxResults(limit);
			
			scenarioes = crit.list();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenarioes;


	}

	@Override
	public int total_records(String application_no,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		int total_records= -1;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Scenario.class);
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

	@Override
	public List<Scenario> getAll(String application_no, int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario> scenarioes = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Scenario.class);
			crit.add(Restrictions.eq("application_no",application_no));
			crit.addOrder(Order.asc("scenario_code"));
			
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
			scenarioes = crit.list();			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenarioes;


	}

	@Override
	public Scenario get(String scenario_code) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		Scenario scenario= null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Scenario.class);
			crit.add(Restrictions.eq("scenario_code",scenario_code));
			List<Scenario> list = crit.list();
			if(list.size()>0){
			scenario = list.get(0);
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenario;
	}

	@Override
	public List<Scenario_Tree_Node> getNodes(String application_no) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario_Tree_Node> scenarioes_tree = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Scenario_Tree_Node.class);
			crit.add(Restrictions.eq("application_no",application_no));
			scenarioes_tree = crit.list();
					
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenarioes_tree;
	}
	

	@Override
	public Scenario_Tree_Node getNode(String node_code) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		Scenario_Tree_Node node= null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Scenario_Tree_Node.class);
			crit.add(Restrictions.eq("node_code",node_code));
			List<Scenario_Tree_Node> list = crit.list();
			if(list.size()>0){
				node = list.get(0);
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return node;
	}

	
	@Override
	public Status setNode(Scenario_Tree_Node node) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.update(node);
		
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Status.SUCCESS;
	}

	
	@Override
	public List<Scenario> getLeafNodes(String node_code) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario> scenarioes = null;
		try {
			tx = session.beginTransaction();

			Criteria crit = session.createCriteria(Scenario.class);
			crit.add(Restrictions.eq("tree_sub_node",node_code));
			scenarioes = crit.list();
					
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenarioes;
	}

	@Override
	public void updateScenario(String scenario_code, String tree_sub_node) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario> scenarioes = null;
		try {
			tx = session.beginTransaction();

			String hqlUpdate = "update Scenario s set s.tree_sub_node = :newSubNode where s.scenario_code = :scenario_code";
			
			int updatedEntities = session.createQuery( hqlUpdate )
			        .setString( "newSubNode", tree_sub_node )
			        .setString( "scenario_code", scenario_code )
			        .executeUpdate();
			tx.commit();		
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

	@Override
	public List<Scenario> list(String application_no, String version) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Scenario> scenarioes = null;
		try {
			tx = session.beginTransaction();

			//Get actual version object TODO just store the version as a string
			Criteria crit1 = session.createCriteria(Scenario_Version.class);
			crit1.add(Restrictions.eq("version_code",version));
			Scenario_Version ver =(Scenario_Version) crit1.list().get(0);
			
			Criteria crit = session.createCriteria(Scenario.class);
			crit.add(Restrictions.eq("application_no",application_no));
			
			crit.add(Restrictions.eq("version",ver));
			crit.addOrder(Order.asc("scenario_code"));
			
			scenarioes = crit.list();
					
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return scenarioes;
	}

}
