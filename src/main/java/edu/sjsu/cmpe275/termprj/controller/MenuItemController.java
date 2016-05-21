package edu.sjsu.cmpe275.termprj.controller;

import edu.sjsu.cmpe275.termprj.model.MenuItem;
import edu.sjsu.cmpe275.termprj.model.MenuItemCount;
import edu.sjsu.cmpe275.termprj.model.Order;
import edu.sjsu.cmpe275.termprj.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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

		if(category == 0) {
			return showAllMenus();
		}

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
			if(menuItemSvc.update(item1) == 1){
				flag = true;
			}	
		}
		if(flag){
			return new ResponseEntity<Void> (HttpStatus.OK);
		}else{
			return new ResponseEntity<Void> (HttpStatus.NOT_MODIFIED);
		}
	}
	@RequestMapping(value = "/adminDashboard", method = RequestMethod.POST)
	public ModelAndView uploadPicture(@RequestParam(value="file") MultipartFile file, HttpServletRequest request){
		System.out.println("in add menu item");
		BufferedImage src = null;

		if(!file.isEmpty()) {

			try {
				src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String originalName = file.getOriginalFilename();
			System.out.println(originalName);

			ServletContext sCtx = request.getSession().getServletContext();
			String rpath=sCtx.getRealPath("/");
			//String rpath = request.getContextPath()+"/resources/";
			System.out.println(rpath);

			String filePath =  "/resources/img/" + originalName;
			String totalPtah = rpath + filePath;
			File destination = new File(totalPtah);

			try {
				ImageIO.write(src, "png", destination);
				System.out.println("inside imageIo.write");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			MenuItem menuitem = new MenuItem();
			menuitem.setImage_path("/termprj"+filePath);
			menuitem = menuItemSvc.addImage(menuitem);
			System.out.println("checking " +menuitem.getImage_path());
			/*item1.setImage_path(filePath);
            menuItemSvc.add(item1);*/
		} else {
			System.out.println("error in uploading the image");
		}
		ModelAndView model = new ModelAndView("adminDashboard");
		return model;
	}

	@RequestMapping(value = "/addNewMenuData", method = RequestMethod.POST)
	public ResponseEntity<MenuItem> addNewMenuData(@RequestBody MenuItem item1) {
		System.out.println("in add menu item");

		MenuItem dbMenu = menuItemSvc.add(item1);
		item1.setName(dbMenu.getName());
		item1.setCategory(dbMenu.getCategory());
		return new ResponseEntity<MenuItem>(item1,HttpStatus.OK);
	}

	/*@RequestMapping(value = "/addNewMenuItem", method = RequestMethod.POST)
	public String addMenuItem(@RequestParam("file") MultipartFile file, @RequestParam("data") MenuItem menuItem, HttpServletRequest request){
		System.out.println("in add menu item");
		System.out.println(menuItem.getName());
		System.out.println(file.getOriginalFilename());
		BufferedImage src = null;

		if(!file.isEmpty()) {

			try {
				src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String originalName = file.getOriginalFilename();
			System.out.println(originalName);

			ServletContext sCtx = request.getSession().getServletContext();
			String rpath=sCtx.getRealPath("/");
			//String rpath = request.getContextPath()+"/resources/";
			System.out.println(rpath);

			String filePath =  "/resources/img/" + originalName;
			String totalPtah = rpath + filePath;
			File destination = new File(totalPtah);

			try {
				ImageIO.write(src, "png", destination);
				System.out.println("inside imageIo.write");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			item1.setImage_path(filePath);
            menuItemSvc.add(item1);
		} else {
			throw new RuntimeException("image is empty");
		}

		return null;
	}*/



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

	@RequestMapping(value = "/getPopReportByCategory/{category}/{startDatestring}/{endDatestring}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<MenuItemCount>> queryPopRepByCategory(@PathVariable("category") Integer category,
			@PathVariable("startDatestring") String startDatestring,
			@PathVariable("endDatestring") String endDatestring){

		if(category == 0) {
			return null;
		}

		List<MenuItemCount> itemList = menuItemSvc.getPopRepByCategory(category,startDatestring,endDatestring);

		if (itemList != null) {
			return new ResponseEntity<List<MenuItemCount>>(itemList,HttpStatus.OK);
		}else{
			return new ResponseEntity<List<MenuItemCount>>(itemList,HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/getStatReport/{startDatestring}/{endDatestring}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Order>> queryPopRepByCategory(@PathVariable("startDatestring") String startDatestring,
			@PathVariable("endDatestring") String endDatestring){


		List<Order> itemList = menuItemSvc.getStatRep(startDatestring,endDatestring);

		if (itemList != null) {
			return new ResponseEntity<List<Order>>(itemList,HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Order>>(itemList,HttpStatus.NOT_FOUND);
		}

	}
}

