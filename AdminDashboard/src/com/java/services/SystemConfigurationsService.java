package com.java.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Menu_Config;
import com.java.entity.SYSTEM_CONFIG_IMG;
import com.java.entity.Status;
import com.java.entity.System_Config_Tiles;


public interface SystemConfigurationsService {
	static Logger log = Logger.getLogger(SystemConfigurationsService.class);
	
	
	public SYSTEM_CONFIG_IMG get_img();
	public Status save_img(SYSTEM_CONFIG_IMG image);
	
	public List<System_Config_Tiles> getAllTiles();
	public Status addTile(System_Config_Tiles new_tile);
	public Status updateTile(System_Config_Tiles new_tile);
	
	public List<Menu_Config> listAll();
	public List<Menu_Config> listVisible();
	public Status edit(Menu_Config dimensions);
}
