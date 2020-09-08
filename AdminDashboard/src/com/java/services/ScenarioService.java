package com.java.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.entity.Status;
import com.java.pojo.Filter;
import com.java.entity.Period;
import com.java.entity.Scenario;
import com.java.entity.Scenario_Tree_Node;

/* Service  created to fetch / update / add data in Application table */

public interface ScenarioService {
	static Logger log = Logger.getLogger(ScenarioService.class);
	
	public List<Scenario> getAll(String application_no);
	public Status addNewScenario(Scenario new_scenario);
	public Status remove(Scenario new_scenario);
	public Status editScenario(Scenario new_scenario);
	public List<Scenario> getAll(String application_no, int start_index, int limit,List<Filter> filters);
	public int totalCount(String application_no,List<Filter> filters);
	
	public Scenario get(String scenario_code);
	
	//get scenario with version
	public List<Scenario> getAll(String application_no, String version);
	
	//Scenario Hierarchy Management
	public List<Scenario_Tree_Node> getAllNodes(String application_no);
	public Scenario_Tree_Node getNode(String node_code);
	public int setNode(Scenario_Tree_Node node);
	public List<Scenario> getLeafScenarioes(String node_code);
	public void updateScenario(String scenario_code,String tree_sub_node);
}
