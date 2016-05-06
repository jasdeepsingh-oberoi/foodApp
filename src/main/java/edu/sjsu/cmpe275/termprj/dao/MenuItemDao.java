package edu.sjsu.cmpe275.termprj.dao;

import java.util.List;

import edu.sjsu.cmpe275.termprj.model.MenuItem;

public interface MenuItemDao {
	public List<MenuItem> getAll();
	public List<MenuItem> getMenuByCategory(Integer category);
	public MenuItem getById(Integer id);
	public List<MenuItem> getByName(String name);
	public int delete(Integer id);
	/*public int update(MenuItem p);*/
	public int add(MenuItem p);
}
