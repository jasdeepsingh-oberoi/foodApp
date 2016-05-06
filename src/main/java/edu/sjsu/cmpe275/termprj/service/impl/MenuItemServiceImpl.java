package edu.sjsu.cmpe275.termprj.service.impl;

import edu.sjsu.cmpe275.termprj.dao.MenuItemDao;
import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.service.MenuItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by wanghao on 3/23/16.
 */

@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService{
	
	@Autowired
	private MenuItemDao menuItemDao;

	public List<MenuItem> getAll() {
		return menuItemDao.getAll();
	}

	public List<MenuItem> getMenuByCategory(Integer category) {
		return menuItemDao.getMenuByCategory(category);
	}

	public MenuItem getById(Integer id) {
		return menuItemDao.getById(id);
	}

	public List<MenuItem> getByName(String name) {
		return menuItemDao.getByName(name);
	}

	public void delete(Integer id) {
		menuItemDao.delete(id);		
	}

	/*public void update(MenuItem p) {
		menuItemDao.update(p);
	}*/

	public int add(MenuItem p) {
		return menuItemDao.add(p);
	}
}
