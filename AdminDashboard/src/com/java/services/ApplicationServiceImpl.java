package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.dao.AppDimensionsConfigDAO;
import com.java.dao.AppDimensionsConfigDAOImpl;
import com.java.dao.ApplicationDAO;
import com.java.dao.ApplicationDAOImpl;
import com.java.dao.SystemConfigurationsDAO;
import com.java.dao.SystemConfigurationsDAOImpl;
import com.java.entity.Status;
import com.java.entity.Users;
import com.java.entity.AppDimensionsConfig;
import com.java.entity.Application;
import com.java.entity.Menu_Config;
import com.java.pojo.Filter;

public class ApplicationServiceImpl implements ApplicationService {

	private ApplicationDAO applicationDAO = new ApplicationDAOImpl();
	private SystemConfigurationsDAO systemconfigDAO = new SystemConfigurationsDAOImpl();
	private AppDimensionsConfigDAO appConfigurationDAO = new AppDimensionsConfigDAOImpl();

	@Override
	public List<Application> getAll() {
		// TODO Auto-generated method stub
		return applicationDAO.list();
	}

	@Override
	public List<Application> getAll(String user_name) {
		// TODO Auto-generated method stub
		return applicationDAO.list(user_name);
	}

	@Override
	public Status addNewApplication(Application new_application) {
		
		Status status =applicationDAO.add(new_application);
		if(status.getCode()==0){
		log.info("New Application "+new_application.getApp_refno() + " Added Successfully");
		String app = new_application.getApp_refno();
		
		/*
		 * Create default dimensions for application created
		 *Generate dimesnions with system generated values.
		 *First time dimensions will be configured will configure default values 
		 *Default value is set at the time of configuration.
		 *There can be only one default value hence the check will be if there exists any default value or not. If no default value
		 *already present then create a new entry and mark it as default.
		 * 
		 **/
		List<AppDimensionsConfig> app_dimensions = new ArrayList<AppDimensionsConfig>();
		
		AppDimensionsConfig app_entity = new AppDimensionsConfig(app,"Entity",true,"Entity","EntityManagement",/*"EntityHierarchyManagement"*/"","B");
		AppDimensionsConfig app_account = new AppDimensionsConfig(app,"Accounts",true,"Accounts","Accounts","AccountHierarchy","B");
		AppDimensionsConfig customdim1 = new AppDimensionsConfig(app,"CustomDimension1",false,"","CustomDim1","","");
		AppDimensionsConfig customdim2 = new AppDimensionsConfig(app,"CustomDimension2",false,"","CustomDim2","","");
		AppDimensionsConfig customdim3 = new AppDimensionsConfig(app,"CustomDimension3",false,"","CustomDim3","","");
		AppDimensionsConfig customdim4 = new AppDimensionsConfig(app,"CustomDimension4",false,"","CustomDim4","","");
		AppDimensionsConfig customdim5 = new AppDimensionsConfig(app,"CustomDimension5",false,"","CustomDim5","","");
		AppDimensionsConfig customdim6 = new AppDimensionsConfig(app,"CustomDimension6",false,"","CustomDim6","","");
		AppDimensionsConfig customdim7 = new AppDimensionsConfig(app,"CustomDimension7",false,"","CustomDim7","","");
		AppDimensionsConfig customdim8 = new AppDimensionsConfig(app,"CustomDimension8",false,"","CustomDim8","","");
		AppDimensionsConfig customdim9 = new AppDimensionsConfig(app,"CustomDimension9",false,"","CustomDim9","","");
		AppDimensionsConfig customdim10 = new AppDimensionsConfig(app,"CustomDimension10",false,"","CustomDim10","","");
		AppDimensionsConfig customdim11 = new AppDimensionsConfig(app,"CustomDimension11",false,"","CustomDim11","","");
		AppDimensionsConfig customdim12 = new AppDimensionsConfig(app,"CustomDimension12",false,"","CustomDim12","","");
			
		app_dimensions.add(app_entity);
		app_dimensions.add(app_account);
		app_dimensions.add(customdim1);
		app_dimensions.add(customdim2);
		app_dimensions.add(customdim3);
		app_dimensions.add(customdim4);
		app_dimensions.add(customdim5);
		app_dimensions.add(customdim6);
		app_dimensions.add(customdim7);
		app_dimensions.add(customdim8);
		app_dimensions.add(customdim9);
		app_dimensions.add(customdim10);
		app_dimensions.add(customdim11);
		app_dimensions.add(customdim12);
		
		
		appConfigurationDAO.addAll(app_dimensions);
		
		
		}
		return status;
	}
	@Override
	public Status editApplication(Application application) {
		
		//Add default values
		log.info("Editting Application "+application.getApp_refno());		
		applicationDAO.Update(application);
		return null;
	}

	@Override
	public Status remove(Application application) {
		// TODO Auto-generated method stub
		Status status= applicationDAO.delete(application);
		if(status.getCode()==0){
			log.info("Removed Application "+application.getApp_refno());
			}
		return status;
		
	}

	@Override
	public List<Application> getAll(String user_name,int start_index, int limit,List<Filter> filters) {

		return applicationDAO.getAll(user_name,start_index,limit,filters);
	}

	@Override
	public int totalCount(String user_name,List<Filter> filters) {
		return applicationDAO.total_records(user_name,filters);
	}

}
