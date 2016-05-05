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
    @ResponseBody
    public String postMenuItem(@ModelAttribute("item1") MenuItem item1) {
        System.out.println("in add menu item");

        menuItemSvc.add(item1);


        return "Created";
    }


    //Read Menu By category
    @RequestMapping(value = "/queryMenuByCategory/{category}", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem>  queryMenuByCategory(@PathVariable("category") Integer category) {
        if(category == 0) {
            return showAllMenus();
        }
        System.out.println("query called");
        List<MenuItem> itemList = menuItemSvc.getMenuByCategory(category);

        if (itemList == null) {
            throw new RuntimeException("Cannot find menu items");
        }

        return itemList;
    }

    @RequestMapping(value = "/showAllMenus", method = RequestMethod.GET)
    @ResponseBody
    public  List<MenuItem>  showAllMenus() {

        List<MenuItem>  menuList = menuItemSvc.getAll();

        if (menuList == null) {
            throw new RuntimeException("Menu List is empty");
        }
        return menuList;
    }



    //obtain menuForm
    @RequestMapping(value = "/menuForm", method = RequestMethod.GET)
    public ModelAndView getMenuForm() {
        System.out.println("in menu form");

        ModelAndView model = new ModelAndView("View/ProfileTemplate");

        return model;
    }

    @RequestMapping(value = "/menuHome", method = RequestMethod.GET)
    public ModelAndView getMenuHome() {
        List<MenuItem>  menuList = menuItemSvc.getAll();
        System.out.println("in menuHome");
        if (menuList == null) {
            throw new RuntimeException("Menu List is empty");
        }

        ModelAndView model = new ModelAndView("View/menuHomepage");
        model.addObject("menus", menuList);

        return model;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public ModelAndView getCart() {


        ModelAndView model = new ModelAndView("View/cart");
        return model;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView gocheckout() {
        ModelAndView model = new ModelAndView("View/checkout");
        return model;
    }


    //Delete Menu By ID
    @RequestMapping(value = "/deleteMenuItem/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMenuItem(@PathVariable("id") String id) {
        MenuItem item1 = null;
        try {
             item1 = menuItemSvc.getById(id);
        } catch(Exception e) {

            System.out.println("in error");
            return "error";
        }
        System.out.println(item1.getName());
        if (item1 == null) {
            throw new RuntimeException("No item found");
        }

        item1.setIs_deleted(1);
        menuItemSvc.update(item1);

        return "ok";
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
