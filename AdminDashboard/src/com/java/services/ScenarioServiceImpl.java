package com.java.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dao.ScenarioDAO;
import com.java.dao.ScenarioDAOImpl;
import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Scenario;
import com.java.entity.Scenario_Tree_Node;

public class ScenarioServiceImpl implements ScenarioService {

	private ScenarioDAO scenarioDAO = new ScenarioDAOImpl();
	@Override
	public List<Scenario> getAll(String application_no) {
		// TODO Auto-generated method stub
		return scenarioDAO.list(application_no);
	}

	@Override
	public Status addNewScenario(Scenario new_scenario) {
		
		Status status = scenarioDAO.add(new_scenario);
		log.info(new_scenario.getScenario_code() + " Added Successfully");
		return status;
	}
	@Override
	public Status editScenario(Scenario scenario) {
		
		//Add default values
		log.info("Editting Scenario "+scenario.getScenario_code());
		scenarioDAO.add(scenario);
		return null;
	}

	@Override
	public Status remove(Scenario scenario) {
		// TODO Auto-generated method stub
		
		Status status =scenarioDAO.delete(scenario);
		if(status.getCode()==0){
		log.info("Removed Scenario "+scenario.getScenario_code());
		}
		return status;
	}

	@Override
	public List<Scenario> getAll(String application_no, int start_index, int limit,List<Filter> filters) {

		return scenarioDAO.getAll(application_no,start_index,limit,filters);
	}

	@Override
	public int totalCount(String application_no,List<Filter> filters) {
		return scenarioDAO.total_records(application_no,filters);
	}

	@Override
	public Scenario get(String scenario_code) {
		// TODO Auto-generated method stub
		return scenarioDAO.get(scenario_code);
	}

	@Override
	public List<Scenario_Tree_Node> getAllNodes(String application_no) {
		// TODO Auto-generated method stub
		return scenarioDAO.getNodes(application_no);
	}

	@Override
	public Scenario_Tree_Node getNode(String node_code) {
		// TODO Auto-generated method stub
		return scenarioDAO.getNode(node_code);
	}

	@Override
	public int setNode(Scenario_Tree_Node node) {
		// TODO Auto-generated method stub
		 scenarioDAO.setNode(node);
	return 0;
	}

	@Override
	public List<Scenario> getLeafScenarioes(String node_code) {
		// TODO Auto-generated method stub
		return scenarioDAO.getLeafNodes(node_code);
	}

	@Override
	public void updateScenario(String scenario_code, String tree_sub_node) {
		// TODO Auto-generated method stub
		scenarioDAO.updateScenario(scenario_code,tree_sub_node);
	}

	@Override
	public List<Scenario> getAll(String application_no, String version) {

		return scenarioDAO.list(application_no,version);
	}





}
