package com.java.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.System_Config_Tiles;
import com.java.dao.SystemConfigurationsDAO;
import com.java.dao.SystemConfigurationsDAOImpl;
import com.java.entity.Menu_Config;
import com.java.entity.SYSTEM_CONFIG_IMG;

public class SystemConfigurationsServiceImpl implements SystemConfigurationsService {

	private SystemConfigurationsDAO systemConfigurationDAO = new SystemConfigurationsDAOImpl();

	@Override
	public SYSTEM_CONFIG_IMG get_img() {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.get(0);
	}

	@Override
	public Status save_img(SYSTEM_CONFIG_IMG image) {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.add(image);
	}

	@Override
	public List<System_Config_Tiles> getAllTiles() {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.getAll();
	}

	@Override
	public Status addTile(System_Config_Tiles new_tile) {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.add(new_tile);
	}
	@Override
	public Status updateTile(System_Config_Tiles new_tile) {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.update(new_tile);
	}

	@Override
	public List<Menu_Config> listAll() {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.listAll();
	}

	@Override
	public List<Menu_Config> listVisible() {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.listVisible();
	}

	@Override
	public Status edit(Menu_Config dimensions) {
		// TODO Auto-generated method stub
		return systemConfigurationDAO.update(dimensions);
	}
	
}
