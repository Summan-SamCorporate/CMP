package com.java.services;

/*
 * Created By: Summan Bahadur
 * Description: Service Interface for module App Configuration
 * Reference/s: Accessed from AppConfigurationServiceImpl
 * 
 * 
 */
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.AppDimensionsConfig;
import com.java.entity.Status;

public interface AppConfigurationsService {
	static Logger log = Logger.getLogger(AppConfigurationsService.class);
	
	
	//-----------------Custom Dimension Mapping Tab----------------//
	
	/*Get all custom dimensions for current selected application*/
	public List<AppDimensionsConfig> listAll(String application_no);
	/*Get all custom dimensions for current selected application that are set to visible*/
	public List<AppDimensionsConfig> listVisible(String application_no);
	public List<AppDimensionsConfig> listCustomDimensions(String application_no);
	
	/*Update values that changed*/
	public Status edit(AppDimensionsConfig dimensions);
}
