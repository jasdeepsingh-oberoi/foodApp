package edu.sjsu.cmpe275.termprj.controller;

import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by wanghao on 3/22/16.
 */
@RestController
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemSvc;

	//Create Menu
	@RequestMapping(value = "/addMenuItem", method = RequestMethod.POST)
	public ResponseEntity<Void> postMenuItem(@RequestBody MenuItem item1) {
		System.out.println("in add menu item");
		menuItemSvc.add(item1);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//Read Menu By category
	@RequestMapping(value = "/queryMenuByCategory/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MenuItem>>  queryMenuByCategory(@PathVariable("category") Integer category) {

		List<MenuItem> itemList = menuItemSvc.getMenuByCategory(category);

		if (itemList != null) {
			return new ResponseEntity<List<MenuItem>>(itemList,HttpStatus.OK);
		}else{
			return new ResponseEntity<List<MenuItem>>(itemList,HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/showAllMenus", method = RequestMethod.GET)
	@ResponseBody
	public  ResponseEntity<List<MenuItem>>  showAllMenus() {

		List<MenuItem>  menuList = menuItemSvc.getAll();

		if (menuList != null) {
			return new ResponseEntity<List<MenuItem>>(menuList,HttpStatus.OK);
		}else{
			return new ResponseEntity<List<MenuItem>>(menuList,HttpStatus.NOT_FOUND);
		}
	}



	//obtain menuForm
	/*@RequestMapping(value = "/menuForm", method = RequestMethod.GET)
    public ModelAndView getMenuForm() {
        System.out.println("in menu form");

        ModelAndView model = new ModelAndView("View/ProfileTemplate");

        return model;
    }*/

	//Delete Menu By ID
	@RequestMapping(value = "/deleteMenuItem", method = RequestMethod.POST)
	public ResponseEntity<Void> deleteMenuItem(@RequestBody MenuItem menuItem) {
		boolean flag = false;
		MenuItem item1 = null;
		try {
			item1 = menuItemSvc.getById((Integer.parseInt(menuItem.getId())));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(item1);

		if (item1 != null) {
			item1.setIs_deleted(1);
			if(menuItemSvc.add(item1) == 1){
				flag = true;
			}	
		}
		if(flag){
			return new ResponseEntity<Void> (HttpStatus.OK);
		}else{
			return new ResponseEntity<Void> (HttpStatus.NOT_MODIFIED);
		}
	}



	//    //Delete Menu By name and Category
	//    @RequestMapping(value = "/deleteMenuByNameInCat", params = {"name", "category"},method = RequestMethod.DELETE)
	//    public ModelAndView deleteMenuByName(@RequestParam(value = "name") String name,
	//                                         @RequestParam(value = "category") Integer category) {
	//        List<MenuItem> allItemFound = menuItemSvc.getByName(name);
	//
	//        if(  allItemFound == null ) throw new PageNotFoundException(name);
	//
	//        for (MenuItem item1 : allItemFound) {
	////                System.out.println(item1.getImage_path());
	////                System.out.println("category found: " + item1.getCategory());
	////                System.out.println("category input: " + category);
	//                if (Integer.toString(item1.getCategory()).equals(category)) {
	////                    System.out.println("in side dataSetting");
	//                    item1.setIs_deleted(1);
	//                    menuItemSvc.update(item1);
	//                    ModelAndView model = new ModelAndView("View/AddSuccess");
	//                    model.addObject("item1", item1);
	//                    return model;
	//                }
	//            }
	//
	//    }


}

