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
import com.java.entity.Nature;
import com.java.entity.Period;
import com.java.entity.AccountHierarchy;
import com.java.entity.AccountHierarchyMapping;
import com.java.entity.AccountHierarchyStructure;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.pojo.AccountTreeNode;
import com.java.pojo.Filter;

public class AccountHierarchyDAOImpl implements AccountHierarchyDAO {
	@Override
	public Status add(AccountHierarchy hierarchy) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(hierarchy);
			tx.commit();
		}catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.NONUNIQUEVALUE;
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		}finally {
			s.close();
		}
		return Status.SUCCESS;
	}
	@Override
	public AccountHierarchy Update(AccountHierarchy hierarchy) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(hierarchy);
			tx.commit();
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		}finally {
			s.close();
		}
		return hierarchy;
	}



	@Override
	public Status delete(AccountHierarchy hierarchy) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(hierarchy);
			
			//When deleting a hierarchy also delete hierarchy structure
			Criteria crit = session.createCriteria(AccountHierarchyStructure.class);
			crit.add(Restrictions.eq("node",hierarchy.getAccount_hierarchy_code()));
			List<AccountHierarchyStructure> structure = crit.list();
			
			for(int i=0;i<structure.size(); i++){
				session.delete(structure.get(i));
			}
			
			
			tx.commit();
		}  catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			return Status.CANNOTDELETE;
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		}finally {
			session.close();
		}
		return Status.SUCCESS;
	}
	
	@Override
	public List<AccountHierarchy> getAll(String application_no, int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AccountHierarchy> hierarchy = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AccountHierarchy.class);
			crit.add(Restrictions.eq("application_no",application_no));
			crit.addOrder(Order.asc("account_hierarchy_code"));
			
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
			hierarchy = crit.list();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return hierarchy;


	}

	@Override
	public int total_records(String application_no,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AccountHierarchy> hierarchy = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AccountHierarchy.class);
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
			hierarchy = crit.list();	
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return hierarchy.size();


	}

	@Override
	public Status create(AccountHierarchyStructure root) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(root);
			tx.commit();
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		}finally {
			s.close();
		}
		return Status.SUCCESS;

	}

	@Override
	public Status create(List<AccountHierarchyStructure> tree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status update(List<AccountHierarchyStructure> tree) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(tree);
			tx.commit();
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		}finally {
			s.close();
		}
		return Status.SUCCESS;

	}

	@Override
	public Status update(AccountHierarchyStructure node) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.update(node);
			tx.commit();
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return Status.HibernateException;
		}finally {
			s.close();
		}
		return Status.SUCCESS;
	}

	
	
	/*
	 * Delete will also delete record recursively , trace the botttom element of the selected node first and delete it.
	 * 
	 */
	@Override
	public Status delete(AccountHierarchyStructure node) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		
		//Get the list of the items to be deleted as we cannot delete using recursive function
		
		try{
			tx = session.beginTransaction();
		
			List<AccountHierarchyStructure> list = new ArrayList<AccountHierarchyStructure>();
			list = get_recursively(node,list,session);
		
			for(int i=0; i<list.size();i++){
			session.delete(list.get(i));
			}
			
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx != null)
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	return Status.SUCCESS;
	}
	
	
	private List<AccountHierarchyStructure> get_recursively(AccountHierarchyStructure parent_node,List<AccountHierarchyStructure> list, Session session){
		
		Criteria crit = session.createCriteria(AccountHierarchyStructure.class);
		crit.add(Restrictions.eq("parent",parent_node.getNode()));
		
		List<AccountHierarchyStructure> child_list = crit.list();
		
		for(int i=0; i< child_list.size(); i++){
			
			get_recursively(child_list.get(i),list,session);
			//list.add(child_list.get(i));
		}
		list.add(parent_node);
		return list;
	}


	
	
	@Override
	public AccountTreeNode get(String account_hierarchy_code) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;

		AccountTreeNode root_node = new AccountTreeNode();
		try {
			tx = session.beginTransaction();
			//Get the root node for the selected account hierarchy
			Criteria crit = session.createCriteria(AccountHierarchyStructure.class);
			crit.add(Restrictions.eq("account_hierarchy_code",account_hierarchy_code));
			crit.add(Restrictions.eq("node_type","root"));
			
			AccountHierarchyStructure root = (AccountHierarchyStructure) crit.list().get(0);

			
			root_node.setNode(root.getNode());
			root_node.setText(root.getNode());
			root_node.setNode_description(root.getNode_description());	
			root_node.setAccount_hierarchy_code(root.getAccount_hierarchy_code());
			root_node.setParent("0");
			root_node.setParent_description("0");
			root_node.setNode_type("root");
			root_node.setLabel(root.getNode());
			root_node.setLeaf(false);		
			root_node.setExpanded(true);
		
			root_node =getChildern(root,root_node,session );

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return root_node;


	}
	
	private AccountTreeNode getChildern(AccountHierarchyStructure parent_node,AccountTreeNode tree, Session session){
		
		Criteria crit = session.createCriteria(AccountHierarchyStructure.class);
		crit.add(Restrictions.eq("parent",parent_node.getNode()));
		
		List<AccountHierarchyStructure> child_list = crit.list();
		
		for(int i=0; i< child_list.size(); i++){
			//---create node
			AccountTreeNode node = new AccountTreeNode();
			node.setNode(child_list.get(i).getNode());
			node.setText(child_list.get(i).getNode());
			node.setNode_description(child_list.get(i).getNode_description());	
			node.setAccount_hierarchy_code(child_list.get(i).getAccount_hierarchy_code());
			node.setLeaf(false);		
			node.setExpanded(true);
			node.setParent(child_list.get(i).getParent());
			node.setParent_description(child_list.get(i).getParent_description());
			node.setNode_type(child_list.get(i).getNode_type());
			node.setLabel(child_list.get(i).getLabel());
					
			//----
			getChildern(child_list.get(i),node,session);
			//Add in tree passed
			tree.getChildren().add(node);
		}
		System.out.println(parent_node.getNode());
		return tree;
	}
	
	//-----------------Account Hierarchy Mapping----//
	@Override
	public AccountHierarchyMapping addUpdate(AccountHierarchyMapping mapping) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(mapping);
			tx.commit();
		}  catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		}finally {
			s.close();
		}
		return mapping;
	}

	@Override
	public Status delete(AccountHierarchyMapping mapping) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.delete(mapping);
			
			
			
			tx.commit();
		}  catch (ConstraintViolationException e) {
			if (tx != null)
				tx.rollback();
			return Status.CANNOTDELETE;
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		}finally {
			session.close();
		}
		return Status.SUCCESS;
	}
	
	@Override
	public List<AccountHierarchyStructure> getNodesList(String account_hierarchy_code) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AccountHierarchyStructure> mapping = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AccountHierarchyStructure.class);
			
			crit.add(Restrictions.eq("account_hierarchy_code",account_hierarchy_code));
			
			mapping = crit.list();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return mapping;


	}
	@Override
	public List<AccountHierarchyMapping> getAllMapping(String application_no,String account_hierarchy_code, int start_index, int limit,List<Filter> filter) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AccountHierarchyMapping> mapping = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AccountHierarchyMapping.class);
			crit.add(Restrictions.eq("application_no",application_no));
			crit.add(Restrictions.eq("account_hierarchy_code",account_hierarchy_code));
			
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
			mapping = crit.list();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return mapping;


	}
	@Override
	public List<AccountHierarchyMapping> getAllMapping(String node_code) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<AccountHierarchyMapping> mapping = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(AccountHierarchyMapping.class);
			crit.add(Restrictions.eq("node_code",node_code));
		
			mapping = crit.list();
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return mapping;


	}

}
