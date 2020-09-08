package com.java.dao;

/*
 * Created By: Summan Bahadur
 * Description: Data Access Object Interface for table app_dimensions_config
 * Reference/s: Accessed from AppConfigurationService
 * 
 * 
 */

import java.util.List;

import com.java.entity.Status;
import com.java.entity.AppDimensionsConfig;


public interface AppDimensionsConfigDAO {
	
	/*List all dimensions of current application*/
	public List<AppDimensionsConfig> listAll(String application_no);
	/*List all dimensions of current application that are visible*/
	public List<AppDimensionsConfig> listVisible(String application_no);
	public List<AppDimensionsConfig> listCustomDimensions(String application_no);
	
	/*Update record in table*/
	public Status update(AppDimensionsConfig dimensions);
	/*Add record in table*/
	public Status add(AppDimensionsConfig dimensions);
	public Status addAll(List<AppDimensionsConfig> dimensions);
	
	
	/*Dimension name is dynamic hence class name will be the default label of the dimension
	 *Default label should be mapped with the entity class object 
	 */
	public Status checkDefault(String dimension_class,String application_no);
	public Status createDefault(String dimension_class,String default_value,String user_created,String application_no);

}
