package edu.sjsu.cmpe275.termprj.service;

import java.util.List;

import edu.sjsu.cmpe275.termprj.model.MenuItem;

public interface MenuItemService {
	public List<MenuItem> getAll();
	public List<MenuItem> getMenuByCategory(Integer category);
	public MenuItem getById(Integer id);
	public List<MenuItem> getByName(String name);
	public void delete(Integer id);
	//public void update(MenuItem p);
	public int add(MenuItem p);
}
