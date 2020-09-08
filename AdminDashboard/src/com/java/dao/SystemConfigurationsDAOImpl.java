package com.java.dao;

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
import com.java.entity.Menu_Config;
import com.java.entity.Period;
import com.java.entity.SYSTEM_CONFIG_IMG;
import com.java.entity.Scenario;
import com.java.entity.Account;
import com.java.entity.Status;
import com.java.entity.System_Config_Tiles;
import com.java.entity.Users;

public class SystemConfigurationsDAOImpl implements SystemConfigurationsDAO {

	@Override
	public SYSTEM_CONFIG_IMG get(int id) {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		SYSTEM_CONFIG_IMG image = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(SYSTEM_CONFIG_IMG.class);
			crit.add(Restrictions.eq("id","Logo_img"));
			image = (SYSTEM_CONFIG_IMG)crit.uniqueResult();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return image;
	}

	@Override
	public Status add(SYSTEM_CONFIG_IMG image) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(image);
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
			s.close();
		}
		return Status.SUCCESS;
	}

	@Override
	public List<System_Config_Tiles> getAll() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<System_Config_Tiles> tiles = null;
		try {
			tx = session.beginTransaction();

			tiles = session.createQuery("FROM System_Config_Tiles").list();
		
					
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tiles;

	}

	@Override
	public Status add(System_Config_Tiles tile) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.save(tile);
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
	public Status update(System_Config_Tiles tile) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			//In order to update the tile get the actual object from database
			Criteria crit = s.createCriteria(System_Config_Tiles.class);
			crit.add(Restrictions.eq("id",tile.getId()));
			List<System_Config_Tiles> tiles = crit.list();
			
			System_Config_Tiles tile_obj=tiles.get(0);
			tile_obj.setDisplay_name(tile.getDisplay_name());
			tile_obj.setBackground_color(tile.getBackground_color());
			tile_obj.setUrl(tile.getUrl());
			
			s.update(tile_obj);
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
	public List<Menu_Config> listAll() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Menu_Config> dimensions = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Menu_Config.class);
			
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
	public List<Menu_Config> listVisible() {
		Session session = HibernateUtil.openSession();
		Transaction tx = null;
		List<Menu_Config> dimensions = null;
		try {
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Menu_Config.class);
			crit.add(Restrictions.eq("visibility",true));
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
	public Status update(Menu_Config dimensions) {
		Session s = HibernateUtil.openSession();
		Transaction tx = null;

		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(dimensions);
			tx.commit();
		}catch(HibernateException e){
			if (tx != null)
				tx.rollback();
			return Status.HibernateException;
		} finally {
			s.close();
		}
		return Status.SUCCESS;}

}
