package com.java.services;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.entity.AppDimensionsConfig;
import com.java.dao.AppDimensionsConfigDAO;
import com.java.dao.AppDimensionsConfigDAOImpl;
import com.java.dao.SystemConfigurationsDAOImpl;


public class AppConfigurationsServiceImpl implements AppConfigurationsService {

	private AppDimensionsConfigDAO appConfigurationDAO = new AppDimensionsConfigDAOImpl();

	
	//-----------------Custom Dimension Mapping Tab----------------//
	
	@Override
	public List<AppDimensionsConfig> listAll(String application_no) {
		//Logging in file
		log.info("Fetching data for "+application_no);
		
		return appConfigurationDAO.listAll(application_no);
	}

	@Override
	public List<AppDimensionsConfig> listVisible(String application_no) {
		// Logging in file
		log.info("Fetching data for " + application_no);
	
		return appConfigurationDAO.listVisible(application_no);
	}
	@Override
	public List<AppDimensionsConfig> listCustomDimensions(String application_no) {
		// Logging in file
		log.info("Fetching data for " + application_no);
	
		return appConfigurationDAO.listCustomDimensions(application_no);
	}

	/*Requirement: Default value for all custom dimensions
	 * All dimensions have a default value column.
	 * Only one object can be a default element
	 * If no element is set to default i.e. configuring for the first time , then create a record
	 * Default value is a mandatory field hence no need to check validation
	 * 
	 */
	@Override
	public Status edit(AppDimensionsConfig dimensions) {
		// Logging in file
		log.info("Updating custom dimension " + dimensions.getDefault_value());
				
		Status status = Status.SUCCESS;
		//check if default value exists for the selected dimension
		
		if(appConfigurationDAO.checkDefault(dimensions.getDefault_label(),dimensions.getApplication_no()) == null){
			//Create new object for the selected dimension
			status =appConfigurationDAO.createDefault(dimensions.getDefault_label(), dimensions.getDefault_value(),dimensions.getCreated_user(),dimensions.getApplication_no());
		}
		
		//If default value successfully created
		if(status.getCode() ==0){
		status = appConfigurationDAO.update(dimensions);
		}
		return status;
	}
	
}
