package cmpe275.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wanghao on 3/22/16.
 */
@Controller
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemSvc;

    @RequestMapping(value = "/addMenuItem", method = RequestMethod.POST)
    public ModelAndView postMenuItem(@ModelAttribute("item1") MenuItem item1) {
        System.out.println("in add menu item");

        menuItemSvc.add(item1);

        ModelAndView model = new ModelAndView("View/AddSuccess");

        model.addObject("item1", item1);
        return model;
    }

    @RequestMapping(value = "/menuForm", method = RequestMethod.GET)
    public ModelAndView getMenuForm() {
        System.out.println("in menu form");

        ModelAndView model = new ModelAndView("View/ProfileTemplate");

        return model;
    }

    @RequestMapping(value = "/deleteMenuItem/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMenuItem(@PathVariable("id") String id) {

        MenuItem item1 = menuItemSvc.getById(id);
        if (item1 != null) {
            item1.setIs_deleted(1);
            menuItemSvc.update(item1);

            ModelAndView model = new ModelAndView("View/AddSuccess");
            model.addObject("item1", item1);
            return model;
        } else {
            ModelAndView model = new ModelAndView("View/404");
            return model;
        }
    }

    @RequestMapping(value = "/deleteMenuByNameInCat", params = {"name", "category"},method = RequestMethod.DELETE)
    public ModelAndView deleteMenuByName(@RequestParam(value = "name") String name,
                                         @RequestParam(value = "category") String category) {

        System.out.println("in api call");
        List<MenuItem> allItemFound = menuItemSvc.getByName(name);
        if (!allItemFound.isEmpty()) {
            for (MenuItem item1 : allItemFound) {
                System.out.println(item1.getImage_path());
                System.out.println("category found: " + item1.getCategory());
                System.out.println("category input: " + category);
                if (Integer.toString(item1.getCategory()).equals(category)) {
                    System.out.println("in side dataSetting");
                    item1.setIs_deleted(1);
                    menuItemSvc.update(item1);
                    ModelAndView model = new ModelAndView("View/AddSuccess");
                    model.addObject("item1", item1);
                    return model;
                }
            }
        }
        ModelAndView model = new ModelAndView("View/404");
        return model;
        }
}
