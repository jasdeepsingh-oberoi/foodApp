package cmpe275.controller;
import cmpe275.Model.MenuItem;
import cmpe275.Service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cmpe275.Service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import cmpe275.Model.Order;
import org.springframework.ui.Model;
import java.util.Date;

import java.util.List;

/**
 * Created by wanghao on 5/1/16.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService ordersvc;

    @RequestMapping(value = "/orderForm", method = RequestMethod.GET)
    public ModelAndView getOrderForm() {
        System.out.println("in menu form");

        ModelAndView model = new ModelAndView("View/OrderTemplate");

        return model;
    }


    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public String postOrder(@ModelAttribute("order1") Order order1) {

        ordersvc.add(order1);
        return "success";
    }


    @RequestMapping(value = "/showorderhistory", method = RequestMethod.GET)
    @ResponseBody
    public  List<Order> showAllOrders() {

        List<Order> orderList = ordersvc.getAll();
        for(Order od: orderList) {
            System.out.println(od.getStart_time());
        }
        if (orderList == null) {
            throw new RuntimeException("Cannot find order history");
        }
        return orderList;
    }

    @RequestMapping(value = "/vieworderbystarttime/{starttime}", method = RequestMethod.GET)
    @ResponseBody
    public  List<Order> displayOrderByStartTime(@PathVariable("starttime") Date starttime ) {

        List<Order> orderList = ordersvc.getByStartTime(starttime);
        if (orderList == null) {
            throw new RuntimeException("Cannot find order history");
        }
        return orderList;
    }



    //Delete order By ID
    @RequestMapping(value = "/deleteorder/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteOrder(@PathVariable("id") String id) {

        Order order1 = ordersvc.getById(id);
        if (order1 == null) {
            throw new RuntimeException("order can not found");
        }
        ordersvc.delete(id);
        return "success";
    }






}
