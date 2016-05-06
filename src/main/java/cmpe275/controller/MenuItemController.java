package cmpe275.controller;
import cmpe275.Model.MenuItem;
import cmpe275.Service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanghao on 3/22/16.
 */
@Controller
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemSvc;


    //Create Menu
    @RequestMapping(value = "/adminAddMenuItem", method = RequestMethod.POST)
    public ModelAndView postMenuItem(@RequestParam("file") MultipartFile file,
                               @ModelAttribute("item1") MenuItem item1,
                               HttpServletRequest request) throws IOException {
        System.out.println("in add menu item");

        if(!file.isEmpty()) {
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

            String originalName = file.getOriginalFilename();
            System.out.println(originalName);

            String rpath=request.getRealPath("/");
            System.out.println(rpath);

            String filePath =  "Images/" + originalName;
            String totalPtah = rpath + filePath;
            File destination = new File(totalPtah);



            ImageIO.write(src, "png", destination);

            item1.setImage_path(filePath);
            menuItemSvc.add(item1);
        } else {
            throw new RuntimeException("image is empty");
        }
        return getMenuForm();
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

        List<MenuItem>  originalmenuList = menuItemSvc.getAll();
        List<MenuItem> menuList = getViableMenu(originalmenuList);

        if (menuList == null) {
            throw new RuntimeException("Menu List is empty");
        }
        return menuList;
    }

    @RequestMapping(value = "/adminAddMenuForm", method = RequestMethod.GET)
    public  ModelAndView  toAddMenuForm() {
        ModelAndView model = new ModelAndView("View/adminAddMenuForm");
        return model;
    }

    private List<MenuItem> getViableMenu(List<MenuItem> menus) {
        List<MenuItem> newMenuList = new ArrayList<MenuItem>();
        for (MenuItem menu: menus) {
            if (menu.getIs_deleted() == 0) {
                newMenuList.add(menu);
            }
        }
        return newMenuList;
    }

    //obtain menuForm
    @RequestMapping(value = "/adminMenu", method = RequestMethod.GET)
    public ModelAndView getMenuForm() {
        System.out.println("in menu form");
        List<MenuItem>  menuList = menuItemSvc.getAll();
        List<MenuItem> finalMenuList = getViableMenu(menuList);
        if (finalMenuList == null) {
            throw new RuntimeException("Menu List is empty");
        }

        ModelAndView model = new ModelAndView("View/adminMenu");
        model.addObject("menus", finalMenuList);
        return model;
    }

    @RequestMapping(value = "/menuHome", method = RequestMethod.GET)
    public ModelAndView getMenuHome() {
        List<MenuItem>  menuList = menuItemSvc.getAll();
        List<MenuItem> finalMenuList = getViableMenu(menuList);

        if (finalMenuList == null) {
            throw new RuntimeException("Menu List is empty");
        }

        ModelAndView model = new ModelAndView("View/menuHomepage");
        model.addObject("menus", finalMenuList);

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
