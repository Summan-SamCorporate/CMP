package com.java.dao;

import java.util.List;

import com.java.entity.Account;
import com.java.entity.Menu_Config;
import com.java.entity.Period;
import com.java.entity.SYSTEM_CONFIG_IMG;
import com.java.entity.Status;
import com.java.entity.System_Config_Tiles;


public interface SystemConfigurationsDAO {
	
	public SYSTEM_CONFIG_IMG get(int id);
	public Status add(SYSTEM_CONFIG_IMG image);
	
	public List<System_Config_Tiles> getAll();
	public Status add(System_Config_Tiles tile);
	public Status update(System_Config_Tiles tile);
	
	public List<Menu_Config> listAll();
	public List<Menu_Config> listVisible();
	public Status update(Menu_Config dimensions);
	}
