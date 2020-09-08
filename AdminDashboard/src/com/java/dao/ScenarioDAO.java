package com.java.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.entity.Period;
import com.java.entity.Scenario;
import com.java.entity.Scenario_Tree_Node;
import com.java.entity.Status;
import com.java.entity.Account;
import com.java.pojo.Filter;


public interface ScenarioDAO {
	
	public List<Scenario> list(String application_no);
	public Status add(Scenario scenario);
	public Status update(Scenario scenario);
	
	public Status delete(Scenario scenario);
	public List<Scenario> list(String application_no, int start_index, int limit);
	public List<Scenario> list(String application_no,String version);
	
	public int total_records(String application_no,List<Filter> filter);
	public List<Scenario> getAll(String application_no, int start_index, int limit,List<Filter> filter);
	
	public Scenario get(String scenario_code);
	
	public List<Scenario_Tree_Node> getNodes(String application_no);
	public Scenario_Tree_Node getNode(String node_code);
	public Status setNode(Scenario_Tree_Node node);	
	public List<Scenario> getLeafNodes(String node_code);
	public void updateScenario(String scenario_code, String tree_sub_node);
	}
