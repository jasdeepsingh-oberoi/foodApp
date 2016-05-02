package cmpe275.controller;
import cmpe275.Model.MenuItem;
import cmpe275.Service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by wanghao on 3/22/16.
 */
@Controller
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemSvc;

    //Create Menu
    @RequestMapping(value = "/addMenuItem", method = RequestMethod.POST)
    public ModelAndView postMenuItem(@ModelAttribute("item1") MenuItem item1) {
        System.out.println("in add menu item");

        menuItemSvc.add(item1);

        ModelAndView model = new ModelAndView("View/AddSuccess");

        model.addObject("item1", item1);
        return model;
    }

    //Read Menu By category
    @RequestMapping(value = "/queryMenuByCategory/{category}", method = RequestMethod.GET)
    public ModelAndView queryMenuByCategory(@PathVariable("category") Integer category) {
        List<MenuItem> itemList = null;
        try {
            itemList = menuItemSvc.getMenuByCategory(category);
        } catch (Exception e) {
            ModelAndView model = new ModelAndView("View/404.jsp");
            return model;
        }

            ModelAndView model = new ModelAndView("View/showMenu");

            model.addObject("menuList", itemList);

            return model;
    }

    @RequestMapping(value = "/showAllMenus", method = RequestMethod.GET)
    public ModelAndView showAllMenus() {

        List<MenuItem> menuList = null;
        try {
             menuList = menuItemSvc.getAll();
        } catch (Exception e) {
            ModelAndView model = new ModelAndView("View/404.jsp");
            return model;
        }


        ModelAndView model = new ModelAndView("View/showMenu");

        model.addObject("menuList", menuList);
        return model;
    }



    //obtain menuForm
    @RequestMapping(value = "/menuForm", method = RequestMethod.GET)
    public ModelAndView getMenuForm() {
        System.out.println("in menu form");

        ModelAndView model = new ModelAndView("View/ProfileTemplate");

        return model;
    }




    //Delete Menu By ID
    @RequestMapping(value = "/deleteMenuItem/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteMenuItem(@PathVariable("id") String id) {

        MenuItem item1 = null;
        try {
            item1 = menuItemSvc.getById(id);
        } catch(Exception e) {
            ModelAndView model = new ModelAndView("View/404.jsp");
            return model;
        }

        item1.setIs_deleted(1);
        menuItemSvc.update(item1);

        ModelAndView model = new ModelAndView("View/AddSuccess");
        model.addObject("item1", item1);
        return model;
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
