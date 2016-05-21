package edu.sjsu.cmpe275.termprj.dao;

import java.util.List;

import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.model.MenuItemCount;
import edu.sjsu.cmpe275.termprj.model.Order;

public interface MenuItemDao {
	public List<MenuItem> getAll();
	public List<MenuItem> getMenuByCategory(Integer category);
	public MenuItem getById(Integer id);
	public List<MenuItem> getByName(String name);
	public int delete(Integer id);
	public int update(MenuItem p);
	public MenuItem add(MenuItem p);
	public MenuItem addImage(MenuItem p);
	public List<MenuItemCount> getPopRepByCategory(Integer category,String startDatestring,String endDatestring);
	public List<Order> getStatRep(String startDatestring,String endDatestring);
}
